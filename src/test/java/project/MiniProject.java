package project;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class MiniProject {
	Excel exl = new Excel();
	WebDriver driver=null;
	String parentId=null;
	public WebDriver createDriver() {
		driver=driverSetup.getWebDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();
	    return driver;
	}
	public String loadingWebpage() {
		if(driverSetup.prop.getProperty("baseUrl").equals(driver.getCurrentUrl())) {
			return "web page is loaded";
		}
		else {
			return "web page is not loaded";
		}
	}
	public void searchBox() throws IOException{
		exl.dataExtract();
		WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(exl.search);
        searchBox.submit();
       
	}
	public String addProduct() {                                                                    
		 driver.findElement(By.cssSelector("#container > div > div._36fx1h._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(1) > div")).click();
		 Set<String> handle = new HashSet<String>();
         handle = driver.getWindowHandles();
        
         List<String> handlee = new ArrayList<String>(handle);
       	 
         String parentId = handlee.get(0);
         String childId = handlee.get(1);
         driver.switchTo().window(childId);

          WebElement element1 = driver.findElement(By.cssSelector("li>button._2KpZ6l"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element1);
          element1.click();
          String price1 = driver.findElement(By.cssSelector("#container > div > div._1Er18h > div > div > div._1YokD2._3Mn1Gg.col-4-12._78xt5Y > div._1AtVbE.col-12-12 > div > div > div > div._3LxTgx > div > div.z4Ha90 > span > div > div > div.z4Ha90 > span")).getText();
         String pricee = "The Price is: "+price1 ;
          driver.close();
//        ==================================================second product====================================================
          driver.switchTo().window(parentId);
          driver.findElement(By.cssSelector("#container > div > div._36fx1h._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(2) > div")).click();
         
          
          Set<String> handle1 = new HashSet<String>();
	      handle1 = driver.getWindowHandles();
	      List<String> handleL = new ArrayList<String>(handle1);
	      String parentID = handleL.get(0);
	      String childID = handleL.get(1);
          
	      
	      driver.switchTo().window(childID);
          WebElement element2 = driver.findElement(By.cssSelector("li>button._2KpZ6l"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element2);
          element2.click();
          String price2 = driver.findElement(By.cssSelector("#container > div > div._1Er18h > div > div > div._1YokD2._3Mn1Gg.col-4-12._78xt5Y > div._1AtVbE.col-12-12 > div > div > div > div._3LxTgx > div > div.z4Ha90 > span > div > div > div.z4Ha90 > span")).getText();
          String pricee2 = "The Price is: "+price2;
          driver.navigate().refresh();
          return pricee + "\n" + pricee2;
	}
 	
	public void closeDriver() {
		 driver.quit();
	}

	public static void main(String[] args) throws IOException  {
		MiniProject proj = new MiniProject();
		//Launch the browser using the configuration settings Chrome/Firefox
		proj.createDriver();
		//Check if the home page of the application is loaded.
		String loadingWebpage = proj.loadingWebpage();
		//In the search box enter the search criteria “Home appliances”.
		proj.searchBox();
        //Click on the add to the cart.
        String price = proj.addProduct();   
        PrintStream ps = new PrintStream(new File("C:\\Users\\2318295\\eclipse-workspace\\MINIPROJECT\\Result.txt"));
        System.setOut(ps);
        try {
        	ps.print(loadingWebpage);
        	System.out.println();
        	ps.print(price);
        	
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        //Closing Browser
        proj.closeDriver();
          
         

	}
}

