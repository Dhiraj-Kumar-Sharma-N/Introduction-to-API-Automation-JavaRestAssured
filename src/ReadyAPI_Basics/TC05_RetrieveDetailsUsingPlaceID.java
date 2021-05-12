package ReadyAPI_Basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import InpuOutputFiles.InputOutputPayload;

public class TC05_RetrieveDetailsUsingPlaceID {
public static String PlaceID;
	public static void main(String[] args) {
		

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String ResponseAsString = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(InputOutputPayload.PostInputPayload()).when().post("maps/api/place/add/json")
				.then().extract().response().asString();
		
		JsonPath jsonpathParser = new JsonPath(ResponseAsString);
		
		PlaceID = jsonpathParser.getString("place_id");
		
		System.out.println("Retrieved Place Id is : " +PlaceID);
		
		given().queryParam("key", "qaclick123").queryParam("place_id", PlaceID)
		.when().get("maps/api/place/get/json").then().log().all();
		
		System.out.println("DATA RETRIEVAL - PASS");
	}
}
