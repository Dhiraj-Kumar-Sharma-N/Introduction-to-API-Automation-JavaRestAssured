package ReadyAPI_Basics;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TC02_ValidateMultipleResponseParam {
public static String url = "http://demo.guru99.com/V4/sinkministatement.php";
	public static void getResponseBody() {
		
		given().when().get("http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1").then().log().all();
	}
	
public static void getResponseBodyUpdated() {
		
		given().queryParam("CUSTOMER_ID", "68195").queryParam("PASSWORD", "1234!").queryParam("Account_No", "1")
		.when().get(url).then().log().body();
	}

public static void getResponseStatus(){
		
	   int statusCode= given().queryParam("CUSTOMER_ID","68195")
	           .queryParam("PASSWORD","1234!")
	           .queryParam("Account_No","1") .when().get(url).getStatusCode();
	   System.out.println("The response status is "+statusCode);

	   given().when().get(url).then().assertThat().statusCode(200);
	}

public static void getResponseHeaders(){
	   System.out.println("The headers in the response "+
	                   get(url).then().extract()
	           .headers());
	}

public static void getResponseTime(){
	  System.out.println("The time taken to fetch the response "+get(url)
	         .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
	}

public static void getResponseContentType(){
	   System.out.println("The content type of response "+
	           get(url).then().extract()
	              .contentType());
	}


public static void getSpecificPartOfResponseBody(){

ArrayList<String> amounts = when().get(url).then().extract().path("result.statements.AMOUNT") ;
int sumOfAll=0;
for(String a:amounts){

    System.out.println("The amount value fetched is "+a);
    sumOfAll=sumOfAll+Integer.valueOf(a);
}
System.out.println("The total amount is "+sumOfAll);

}

	public static void main(String[] args) {
		
		getResponseBodyUpdated();
		getResponseStatus();
		getResponseHeaders();
		getResponseTime();
		getResponseContentType();
		//getSpecificPartOfResponseBody();
		
		String amount = when().get(url).then().extract().path("result.statements.AMOUNT") ;
		System.out.println(amount);
	}
}

