package SpecBuilder;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import InpuOutputFiles.InputOutputPayload;
import org.testng.Assert;

public class RequestSpecBuilder {

	public static void main(String[] args) {
		

		//REQUEST SPEC BUILD 
		
		RequestSpecification ReqSpecObj = new io.restassured.builder.RequestSpecBuilder()
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
				.setBaseUri("https://rahulshettyacademy.com").build();
		
		

		String ResponseAsString = given().spec(ReqSpecObj).body(InputOutputPayload.PostInputPayload()).when()
				.post("maps/api/place/add/json").then().extract().response().asString();

		JsonPath jsonpathParser = new JsonPath(ResponseAsString);

		String PlaceID = jsonpathParser.getString("place_id");

		System.out.println("Retrieved Place Id is : " + PlaceID);

		given().spec(ReqSpecObj).queryParam("place_id", PlaceID).when().get("maps/api/place/get/json").then().log()
				.all();

		System.out.println("RETRIEVE/GET OPERATION = Completed");

// Update Place
		String newAddress = "Summer Walk, Africa";

		given().spec(ReqSpecObj)
				.body("{\r\n" + "\"place_id\":\"" + PlaceID + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		System.out.println("UPDATE/PUT OPERATION = Completed");

		String UpdatedResponse = given().spec(ReqSpecObj).queryParam("place_id", PlaceID).when()
				.get("maps/api/place/get/json").then().extract().response().asString();

		JsonPath jsonpathobj = new JsonPath(UpdatedResponse);
		String UpdatedAdress = jsonpathobj.getString("address");
		Assert.assertEquals(UpdatedAdress, newAddress);

		if (UpdatedAdress.equalsIgnoreCase(newAddress)) {

			System.out.println("Address updated Successfully");
		} else {

			System.out.println("Failed to update Address");
		}
		given().spec(ReqSpecObj).body("{\r\n" + "    \"place_id\":\"" + PlaceID + "\"   	 	\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/delete/json").then().assertThat().statusCode(200)
				.body("status", equalTo("OK"));

		System.out.println("DELETE OPERATION = Completed");

	}

}
