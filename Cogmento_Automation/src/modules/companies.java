package modules;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import frameWork.CommonMethods;
import frameWork.CompanySearchOperator;
import frameWork.CompanySearchOptions;
import frameWork.Data;
import frameWork.EventMetods;
import frameWork.UtilityMethods;

  public class companies {
	
		public static void naviagte_to_companies_page() {
			EventMetods.click_Btn(By.xpath("//span[text()='Companies']"), "Click on Companies link");
			
			if (EventMetods.verify_element_exist(By.xpath("//div[text()='Companies']"))){
				System.out.println("APplication is navigated to companies page after clicking on companies link.");
			} else {
				System.out.println("Applicaiton is NOT navigated to COmpanies page even after clicking on Companies link.");
				System.exit(0);
			}
				
				}
        
  
   public static void navigate_new_companies_page() {
	   
	   
	   EventMetods.click_Btn(By.xpath("//button[contains(@class,'linkedin') and contains(text(),'New')]"), "Click on New Company Button");
		
		if (EventMetods.verify_element_exist(By.xpath("//div[text()='Create new Company']"))){
			System.out.println("APplication is navigated to Create New Company page.");
		} else {
			System.out.println("Applicaiton is NOT navigated to Create New Company page even after clicking on New Company Button.");
			System.exit(0);
		}
	}
	 
	 
   
   public static void selectAccessLevel(String accessLevel){
		String buttonText = Data.driver.findElement(By.xpath("//label[text()='Access']/following-sibling::div/descendant::button")).getText();
		
		switch (accessLevel.toLowerCase()) {
		case "public":
			
			if (buttonText.contains("Private")){
				EventMetods.click_Btn(By.xpath("//label[text()='Access']/following-sibling::div/descendant::button"), "Click on Access Button");
			}
			break;

		default:
			
			if (buttonText.contains("Public")){
				EventMetods.click_Btn(By.xpath("//label[text()='Access']/following-sibling::div/descendant::button"), "Click on Access Button");
			}
			break;
		}
	}
	
   public static String createCompany(HashMap<String, String> tcData){
		String companyName = tcData.get("COMPANY_NAME") + UtilityMethods.getCurrentTimeStamp();
		EventMetods.enter_value_in_text_field(By.xpath("//label[text()='Name']/following-sibling::div/descendant::input"), companyName, "Enter Company Name");
		
		companies.selectAccessLevel(tcData.get("ACCESS_LEVEL"));
		
		EventMetods.enter_value_in_text_field(By.name("url"), tcData.get("WEBSITE"), "Enter company URL");
		EventMetods.enter_value_in_text_field(By.name("address"), tcData.get("ADDRESS"), "Enter company Address");
		EventMetods.enter_value_in_text_field(By.name("city"), tcData.get("CITY"), "Enter City");
		EventMetods.enter_value_in_text_field(By.name("state"), tcData.get("STATE"), "Enter State Code");
		EventMetods.enter_value_in_text_field(By.name("zip"), UtilityMethods.formatNumber(tcData.get("POSTAL_CODE")), "Enter Postal Code");
		CommonMethods.selectCountryFromList(By.name("country"),By.xpath("//span[text()='"+tcData.get("COUNTRY")+"']"));
		CommonMethods.selectCountryFromList(By.name("hint"),By.xpath("(//span[text()='"+tcData.get("COUNTRY")+"'])[2]"));
		EventMetods.enter_value_in_text_field(By.xpath("//input[@name='value' and @placeholder='Number']"),UtilityMethods.formatLongNumber(tcData.get("PHONE")),"Enter phone number.");
		
		EventMetods.click_Btn(By.xpath("//button[text()='Save']"), "Click on Save Button after entering the Company Info");
		
		UtilityMethods.staticWait(5000);

		if (EventMetods.verify_element_exist(By.xpath("//div[text()='"+companyName+"']"))){
			System.out.println("Company is created successfully.");
			CommonMethods.captureScreenShot("CreateCompany_passed");
		}else{
			System.out.println("Company is not created successfully.");
			
			CommonMethods.captureScreenShot("CreateCompany_failed");
		}
		
		return companyName;
		
	}
	
	public static void verifyCompanyExists(String companyName){
		while (true){
			
			if (EventMetods.verify_element_exist(By.xpath("//td[text()='"+companyName+"']"))){
				System.out.println("Company : " +companyName+ " is found." );
				break;
			}else {				
				String rightLinkText = Data.driver.findElement(By.xpath("//i[contains(@class,'right')]/parent::a")).getAttribute("class");
				
				if (rightLinkText.endsWith("disabled")){
					System.out.println("Company : " +companyName+ " is not found even after searching till last page.");
					break;
				}else {
					EventMetods.click_Btn(By.xpath("//i[contains(@class,'right')]/parent::a"), "Click Right arrow to display next page companies");
				}
			}
			
		}
	}

	
	public static boolean verifyCompanySearchResult_byName_Equality(String companyName) {
		boolean isCompanyNameMatched = true;
		
		List<WebElement> allCompanyNameElements = Data.driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
		
		for (WebElement companyElement:allCompanyNameElements) {
			if (!companyElement.getText().trim().equalsIgnoreCase(companyName)) {
				isCompanyNameMatched = false;
				break;
			}
		}
		
		return isCompanyNameMatched;
	}
	
	public static boolean performFilterSearch(CompanySearchOptions searchOption, CompanySearchOperator searchOperator, String searchValue) {
		//Select Search Option.
		if (showFilters()) {
			EventMetods.click_Btn(By.xpath("//div[@name='name']/input"), "Click on Search Option to show the options");
			switch (searchOption) {
				case NAME:
					EventMetods.click_Btn(By.xpath("//div[@name='name']/input/following::span[text()='Name']"), "Select Name under Search Options");
					break;
				case WEBSITE:
					EventMetods.click_Btn(By.xpath("//div[@name='name']/input/following::span[text()='Website']"), "Select Website under Search Options");
					break;
					
				case ADDRESS:
					EventMetods.click_Btn(By.xpath("//div[@name='name']/input/following::span[text()='Address']"), "Select Address under Search Options");
					break;
				
				case INDUSTRY:
					EventMetods.click_Btn(By.xpath("//div[@name='name']/input/following::span[text()='Industry']"), "Select Industry under Search Options");
					break;
				default:
					EventMetods.click_Btn(By.xpath("//div[@name='name']/input/following::span[text()='Tags']"), "Select Tags under Search Options");
					break;
			}
			
			//Select the Search Operator.
			EventMetods.click_Btn(By.xpath("//div[@name='operator']/div"), "Click on Opeator list to show all operators");
			
			switch (searchOperator) {
			
				case EQUALS:
					EventMetods.click_Btn(By.xpath("//span[text()='Equals']"), "Select equals under oprator.");
					break;
					
				case CONTAINS:
					EventMetods.click_Btn(By.xpath("//span[text()='Contains']"), "Select Contains under oprator.");
					break;
					
				case STARTS_WITH:
					EventMetods.click_Btn(By.xpath("//span[text()='Starts With']"), "Select Starts with under oprator.");
					break;
					
				default:
					EventMetods.click_Btn(By.xpath("//span[text()='Ends With']"), "Select Ends with under oprator.");
					break;
				}
			
			EventMetods.enter_value_in_text_field(By.xpath("//input[@name='value']"), searchValue, "Enter search Option.");
			
			EventMetods.click_Btn(By.xpath("//button/i[contains(@class,'search')]"), "Click on Search Button after entering the search criteria.");
			
			if (EventMetods.verify_element_exist(By.xpath("//td[contains(text(),'"+searchValue+"')]"))) {
				return true;
				
			}else {
				return false;
			}
			
		} else {
			System.out.println("Filter options are not populated.");
			return false;
		}
		
	}
	
	
	
	private static boolean showFilters() {

		EventMetods.click_Btn(By.xpath("//button[text()='Show Filters']"), "Click on Show Filters button to show filter options");
		
		if (EventMetods.verify_element_exist(By.xpath("//div[text()='Filter']"))) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	 
   }
	
 