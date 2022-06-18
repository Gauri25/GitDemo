import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Pojo.Addplace;
import Pojo.Location;
public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
RestAssured.baseURI="https://rahulshettyacademy.com";
Addplace p=new Addplace();
p.setAccuracy(50);
p.setAddress("29, side layout, cohen 09");
p.setLanguage("French-IN");
p.setName("Gauri Jagtap");
p.setPhone_number("(+91) 983 893 3937");
p.setWebsite("https://rahulshettyacademy.com");
List<String> mylist=new ArrayList<String>();
mylist.add("shoe park");
mylist.add("shop");
p.setTypes(mylist);
Location l=new Location();
l.setLat(-38.383494);
l.setLng(33.427362);
p.setLocation(l);
RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").
setContentType(ContentType.JSON).build();

ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

RequestSpecification responce=given().spec(req).body(p);

Response responce1=responce.when().post("/maps/api/place/add/json").then().spec(resp).extract().response();
	System.out.println(responce1.asString());
	}

}
