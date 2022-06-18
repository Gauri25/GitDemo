import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	JsonPath JS3=new JsonPath(Payload.course());
	//print the courses
	int count=JS3.getInt("courses.size()");
	System.out.println(count);
	//print purches amount 
	int amt=JS3.getInt("dashboard.purchaseAmount");
	System.out.println(amt);
	String title=JS3.getString("courses[0].title");
	System.out.println(title);
	int copies1=0;
	int price=0;
	for(int i=0;i<count;i++)
	{
	String title1=JS3.getString("courses["+i+"].title");
		System.out.println(title1);
		 copies1=JS3.getInt("courses["+i+"].copies");
		if(title1.equalsIgnoreCase("RPA")) {
			int copies=JS3.getInt("courses["+i+"].copies");
		   System.out.println("Copies sold by RPA"+copies);
		  
		}
	price=JS3.getInt("courses["+i+"].price");
	System.out.println(price);
	
	
		}
	int Totalamt=copies1*price;
	if(Totalamt==amt)
	{
		System.out.println("Price is matching");
	}

	}
	

}
