import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Pojo.Addplace;
import Pojo.Location;
public class SerializedTest {

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
String responce=given().queryParam("key","qaclick123").log().all().
body(p).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
	System.out.println(responce);
	}

}
