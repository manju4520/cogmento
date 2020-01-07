package frameWork;

import org.openqa.selenium.WebDriver;

public class Data {

	public static WebDriver driver;
	
	public static final String Sysytrm_Path=System.getProperty("user.dir");
	public static final String chrome_Path=Sysytrm_Path+"\\Drivers\\chromedriver.exe";
	public static final String firefox_Path=Sysytrm_Path+"\\Drivers\\geckodriver.exe";
	public static final String edge_Path=Sysytrm_Path+"\\Drivers\\IEDriverServer.exe";
	
	public static final int IMPLICIT_TIME_OUT=20;
	
	
	
	
	
	
}
