package AdvancedPayloadCreation;

import org.testng.annotations.Test;

import InpuOutputFiles.InputOutputPayload;
import Utilities.ReusableUtilities;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class DynamicallyBuildJsonPayloadfromTest {
	
	@Test
	
	public static void AddBook() {
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String AddResponse = given().header("Content-Type", "application/json").log().all().body(InputOutputPayload.AddBook(133,"JHON"))
		.when().post("/Library/Addbook.php").then().extract().response().asString();
		
		JsonPath js = ReusableUtilities.ParseStringtoJSON(AddResponse);
		
		System.out.println(AddResponse);
		
		String BookID = js.get("ID").toString();
		
		System.out.println("BOOK ID IS = "+ BookID);
		
		given().queryParam("ID", BookID).when().get("Library/GetBook.php").then().log().all();
		
	}
}
