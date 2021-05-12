package AdvancedPayloadCreation;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Utilities.ReusableUtilities;
import io.restassured.RestAssured;

public class RetrieveDatafromExternalJsonFile {

	public static void main(String[] args) throws IOException {
		

		RestAssured.baseURI = "http://216.10.245.166";

		given().header("Content-Type", "application/json").log().all()
				.body(ReusableUtilities.GenerateStringFromResource("./src/InpuOutputFiles/JsonSampleData.json")).when()
				.post("/Library/Addbook.php").then().log().all();

		given().header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("./src/InpuOutputFiles/JsonSampleData.json")))).when()
				.post("/Library/Addbook.php").then().assertThat().statusCode(200);

		System.out.println("ASSERTION PASSED");
	}

}
