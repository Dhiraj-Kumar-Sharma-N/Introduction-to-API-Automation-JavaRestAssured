package ReadyAPI_Advanced;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import InpuOutputFiles.InputOutputPayload;

public class JIRAAPI_AddCommenttoIssue {

	public static void main(String[] args) {
		
		
		RestAssured.baseURI="http://localhost:8080";
		
		
		// LOGIN TO GET THE COOKIE/SESSION ID
		
		String AuthenticateResponse = given().header("Content-Type","application/json").body(InputOutputPayload.JIRAAuthenticateUser())
		.when().post("/rest/auth/1/session").then().extract().body().asString();
		
		JsonPath js = Utilities.ReusableUtilities.ParseStringtoJSON(AuthenticateResponse);
		
		String cookie1=js.getString("session.name");
		String cookie2=js.getString("session.value");
		
		String cookie = cookie1+"="+cookie2;
		
		System.out.println("COOKIE FROM THE SESSION IS = "+cookie);
		
		// ADD COMMENT TO AN ISSUE IN JIRA
		
		given().pathParam("key", "10003").header("Cookie",cookie)
		.header("Content-Type","application/json").body(InputOutputPayload.JIRAAddComment()).when()
		.post("/rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201).log().all();
	}

}
