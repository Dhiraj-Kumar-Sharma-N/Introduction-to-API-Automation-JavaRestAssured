package ReadyAPI_Basics;

import InpuOutputFiles.InputOutputPayload;
import io.restassured.path.json.JsonPath;

public class TC08_PrintPurchaseAmount {


	public static void main(String[] args) {
		

			JsonPath jsobj = new JsonPath(InputOutputPayload.MockResponse());
			
			int PurchaseAmt = jsobj.getInt("dashboard.purchaseAmount");
				System.out.println("Purchase Amount Returned from the API is = "+PurchaseAmt);
	}
}

