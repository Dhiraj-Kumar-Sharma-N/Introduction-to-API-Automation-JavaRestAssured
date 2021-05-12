package ReadyAPI_Basics;

import InpuOutputFiles.InputOutputPayload;
import io.restassured.path.json.JsonPath;

public class TC09_PrintTitleOfFirstCourse {


	public static void main(String[] args) {
		

			JsonPath jsobj = new JsonPath(InputOutputPayload.MockResponse());
						
			String FirstCourseTitle = jsobj.get("courses[0].title").toString();

			System.out.println("Title of First Courses Returned from the API is = "+FirstCourseTitle);
	}
}

