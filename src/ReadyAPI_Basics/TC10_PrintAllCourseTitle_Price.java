package ReadyAPI_Basics;

import java.util.List;

import InpuOutputFiles.InputOutputPayload;
import io.restassured.path.json.JsonPath;

public class TC10_PrintAllCourseTitle_Price {


	public static void main(String[] args) {
		

			JsonPath jsobj = new JsonPath(InputOutputPayload.MockResponse());
			
			List<Object> CourseCount = jsobj.getList("courses");
			
			for (int i = 0; i < CourseCount.size(); i++) {
				
				String CourseTitle = jsobj.getString("courses["+i+"].title");
				String CoursePrice = jsobj.getString("courses["+i+"].price");
				
				System.out.println("====COURSE DETAILS====\nCourse number "+i+"\nCourse Title "+CourseTitle+"\nCourse Price "+ CoursePrice);
			}
				
	}
}

