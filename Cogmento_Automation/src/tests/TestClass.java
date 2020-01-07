package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestClass {
@Test
public void TestMethod() {
	WebDriver driver = new ChromeDriver();
	driver.get("http://www.google.com");
}
}
