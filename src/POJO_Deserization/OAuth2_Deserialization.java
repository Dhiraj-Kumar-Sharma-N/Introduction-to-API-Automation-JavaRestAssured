package POJO_Deserization;

import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class OAuth2_Deserialization {

	//static List<String> Expectedcourselist = new ArrayList<>(Arrays.asList("Selenium Webdriver Java", "Cypress", "Protractor"));

	static String[] Expectedcourselist = {"Selenium Webdriver Java", "Cypress", "Protractor"};
	static List<String> ExpectedcourselistArr= Arrays.asList(Expectedcourselist);
	
	public static void main(String[] args) throws InterruptedException {
		

		// GET AUTHORIZATION CODE FROM BROWSER USING SELENIUM

		System.setProperty("webdriver.chrome.driver", "./WebDrivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(
				"https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");

		String Username = "testing123api@gmail.com";
		String Password = "Killer@123";

		driver.findElement(By.xpath("//*[@id='identifierId']")).sendKeys(Username);
		Thread.sleep(2000);
		WebElement clickbutton = driver.findElement(By.xpath("(//*[contains(text(),'Next')])[2]"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", clickbutton);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@name='password']")).sendKeys(Password);
		Thread.sleep(2000);
		WebElement clickbutton1 = driver.findElement(By.xpath("(//*[contains(text(),'Next')])[2]"));
		js.executeScript("arguments[0].click();", clickbutton1);
		Thread.sleep(3000);

		String GetCodeResponse = driver.getCurrentUrl();

		String[] SplitString = GetCodeResponse.split("&", 2);

		String[] Code = SplitString[0].split("=");

		String AuthorizationCode = Code[1];

		System.out.println("Authorization code is =" + AuthorizationCode);

		driver.quit();

		// GET ACCESS CODE BY FEEDING AUTHORIZATION CODE TO AUTH_URL

		String GetAccessCodeResponse = given().urlEncodingEnabled(false).queryParam("code", AuthorizationCode)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when()
				.post("https://www.googleapis.com/oauth2/v4/token").then().extract().response().asString();

		JsonPath jsp = new JsonPath(GetAccessCodeResponse);

		String AccessCode = jsp.getString("access_token");

		System.out.println("ACCESS CODE is = " + AccessCode);

		// USE ACCESS TOKEN TO GET ALL THE COURSES using POJO Class

		GetCourseResponse_POJO PojoObj = given().queryParam("access_token", AccessCode).expect()
				.defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php")
				.as(GetCourseResponse_POJO.class);

		System.out.println("Instructor Name is " + PojoObj.getInstructor());

		System.out.println("Instructor Expertises in " + PojoObj.getExpertise());

		// GET PRICE OF SEARCHED COURSE FROM API NESTED JSON

		int NumberOfCoursesinAPI = PojoObj.getCourses().getApi().size();

		for (int i = 0; i < NumberOfCoursesinAPI; i++) {

			String searchcourse = "SoapUI Webservices testing";

			if (PojoObj.getCourses().getApi().get(i).getCourseTitle().equalsIgnoreCase(searchcourse)) {

				String priceofsearchedcourse = PojoObj.getCourses().getApi().get(i).getPrice();
				System.out.println("Price of " + searchcourse + " is = " + priceofsearchedcourse);
			}
		}

		// GET COURSE TITLE OF ALL COURSES FROM WEBAUTOMATION NESTED JSON

		int NumberOfCoursesinWebAutomation = PojoObj.getCourses().getWebAutomation().size();
		ArrayList<String> courselist = new ArrayList<>();
		for (int i = 0; i < NumberOfCoursesinWebAutomation; i++) {

			String courseTitles = PojoObj.getCourses().getWebAutomation().get(i).getCourseTitle();

			courselist.add(courseTitles);

		}
		System.out.println("WebAutomation contains the following Courses  = " + courselist);

		if (courselist.containsAll(ExpectedcourselistArr)) {

			System.out.println("All Courses of webautomation Exists ");
		} else {
			System.out.println("All Courses of webautomation  does not Exists ");
		}
	}

}
