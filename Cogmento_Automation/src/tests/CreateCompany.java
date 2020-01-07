package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import frameWork.CommonMethods;
import frameWork.Data;
import frameWork.DataUtil;
import frameWork.EventMetods;
import frameWork.UtilityMethods;
import modules.companies;

public class CreateCompany {
	static Properties propfile;
	public static void main(String[] args) throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
	
		
		String dataFilePath = "E:\\Selenium\\Cogmento_Automation\\Data\\CompaniesData.xlsx";
		
        String sheetName = "CreateCompany";
		
		try {

			String propFilePath="E:\\Selenium\\SeleniumWithJava\\Confiq\\MasterData.properties";
			
			propfile=DataUtil.getpropertesFile(propFilePath);
		} catch (IOException e) {
			System.out.println("Unable to read the data from propertie file. Exception caught as : "+ e.getMessage());
			System.exit(0);
		}
		

		CommonMethods.launchApplication(propfile.getProperty("browser"), propfile.getProperty("url"));
		Data.driver.findElement(By.name("email")).sendKeys("nandasele69@gmail.com");
		Data.driver.findElement(By.name("password")).sendKeys("Test@1234",Keys.ENTER);
		
	
		//CommonMethods.login(propfile.getProperty("username"), propfile.getProperty("password"));

		companies.naviagte_to_companies_page();
		companies.navigate_new_companies_page();
		
		HashMap<String, String> tcData = DataUtil.getTestCaseData(dataFilePath, sheetName, "CC_01");
		
		enterCompanyInfo(tcData);
		
	}
	
	
	
	public static void enterCompanyInfo(HashMap<String, String> tcData){
		
		EventMetods.enter_value_in_text_field(By.xpath("//label[text()='Name']/following-sibling::div/descendant::input"), tcData.get("COMPANY_NAME"), "Enter Company Name");
		
		companies.selectAccessLevel(tcData.get("ACCESS_LEVEL"));
		
		EventMetods.enter_value_in_text_field(By.name("url"), tcData.get("WEBSITE"), "Enter company URL");
		EventMetods.enter_value_in_text_field(By.name("address"), tcData.get("ADDRESS"), "Enter company Address");
		EventMetods.enter_value_in_text_field(By.name("city"), tcData.get("CITY"), "Enter City");
		EventMetods.enter_value_in_text_field(By.name("state"), tcData.get("STATE"), "Enter State Code");
		EventMetods.enter_value_in_text_field(By.name("zip"), UtilityMethods.formatNumber(tcData.get("POSTAL_CODE")), "Enter Postal Code");
		CommonMethods.selectCountryFromList(By.name("country"),By.xpath("//span[text()='"+tcData.get("COUNTRY")+"']"));
		CommonMethods.selectCountryFromList(By.name("hint"),By.xpath("(//span[text()='"+tcData.get("COUNTRY")+"'])[2]"));
		EventMetods.enter_value_in_text_field(By.xpath("//input[@name='value' and @placeholder='Number']"),UtilityMethods.formatNumber(tcData.get("PHONE")),"Enter phone number.");
	}
	
	
	
		
	}
  
		
		
		
		
