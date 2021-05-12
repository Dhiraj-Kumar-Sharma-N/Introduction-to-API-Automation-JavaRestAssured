package ReadyAPI_Advanced;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import InpuOutputFiles.InputOutputPayload;
import java.util.*;

public class JIRAAPI_VerifyAttachment_Comments {

	public static void main(String[] args) {
	
		RestAssured.baseURI = "http://localhost:8080";

		// LOGIN TO GET THE COOKIE/SESSION ID

		SessionFilter sessionvalue = new SessionFilter();

		given().header("Content-Type", "application/json").relaxedHTTPSValidation().body(InputOutputPayload.JIRAAuthenticateUser())
				.filter(sessionvalue).when().post("/rest/auth/1/session").then().extract().body().asString();

		String ID = "10005";

		// ADD COMMENT & RETRIEVE COMMENT ID TO AN ISSUE IN JIRA

		String AddcommentResponse = given().pathParam("key", ID).header("Content-Type", "application/json")
				.body(InputOutputPayload.JIRAAddComment()).filter(sessionvalue).when()
				.post("/rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201).extract().response()
				.asString();

		JsonPath js1 = new JsonPath(AddcommentResponse);

		String CommentID = js1.getString("id");

		System.out.println("COMMENT ID IS =" + CommentID);

		// GET ISSUE DETAILS IN JIRA & VERIFY IF THE COMMENT IS ADDED

		String getIssueDetailforComment = given().pathParam("key", ID).queryParam("fields", "comment")
				.filter(sessionvalue).when().get("/rest/api/2/issue/{key}").then().assertThat().statusCode(200)
				.extract().response().asString();

		JsonPath js2 = new JsonPath(getIssueDetailforComment);

		List<Object> CountofComments = js2.getList("fields.comment.comments");

		System.out.println("Number of Comments is " + CountofComments.size());

		for (int i = 0; i < CountofComments.size(); i++) {

			if (js2.get("fields.comment.comments[" + i + "].id").toString().equalsIgnoreCase(CommentID)) {

				System.out.println("Successfully Verified Comment Exist");
			}
		}

		String getIssueDetailforAttachement = given().pathParam("key", ID).queryParam("fields", "attachment")
				.filter(sessionvalue).when().get("/rest/api/2/issue/{key}").then().assertThat().statusCode(200)
				.extract().response().asString();

		JsonPath js3 = new JsonPath(getIssueDetailforAttachement);

		List<Object> CountofAttachement = js3.getList("fields.attachment");

		System.out.println("Number of Attachements is " + CountofAttachement.size());

		for (int i = 0; i < CountofAttachement.size(); i++) {

			if (js3.get("fields.attachment[" + i + "].filename").toString().equalsIgnoreCase("StatusCodes.png")) {

				System.out.println("Successfully Verified Attachment Exist ");
			}

		}
	}

}
