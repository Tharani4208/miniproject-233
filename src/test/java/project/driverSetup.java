package project;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class driverSetup {
	static WebDriver driver;
	static Properties prop = new Properties();
	public static WebDriver getWebDriver() {
		Scanner sc =new Scanner(System.in);
		System.out.println("Please select the browser:\n1.Chrome\n2.Firefox");
		String browser = sc.next();
		sc.close();
		try {
			FileInputStream fin = new FileInputStream("Property.properties");
			prop.load(fin);
			fin.close();
		} catch (Exception e) {
			System.out.println("File Not Found");
		}
		if(browser.equalsIgnoreCase("chrome"))
		{
		    driver = new ChromeDriver();	
		    driver.get(prop.getProperty("baseUrl"));
		    return driver;
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
		    driver = new FirefoxDriver();	
		    driver.get(prop.getProperty("baseUrl"));
		    return driver;
		}
				else 
		{
			System.out.println("Enter the valid Browser");
			return driver;
		}
	}
	
}
