package InpuOutputFiles;

import java.util.ArrayList;

public class InputOutputPayload {

	public static String PostInputPayload() {
		
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, USA\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"";
		
	}

	

	public static String MockResponse()
	{
		
		return "{\r\n" + 
				"  \"dashboard\": {\r\n" + 
				"    \"purchaseAmount\": 1162,\r\n" + 
				"    \"website\": \"rahulshettyacademy.com\"\r\n" + 
				"  },\r\n" + 
				"  \"courses\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Selenium Python\",\r\n" + 
				"      \"price\": 50,\r\n" + 
				"      \"copies\": 6\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Cypress\",\r\n" + 
				"      \"price\": 40,\r\n" + 
				"      \"copies\": 4\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"RPA\",\r\n" + 
				"      \"price\": 45,\r\n" + 
				"      \"copies\": 10\r\n" + 
				"    },\r\n" + 
				"     {\r\n" + 
				"      \"title\": \"Appium\",\r\n" + 
				"      \"price\": 36,\r\n" + 
				"      \"copies\": 7\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"  ]\r\n" + 
				"}\r\n" + 
				"";
				
}
	
	public static String AddBook(int ID,String Author) {
		
		String payload = "{\r\n" + 
				"\"name\":\"Introduction to RestAssured\",\r\n" + 
				"\"isbn\":\"DJ\",\r\n" + 
				"\"aisle\":\""+ID+"\",\r\n" + 
				"\"author\":\""+Author+"\"\r\n" + 
				"}";
		return payload;
		}
	
	public static String JIRAAddComment() {
		
		String payload = "{\r\n" + 
				"    \"body\": \"Dhiraj Lorem ipsum dolor sit amet, consectetur adipiscing elit.\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}";
		return payload;
		}
	
	public static String JIRAAuthenticateUser() {
		
		String payload = "{\r\n" + 
				"     \"username\": \"Dhirajkumar\",\r\n" + 
				" \"password\": \"9448427443\"\r\n" + 
				"  }";
		return payload;
		}
	
	public static String JIRACreateIssue(String IssueTitle) {
		
		String payload = "{\r\n" + 
				"    \"fields\": {\r\n" + 
				"        \"project\": {\r\n" + 
				"            \"key\": \"DJ\"\r\n" + 
				"        },\r\n" + 
				"        \"summary\": \""+IssueTitle+"\",\r\n" + 
				"        \"description\" : \"On logging to the URL Page it shows invalid Username Error\",\r\n" + 
				"        \"issuetype\": {\r\n" + 
				"            \"name\": \"Bug\"\r\n" + 
				"        }      \r\n" + 
				"    }\r\n" + 
				"}";
		return payload;
		}
	public static String ExcelAddBook(ArrayList<String> arr) {
		
		String payload = "{\r\n" + 
				"\"name\":\""+arr.get(0)+"\",\r\n" + 
				"\"isbn\":\""+arr.get(1)+"\",\r\n" + 
				"\"aisle\":\""+arr.get(2)+"\",\r\n" + 
				"\"author\":\""+arr.get(3)+"\"\r\n" + 
				"}";
		return payload;
		}
}
