package tests;


	import java.io.IOException;
	import java.util.Properties;

	import org.openqa.selenium.By;

import frameWork.CommonMethods;
import frameWork.CompanySearchOperator;
import frameWork.CompanySearchOptions;
import frameWork.Data;
import frameWork.DataUtil;
import frameWork.EventMetods;
import frameWork.UtilityMethods;
import modules.companies;

	

	public class ModifyCompany {
		static Properties configProperties;
		public static void main(String[] args) {
			CommonMethods.closeProcesses();
			try {
				String propFilePath = Data.Sysytrm_Path+"\\Config\\MasterData.properties";
				configProperties = DataUtil.getpropertesFile(propFilePath);
			} catch (IOException e) {
				System.out.println("Unable to read the data from propertie file. Exception caught as : "+ e.getMessage());
				System.exit(0);
			}
			
			CommonMethods.launchApplication(configProperties.getProperty("browser"), configProperties.getProperty("url"));
			CommonMethods.login(configProperties.getProperty("username"), configProperties.getProperty("password"));
		
			companies.naviagte_to_companies_page();
			UtilityMethods.staticWait(5000);
			String companyName = "Company104854115";
			boolean performFilter = companies.performFilterSearch(CompanySearchOptions.NAME, CompanySearchOperator.EQUALS, companyName);
			
			if (performFilter) {
				EventMetods.click_Btn(By.xpath("//button[@class='ui icon button']/i[contains(@class,'edit')]"), "Click on Edit Icon");
				
			}else {
				System.out.println("Search did not return any records. Company not found.");
			}
			
			CommonMethods.logout();
			CommonMethods.close_Browsers();
		}

	}


