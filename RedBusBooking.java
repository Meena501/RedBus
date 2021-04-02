package assign;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class RedBusBooking {
	WebDriver driver;
  @Test
  public void f() throws InterruptedException {
	  driver.get("https://www.redbus.com");
	  String homeurl=driver.getCurrentUrl();
	  WebElement src=driver.findElement(By.id("src"));
	  src.sendKeys("Mysore");
	  Thread.sleep(2000);
	  src.sendKeys(Keys.TAB);
	  src.sendKeys(Keys.ENTER); // enter source as mysore then click tab then click enter ...
	  Thread.sleep(2000);
	  WebElement destination=driver.findElement(By.id("dest"));  
	  destination.sendKeys("Bangalore");
	  Thread.sleep(2000);
	  destination.sendKeys(Keys.TAB);
	  destination.sendKeys(Keys.ENTER);//enter destination as bangalore then tab then enter....
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("/html/body/section/div[2]/main/div[2]/section/div/div[2]/section/div/div[3]/span")).click();
	  // To select a particular date use the below code ...
	  String CurrentMonYear=driver.findElement(By.xpath("/html/body/div[5]/table/tbody/tr[1]/td[2]")).getText();
	  String ExpMonthYear="Apr 2021";
	  if(CurrentMonYear.equals(ExpMonthYear)) {
		  
	  }
	  else {
		  for(int i=1;i<12;i++) {
			  driver.findElement(By.xpath("/html/body/div[5]/table/tbody/tr[1]/td[3]")).click();
			   CurrentMonYear=driver.findElement(By.xpath("/html/body/div[5]/table/tbody/tr[1]/td[2]")).getText();
			   if(CurrentMonYear.equals(ExpMonthYear)) {
					break;  
				  }
		  }
	  }
	  WebElement pickDatetab=driver.findElement(By.xpath("/html/body/div[5]/table/tbody"));
	  List<WebElement> dates=pickDatetab.findElements(By.tagName("td"));
	  for(WebElement date:dates) {
		  String bookdate=date.getText();
		  if(bookdate.equals("13")) {
			  date.click();
			  break;
		  }
	  }
	  
	  driver.findElement(By.id("search_btn")).click(); // To search available buses for the journey...
	  Thread.sleep(3000);
	  String nowUrl=driver.getCurrentUrl();
	  // Check whether the apge url has changed or not using explicit wait statements ....
	  if(homeurl.equalsIgnoreCase(nowUrl)) {
		System.out.println("We are still on the same page"); 
	  }
	  else
		  {
		  
		  WebDriverWait wait = new WebDriverWait(driver,60);
		  wait.until(ExpectedConditions.urlContains(nowUrl)); 
		  System.out.println("Web page url has changed and available bus details are showing... ");
		  }
  }
  @BeforeClass
  public void beforeClass() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\Desktop\\chromedriver_win32 (1)\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.redbus.com");
		Thread.sleep(1000);
  }

  @AfterClass
  public void afterClass() {
	 // driver.quit();
  }

}
