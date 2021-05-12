package ReadyAPI_Advanced;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.io.File;

import InpuOutputFiles.InputOutputPayload;

public class JIRAAPI_AddAttachmenttoIssue {

	public static void main(String[] args) {
		
		
		RestAssured.baseURI="http://localhost:8080";
		
		
		// LOGIN TO GET THE COOKIE/SESSION ID
		
		SessionFilter sessionvalue = new SessionFilter();
		
		given().header("Content-Type","application/json").body(InputOutputPayload.JIRAAuthenticateUser()).filter(sessionvalue)
		.when().post("/rest/auth/1/session").then().extract().body().asString();
		
		// CREATE AN ISSUE AND NOTE DOWN THE ID
		
		String CreateIssueTResponse = given().header("Content-Type","application/json").body(InputOutputPayload.JIRACreateIssue("Test Issue")).filter(sessionvalue)
		.when().post("/rest/api/2/issue").then().extract().body().asString();
		
				
				JsonPath js = Utilities.ReusableUtilities.ParseStringtoJSON(CreateIssueTResponse);
				
				String ID = js.getString("id");
				
		// ADD COMMENT TO AN ISSUE IN JIRA
		
		given().pathParam("key",ID).header("Content-Type","application/json").body(InputOutputPayload.JIRAAddComment())
		.filter(sessionvalue).when().post("/rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201).log().all();
		
		
		// ADD ATTACHEMENT TO AN ISSUE IN JIRA
		

		given().pathParam("key",ID).header("X-Atlassian-Token","nocheck").header("Content-Type","multipart/form-data")
		.filter(sessionvalue).multiPart("file",new File("./src/InpuOutputFiles/StatusCodes.png"))
		.when().post("/rest/api/2/issue/{key}/attachments").then().assertThat().statusCode(200).log().all();
		
	}

}
