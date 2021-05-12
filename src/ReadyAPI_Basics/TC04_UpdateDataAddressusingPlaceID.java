package ReadyAPI_Basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import InpuOutputFiles.InputOutputPayload;

public class TC04_UpdateDataAddressusingPlaceID {

	public static void main(String[] args) {
		

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String ResponseAsString = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(InputOutputPayload.PostInputPayload()).when().post("maps/api/place/add/json")
				.then().extract().response().asString();
		
		JsonPath jsonpathParser = new JsonPath(ResponseAsString);
		
		String PlaceID = jsonpathParser.getString("place_id");
		
		System.out.println("Retrieved Place Id is : " +PlaceID);

		//Update Place
				String newAddress = "Summer Walk, Africa";
				
				given().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body("{\r\n" + 
						"\"place_id\":\""+PlaceID+"\",\r\n" + 
						"\"address\":\""+newAddress+"\",\r\n" + 
						"\"key\":\"qaclick123\"\r\n" + 
						"}").
				when().put("maps/api/place/update/json")
				.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
				
		System.out.println("Address Updated - PASS");
	}
}
