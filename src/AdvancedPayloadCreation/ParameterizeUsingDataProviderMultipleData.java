package AdvancedPayloadCreation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import InpuOutputFiles.InputOutputPayload;
import Utilities.ReusableUtilities;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class ParameterizeUsingDataProviderMultipleData {
	
	@Test(dataProvider = "MultpleBooksData")
	
	public static void AddBook(int id,String Name) {
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String AddResponse = given().header("Content-Type", "application/json").body(InputOutputPayload.AddBook(id,Name))
		.when().post("/Library/Addbook.php").then().extract().response().asString();
		
		JsonPath js = ReusableUtilities.ParseStringtoJSON(AddResponse);
		
		String BookID = js.get("ID").toString();
		
		System.out.println("BOOK ID IS = "+ BookID);
		
		given().queryParam("ID", BookID).when().get("Library/GetBook.php").then().log().all();
		
	}
	
	@DataProvider(name="MultpleBooksData")
	
	public static Object[][] GetBookData() {
		
		//Array - collection of Elements MultidimensionalArray = Collection of Array
		return new Object[][] {{112,"Ramesh"},{113,"Suresh"},{114,"Naresh"}};
	}
}
