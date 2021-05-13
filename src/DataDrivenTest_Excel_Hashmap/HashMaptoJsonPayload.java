package DataDrivenTest_Excel_Hashmap;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import Utilities.ReusableUtilities;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class HashMaptoJsonPayload {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

			HashMap<String, Object> HMAPObj = new HashMap<>();
			HMAPObj.put("name", "DataBase Basic");
			HMAPObj.put("isbn", "DB");
			HMAPObj.put("aisle", "108");
			HMAPObj.put("author", "High Sparrow");
			
			RestAssured.baseURI="http://216.10.245.166";
			
			String AddResponse = given().header("Content-Type", "application/json").log().all().body(HMAPObj)
			.when().post("/Library/Addbook.php").then().extract().response().asString();
			
			JsonPath js = ReusableUtilities.ParseStringtoJSON(AddResponse);
			
			System.out.println(AddResponse);
			
			String BookID = js.get("ID").toString();
			
			System.out.println("BOOK ID IS = "+ BookID);
	}

}
