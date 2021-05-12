package ReadyAPI_Basics;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import InpuOutputFiles.InputOutputPayload;


public class TC01_ValidateResponseCode {


public static void ValidateResoinseCode200() {
	RestAssured.baseURI= "https://rahulshettyacademy.com";
	
	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body("{\r\n" + 
			"  \"location\": {\r\n" + 
			"    \"lat\": -38.383494,\r\n" + 
			"    \"lng\": 33.427362\r\n" + 
			"  },\r\n" + 
			"  \"accuracy\": 50,\r\n" + 
			"  \"name\": \"Frontline house\",\r\n" + 
			"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
			"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
			"  \"types\": [\r\n" + 
			"    \"shoe park\",\r\n" + 
			"    \"shop\"\r\n" + 
			"  ],\r\n" + 
			"  \"website\": \"http://google.com\",\r\n" + 
			"  \"language\": \"French-IN\"\r\n" + 
			"}\r\n" + 
			"").when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200);
	
}

	public static void main(String[] args) {
		

		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"").when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",equalTo("APP"));
		
				given().queryParam("key","qaclick123").header("Content-Type","application/json")
				.body(InputOutputPayload.PostInputPayload()).when().post("maps/api/place/add/json").then().assertThat().header("Server",equalTo("Apache/2.4.18 (Ubuntu)"));
		
				System.out.println(given().queryParam("key","qaclick123").header("Content-Type","application/json")
				.body(InputOutputPayload.PostInputPayload()).when().post("maps/api/place/add/json").then().extract().contentType());
		
				
				System.out.println("Assertion Passed");
	}
}



/*  ========================= OUTPUT ==================================================

Request method:	POST
Request URI:	https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123
Proxy:			<none>
Request params:	<none>
Query params:	key=qaclick123
Form params:	<none>
Path params:	<none>
Headers:		Accept=**
				Content-Type=application/json; charset=UTF-8
Cookies:		<none>
Multiparts:		<none>
Body:
{
    "location": {
        "lat": -38.383494,
        "lng": 33.427362
    },
    "accuracy": 50,
    "name": "Frontline house",
    "phone_number": "(+91) 983 893 3937",
    "address": "29, side layout, cohen 09",
    "types": [
        "shoe park",
        "shop"
    ],
    "website": "http://google.com",
    "language": "French-IN"
}
HTTP/1.1 200 OK
Date: Tue, 04 May 2021 09:42:38 GMT
Server: Apache/2.4.18 (Ubuntu)
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: POST
Access-Control-Max-Age: 3600
Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With
Content-Length: 194
Keep-Alive: timeout=5, max=100
Connection: Keep-Alive
Content-Type: application/json;charset=UTF-8

{
    "status": "OK",
    "place_id": "e4f5a262454f8f93bc4be523ac96dc62",
    "scope": "APP",
    "reference": "c64c22932ace32bdd0d9b106cc52c0b4c64c22932ace32bdd0d9b106cc52c0b4",
    "id": "c64c22932ace32bdd0d9b106cc52c0b4"
}


*/