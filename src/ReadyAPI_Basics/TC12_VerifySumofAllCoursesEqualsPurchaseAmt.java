package ReadyAPI_Basics;

import java.util.List;
import org.testng.annotations.Test;
import InpuOutputFiles.InputOutputPayload;
import io.restassured.path.json.JsonPath;

public class TC12_VerifySumofAllCoursesEqualsPurchaseAmt {
	static int sum = 0;

	@Test
	public static void ValidateSum() {
		

		JsonPath jsobj = new JsonPath(InputOutputPayload.MockResponse());

		List<Object> CourseCount = jsobj.getList("courses");

		for (int i = 0; i < CourseCount.size(); i++) {

			int CoursePrice = jsobj.getInt("courses[" + i + "].price");
			int copiesSold = jsobj.getInt("courses[" + i + "].copies");

			int total = CoursePrice * copiesSold;
			sum=sum+total;
		}
			System.out.println("Sum of All courses is "+sum);
			int PurchaseAmt = jsobj.getInt("dashboard.purchaseAmount");
			
			if (sum == PurchaseAmt) {
				System.out.println("Successfully Verified Sum of all courses is equal to Purchase Amount" );
			} else {
				System.out.println(" Sum of all course is  not equal to Purchase Amount" );
			}
	}
	
		
}
