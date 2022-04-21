package weightworld;

import java.awt.AWTException;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PlacingAnOrder {

	public static void main(String[] args) throws InterruptedException, AWTException {

		//Launching the browser
		
		//From local System
		System.setProperty("webdriver.chrome.driver", "/Users/rallapallis/Downloads/chromedriver");

		//From remote system
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		//Go to the Website
		driver.navigate().to("https://www.weightworld.uk/");
		Thread.sleep(1000);


		//Search for a product
		driver.findElement(By.id(("algo_search"))).sendKeys("Turmeric");
		Thread.sleep(2000);

		//selecting the product
		driver.findElement(By.xpath("//*[@id=\"headerSearchHits\"]/div/div[1]/div/div/div[2]/a")).click();
		Thread.sleep(3000);

		//Selecting quantity
		driver.findElement(By.xpath("//*[@id=\"stickySidebar\"]/ul/li[1]")).click();

		//Accepting cookies
		driver.findElement(By.xpath("//*[@id=\"cookiescript_accept\"]")).click();
		Thread.sleep(3000);

		//Add to Basket		
		driver.findElement(By.xpath("//*[@id=\"stickySidebar\"]/button")).click();

		//Checkout
		driver.findElement(By.id(("menuHeaderTotal")));
		Thread.sleep(3000);
		driver.findElement(By.linkText("Secure Checkout")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("basketCheckoutBtn")).click();

		//Personal Page
		driver.findElement(By.id("email")).sendKeys("rajani.rallapalli@comfortclick.co.uk");
		Select lstbox = new Select(driver.findElement(By.id("del_title")));
		lstbox.selectByIndex(2);
		driver.findElement(By.name("del_firstname")).sendKeys("Test");
		driver.findElement(By.name("del_lastname")).sendKeys("Test");
		driver.findElement(By.id("del_mobile")).sendKeys("2341231234");
		Thread.sleep(1000);
		
		driver.findElement(By.name("birth_day")).sendKeys("11");
		driver.findElement(By.name("birth_month")).sendKeys("11");
		driver.findElement(By.name("birth_year")).sendKeys("2000");
		driver.findElement(By.id("del_add1")).sendKeys("Test");
		driver.findElement(By.id("del_city")).sendKeys("City");
		driver.findElement(By.id("del_zipcode")).sendKeys("12345");
		
		driver.findElement(By.id("del_country_id_title")).click();
		
		driver.findElement(By.xpath("//*[@id=\"del_country_id_child\"]/ul/li[37]"));
		driver.findElement(By.id("checkoutBtn")).click();
		Thread.sleep(1000);


		//Open Laravel window and login 
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://www.weightworld.uk/checkout/admin/login\\");
		
		//To login 
		driver.findElement(By.id("uname")).sendKeys("developer");
		driver.findElement(By.id("password")).sendKeys("@#login");
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div/form/div[3]/div/button")).click();
		Thread.sleep(2000);
		driver.switchTo().window(tabs.get(0));

		//Delivery Page
		driver.findElement(By.id("shippingMethodDesc_2")).click();
		driver.findElement(By.name("checkout")).click();
		Thread.sleep(2000);
		
		//Payment Page
		driver.findElement(By.xpath("//*[@id=\"paymentForm\"]/div[2]/div/label/span[1]")).click();
		driver.switchTo().frame("cko-iframe-id");
		driver.findElement(By.xpath("//*[@id=\"modal-wrapper\"]/div/div/div/form/div[1]/input")).sendKeys("4242424242424242");
		driver.findElement(By.xpath("//*[@id=\"modal-wrapper\"]/div/div/div/form/div[2]/div[1]/div/div/div[1]/input")).sendKeys("11");
		driver.findElement(By.xpath("//*[@id=\"modal-wrapper\"]/div/div/div/form/div[2]/div[1]/div/div/div[2]/input")).sendKeys("23");
		driver.findElement(By.xpath("//*[@id=\"modal-wrapper\"]/div/div/div/form/div[2]/div[2]/div/input")).sendKeys("100");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		//To scroll down
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll (0,350)");
		
		driver.findElement(By.xpath("//*[@id=\"paymentForm\"]/div[4]/div[2]/div/label[2]/span")).click();
		//driver.findElement(By.xpath("//*[@id=\"paymentForm\"]/div[4]/div[2]/div/label[2]/span")).click();
		driver.findElement(By.id("place-order")).click();
		Thread.sleep(3000);
		
		//Payment sandbox window handling
		driver.switchTo().frame("cko-3ds2-iframe");
		driver.findElement(By.id("password")).sendKeys("Checkout1!");
		driver.findElement(By.id("txtButton")).submit();
		
		//Closing the Google reviews pop up
		Thread.sleep(4000);
		WebElement frame = driver.findElement(By.xpath("//iframe[contains(@id,'I0_')]"));
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("/html/body/c-wiz/div/div/div[2]/div[2]/span[1]/div/button")).click();
		
		System.out.println(driver.findElement(By.xpath("/html/body/section/div/div/div/div[1]/div[2]/form/div/div[1]/div[2]/span")).getText());
	

	}

}
