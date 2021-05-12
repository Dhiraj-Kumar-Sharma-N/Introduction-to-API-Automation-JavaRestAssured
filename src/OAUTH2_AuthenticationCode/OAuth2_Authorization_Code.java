package OAUTH2_AuthenticationCode;

import static io.restassured.RestAssured.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.restassured.path.json.JsonPath;

public class OAuth2_Authorization_Code {

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
		Thread.sleep(2000);

		String GetCodeResponse = driver.getCurrentUrl();

		String[] SplitString = GetCodeResponse.split("&", 2);

		String[] Code = SplitString[0].split("=");

		String AuthorizationCode = Code[1];

		System.out.println("Authorization code is =" + AuthorizationCode);

		driver.quit();

		// GET ACCESS CODE BY FEEDING AUTHORIZATION CODE TO AUTH_URL

		String GetAccessCodeResponse = given().log().all().urlEncodingEnabled(false)
				.queryParam("code", AuthorizationCode)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when()
				.post("https://www.googleapis.com/oauth2/v4/token").then().log().all().extract().response().asString();

		JsonPath jsp = new JsonPath(GetAccessCodeResponse);

		String AccessCode = jsp.getString("access_token");

		System.out.println("ACCESS CODE is = " + AccessCode);

		// USE ACCESS TOKEN TO GET ALL THE COURSES

		given().queryParam("access_token", AccessCode).when().get("https://rahulshettyacademy.com/getCourse.php").then()
				.log().all().assertThat().statusCode(200);

	}

}
