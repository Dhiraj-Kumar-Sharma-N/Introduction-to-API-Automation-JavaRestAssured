package ReadyAPI_Basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import InpuOutputFiles.InputOutputPayload;
import org.testng.Assert;

public class TC06_CURDOperationE2E {

	public static void main(String[] args) {
		

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String ResponseAsString = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(InputOutputPayload.PostInputPayload()).when().post("maps/api/place/add/json").then().extract()
				.response().asString();

		JsonPath jsonpathParser = new JsonPath(ResponseAsString);

		String PlaceID = jsonpathParser.getString("place_id");

		System.out.println("Retrieved Place Id is : " + PlaceID);

		System.out.println("CREATE/POST OPERATION = Completed");

		given().queryParam("key", "qaclick123").queryParam("place_id", PlaceID).when().get("maps/api/place/get/json")
				.then().log().all();

		System.out.println("RETRIEVE/GET OPERATION = Completed");

		// Update Place
		String newAddress = "Summer Walk, Africa";

		given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + PlaceID + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		System.out.println("UPDATE/PUT OPERATION = Completed");

		String UpdatedResponse = given().queryParam("key", "qaclick123").queryParam("place_id", PlaceID).when()
				.get("maps/api/place/get/json").then().extract().response().asString();

		JsonPath jsonpathobj = new JsonPath(UpdatedResponse);
		String UpdatedAdress = jsonpathobj.getString("address");
		Assert.assertEquals(UpdatedAdress, newAddress);
		
		if (UpdatedAdress.equalsIgnoreCase(newAddress)) {

			System.out.println("Address updated Successfully");
		} else {

			System.out.println("Failed to update Address");
		}
		given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"place_id\":\"" + PlaceID + "\"   	 	\r\n" + "}\r\n" + "").when()
				.put("maps/api/place/delete/json").then().assertThat().statusCode(200).body("status", equalTo("OK"));

		System.out.println("DELETE OPERATION = Completed");

	}
}
