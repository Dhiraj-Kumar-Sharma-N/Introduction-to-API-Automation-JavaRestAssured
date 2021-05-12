package POJO_Serialization;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;

public class GOOGLEAPI_Serialization {

	public static void main(String[] args) {
		

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		//SET VALUES USING THE POJO CLASS IN ORDER TO CONVERT JAVA OBJECT TO JSON
		
		AddPlaceRequest_POJO Obj = new AddPlaceRequest_POJO();
		Obj.setAccuracy(90);
		Obj.setAddress("Delhi");
		Obj.setLanguage("Hindi");
		Obj.setName("Jhon Snow");
		Obj.setPhone_number("(+91) 9432198754");
		Obj.setWebsite("https://Testing123api.com/");

		List<String> ListofTypes = new ArrayList<String>();
		ListofTypes.add("Apple");
		ListofTypes.add("Orange");

		Obj.setTypes(ListofTypes);

		NestedLocatonRequest_POJO obj1 = new NestedLocatonRequest_POJO();
		obj1.setLat(22.1234);
		obj1.setLng(44.9876);

		Obj.setLocation(obj1);

		//FEED OBJECT AS AN ARGUMENT TO THE BODY 
		
		String ResponseAsString = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Obj).log().all().when().post("maps/api/place/add/json").then().extract().response().asString();

		JsonPath pt = new JsonPath(ResponseAsString);

		//IF PLACE IS ADDED AND RESPONSE STATUS IS OK THEN GET THE PLACE DETAIL
		
		if (pt.get("status").toString().equalsIgnoreCase("OK")) {

			String PlaceID = pt.getString("place_id");
			
			System.out.println("PLACE ID IS = " + PlaceID);

			given().queryParam("key", "qaclick123").queryParam("place_id", PlaceID).when()
					.get("maps/api/place/get/json").then().log().body();

		}
	}

}
