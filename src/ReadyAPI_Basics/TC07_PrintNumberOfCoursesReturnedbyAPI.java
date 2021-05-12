package ReadyAPI_Basics;

import java.util.List;

import InpuOutputFiles.InputOutputPayload;
import io.restassured.path.json.JsonPath;

public class TC07_PrintNumberOfCoursesReturnedbyAPI {


	public static void main(String[] args) {
		

			JsonPath jsobj = new JsonPath(InputOutputPayload.MockResponse());
			
			List<Object> CourseCount = jsobj.getList("courses");
				System.out.println("Total number of Courses Returned from the API is = "+CourseCount.size());
				
				int CourseCount1 = jsobj.getInt("courses.size()");
				System.out.println("Total number of Courses Returned from the API is = "+CourseCount1);
	}
}

