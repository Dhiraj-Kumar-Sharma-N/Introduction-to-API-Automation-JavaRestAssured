package DataDrivenTest_Excel_Hashmap;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;

import InpuOutputFiles.InputOutputPayload;
import Utilities.ReusableUtilities;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ExcelDataDrivenLibraryAPI {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

			ReusableUtilities obj = new ReusableUtilities();
			ArrayList<String> inputarrpl = obj.RetrieveExcelData("AddBookData","Java Basic");
			

			RestAssured.baseURI="http://216.10.245.166";
			
			String AddResponse = given().header("Content-Type", "application/json").log().all().body(InputOutputPayload.ExcelAddBook(inputarrpl))
			.when().post("/Library/Addbook.php").then().extract().response().asString();
			
			JsonPath js = ReusableUtilities.ParseStringtoJSON(AddResponse);
			
			System.out.println(AddResponse);
			
			String BookID = js.get("ID").toString();
			
			System.out.println("BOOK ID IS = "+ BookID);
	}

}
