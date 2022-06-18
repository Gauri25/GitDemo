package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static  io.restassured.RestAssured.*;
public class DynamicJSON {
 Payload load=new Payload();
 
	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle){
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().log().all().header("Content-Type","application/json").
		body(load.addBook(isbn,aisle)).
		when().post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().asString();
		JsonPath js = ReUsableMethod.rawToJson(response);
		String id=js.get("ID");
		System.out.println(id);
	}
@DataProvider(name="BooksData")
public Object[][] getData()
{
	//collection of elements =array 
	//collection of multiple arrays =Multidimensional array 
	return new Object [][] {{"aswd123","37338"},{"adddd123","4338"},{"wddf123","98338"}};
}
}
