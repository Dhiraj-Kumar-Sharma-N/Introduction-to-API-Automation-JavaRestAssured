package ReadyAPI_Basics;

import java.util.List;
import InpuOutputFiles.InputOutputPayload;
import io.restassured.path.json.JsonPath;

public class TC11_VerifyNumberofcopiesSoldbyRPA {


	public static void main(String[] args) {
		

			JsonPath jsobj = new JsonPath(InputOutputPayload.MockResponse());
			
			List<Object> CourseCount = jsobj.getList("courses");
			
			for (int i = 0; i < CourseCount.size(); i++) {
				
				String CourseTitle = jsobj.getString("courses["+i+"].title");
				
				if (CourseTitle.equalsIgnoreCase("RPA")) {
					
					int copiesSold = jsobj.getInt("courses["+i+"].copies");
					
					System.out.println("Number of RPA Copies Sold = "+copiesSold);
					break;
				}
			}
				
	}
}

