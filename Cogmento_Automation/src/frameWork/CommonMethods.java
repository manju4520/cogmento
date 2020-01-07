package frameWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import modules.companies;

public class CommonMethods {
	@DataProvider
	public Object[][] browserData(){
		Object[][] data = new Object[2][2];
		data[0][0] = "chrome";
		data[0][1] = "http://www.google.com";
		data[1][0] = "firefox";
		data[1][1] = "http://www.google.com";
		
		return data;
		
	}
	@Test(dataProvider = "browserData")
	public static void launchApplication(String browser, String url) {

		try {

			browser = (browser == null) ? "chrome" : browser;// ternary oprator or conditiol operator

			switch (browser.toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", Data.chrome_Path);
				Data.driver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", Data.chrome_Path);
				Data.driver = new FirefoxDriver();
				break;
			case "edge":
				System.setProperty("webdriver.edge.driver", Data.chrome_Path);

				Data.driver = new EdgeDriver();

				break;

			default:
				System.out.println("the browser :" + browser + "invalid");
				System.exit(0);
				break;
			}

			Data.driver.get(url);
			Data.driver.manage().window().maximize();
			Data.driver.manage().timeouts().implicitlyWait(Data.IMPLICIT_TIME_OUT, TimeUnit.SECONDS);
			

		} catch (SessionNotCreatedException e) {
			System.out.println("Unanble to launch application" + e.getMessage());
		} catch (IllegalStateException e) {
			System.out.println("Unanble to launch application" + e.getMessage());
		} 
		try {
			Data.driver.findElement(By.name("email"));
			System.out.println("Application is succeefully launched and navigated to login page");

		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("application is not navigated to login page.unable to continue with execution"+e.getMessage());
		

		}
	}
	public static void close_Browsers() {
		try {
			Data.driver.close();
			Data.driver.quit();
			System.exit(0);
		} catch (WebDriverException wde) {
			System.out.println("The browser Alredy closed"+wde.getMessage());
			
		
		}
	}
	public static void login(String username,String password) {
		EventMetods.enter_value_in_text_field(By.name("email"), username, "Enter User Name");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EventMetods.enter_value_in_text_field(By.name("password"), username, "Enter Passworde");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EventMetods.click_Btn(By.xpath("//*[@id=\"ui\"]/div/div/form/div/div[3]"), "Login  on Button");
		
		if (EventMetods.verify_element_exist(By.xpath("//span[text()='Home']"))) {
			System.out.println("Application is navigated to login page");
		}else {
				System.out.println("Application is not naviate to login page");
			   CommonMethods.close_Browsers();
			   System.exit(0);
			
		}
	}
	
	public static void selectCountryFromList(By locatorForList, By locatorForCountry){
		EventMetods.click_Btn(locatorForList, "Click on Country list to populate countries");
		
		EventMetods.click_Btn(locatorForCountry, "Click on COuntry list to populate countries");
	}
	public static void closeProcesses(){
		String[] allProcesses = {"chrome.exe","firefox.exe","iexplore.exe","microsoftedge.exe","chromedriver.exe","geckodriver.exe","IEDriverServer.exe","MicrosoftWebDriver.exe"};
		for (String processName : allProcesses){
			try {
				Runtime.getRuntime().exec("taskkill /F /IM "+ processName);
			} catch (IOException e1) {
				
			}
		}
	}

	public static void selectFutureDateFromCalender(Date dt){
		HashMap<String, String> dateParts = UtilityMethods.getDateParts(dt);
		String monthYear = dateParts.get("MONTH_NAME")+" "+dateParts.get("YEAR");
		
		
		while (true){
			
			boolean isMonthYearExist = EventMetods.verify_element_exist(By.xpath("//div[text()='"+monthYear+"']"));
			
			if (isMonthYearExist){
				String dayXPath = "//div[contains(text(),'"+dateParts.get("MONTH_NAME")+"')]/following::p[text()='"+dateParts.get("DAY_NUMBER")+"']";
				EventMetods.click_Btn(By.xpath(dayXPath), "Click on Day Link");
				break;
			} else{
				EventMetods.click_Btn(By.xpath("//span[@role='button' and @aria-label='Next Month']"), "Click on Next Month");
			}
			
		}
	}
	public static void logout(){
		EventMetods.click_Btn(By.xpath("//i[contains(@class,'settings icon')]/parent::div"), "Click on Settings icon");
		EventMetods.click_Btn(By.xpath("//span[text()='Log Out']"), "Click on Logout Link");
		
		if (EventMetods.verify_element_exist(By.name("email"))){
			System.out.println("user logged out successfully.");
		} else{
			System.out.println("User was not logged out.");
			CommonMethods.captureScreenShot("logout");
		}
	}
   public static void captureScreenShot(String imageName){
		
		UtilityMethods.createFolder(Data.Sysytrm_Path+"\\Screenshots");
		String imagePath = Data.Sysytrm_Path+"\\Screenshots\\"+imageName+".png";
		
		try {
			Files.move(((TakesScreenshot) Data.driver).getScreenshotAs(OutputType.FILE), new File(imagePath));
		} catch (IOException e) {
			System.out.println("Unable to save the image : " + imagePath);
		}
		
	}

	/*
	 * public static void selectCountryFromList(By locatorForList, By
	 * locatorForCountry){ EventMetods.click_Btn(locatorForList,
	 * "Click on Country list to populate countries");
	 * 
	 * EventMetods.click_Btn(locatorForCountry,
	 * "Click on COuntry list to populate countries"); }
	 */
}

	
	/*public static void takeScreenShots(String image) {

		TakesScreenshot ts = (TakesScreenshot) Data.driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + image + ".png");
		// System.getProperty("user.dir")+"\\ScreenShots\\"+image+".png"
		try {
			Files.copy(file, dest);

		} catch (IOException e) {
			// TODO: handle exception
		}

	}

	

	}

}*/
