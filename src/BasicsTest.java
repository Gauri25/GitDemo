import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import org.testng.Assert;

import files.Payload;
import files.ReUsableMethod;

public class BasicsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//Validate if add place working as expected 
		//Given =All input details
		//When=Submit the API there //Resouces and http method will go in When method 
		//Then=Validate the response
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		Payload pay1=new Payload();
		
		String responce=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
		body(pay1.addplace()).when().post("maps/api/place/add/json").
		then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		//add place->Update place with new address ->Get place to validate with new address 
	System.out.println(responce);
	JsonPath js=new JsonPath(responce);
	String placeID=js.getString("place_id");
	System.out.println(placeID);
	System.out.println(placeID);
	System.out.println(placeID);
	//Update place 
	String newAddress="70 Summer walk, USA";
   given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").
	body("{\r\n"
			+ "\"place_id\":\""+placeID+"\",\r\n"
			+ "\"address\":\""+newAddress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}").
	when().put("maps/api/place/update/json").
	then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
	//get api
	String getResponce =given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeID).when().get("maps/api/place/get/json")
	.then().log().all().assertThat().statusCode(200).extract().response().asString();
	//JsonPath js1=new JsonPath(getResponce);
	JsonPath js1=ReUsableMethod.rawToJson(getResponce);
	String actualadd=js1.getString("address");
	System.out.println(actualadd);
    Assert.assertEquals(actualadd, newAddress);
	}
}
