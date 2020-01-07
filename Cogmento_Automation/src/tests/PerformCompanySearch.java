package tests;


	import java.io.IOException;
	import java.util.List;
	import java.util.Properties;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebElement;

import frameWork.CommonMethods;
import frameWork.CompanySearchOperator;
import frameWork.CompanySearchOptions;
import frameWork.Data;
import frameWork.DataUtil;
import frameWork.UtilityMethods;
import modules.companies;

	

	public class PerformCompanySearch {
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
				System.out.println("Search has returned results.");
				
				if (companies.verifyCompanySearchResult_byName_Equality(companyName)) {
					System.out.println("Results are as per the selection criteria.");
				} else {
					System.out.println("Results are not returned as per the selection criteria.");
				}
			} else {
				System.out.println("Search is not returned any records.");
			}
			
			CommonMethods.logout();
			CommonMethods.close_Browsers();

		}
		
		
		
		
	}


