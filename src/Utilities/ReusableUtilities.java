package Utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;

public class ReusableUtilities {

	public static JsonPath ParseStringtoJSON(String StringData) {
		
		//JSON PATH CLASS 
		
		JsonPath jsobj = new JsonPath(StringData);

		return jsobj;
	}

	public static String GenerateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}
}
