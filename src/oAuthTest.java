import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import Pojo.Api;
import Pojo.GetCourceRes;
import Pojo.WebAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
public class oAuthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
String courceTitle[]= {"SoapUI webservices testing ","Protactor","Java"};
		System.setProperty("webdriver.chrome.driver","D:\\Java\\Setup\\chromedriver.exe");
		WebDriver wb=new ChromeDriver();
		wb.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&flowName=GeneralOAuthFlow");
		wb.findElement(By.cssSelector("input[type='email']")).sendKeys("srinath19830");
		wb.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		wb.findElement(By.cssSelector("input[type='password']")).sendKeys("password");
		wb.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
	String url=	wb.getCurrentUrl();
	String partialcode=url.split("code=")[1];
	String actualcode=partialcode.split("&scope")[0];
	
	System.out.println(actualcode);
	String accesstokenresponce=	given().urlEncodingEnabled(false).
		queryParams("code",actualcode).queryParams("client_id"
				+ "", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
		queryParams("redirect URL","https://rahulshettyacademy.com/getCourse.php").
		queryParams("grant_type","authorization_code").
		when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
	System.out.println(accesstokenresponce);
	JsonPath js=new JsonPath(accesstokenresponce);
	String access_token =js.get("access_token");
	System.out.println(access_token);
GetCourceRes gc =given().queryParam("access_token", "access_token").expect().defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourceRes.class);
	//System.out.println(gc);
	
	System.out.println(gc.getLinkedIn());
	System.out.println(gc.getInstructor());

	List<Api> apicource=gc.getCourses().getApi();
for(int i=0;i<apicource.size();i++)
{
	
	System.out.println( apicource.get(i).getCourseTitle());
	if(apicource.get(i).getCourseTitle().equalsIgnoreCase("SoapUI webservices testing"))
	{
		System.out.println(apicource.get(i).getPrice());  
	}
}
List<WebAutomation> webCource=gc.getCourses().getWebAutomation();
ArrayList <String >a=new ArrayList<String>();


for(int j=0;j<webCource.size();j++) {
  a.add(webCource.get(j).getCourseTitle());	
}
List<String>expectedlist =Arrays.asList(courceTitle);
Assert.assertTrue(a.equals(expectedlist));
	}

}
