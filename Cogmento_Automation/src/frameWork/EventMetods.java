package frameWork;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class EventMetods {
	 // "nandasele69@gmail.com"
	//"Test@1234"
	  /**
	   * 
	   * @param locator
	   * @param input
	   * @param stepname
	   */
	  public static void enter_value_in_text_field(By locator,String input,String stepname) {
		  
		  try {
			  Data.driver.findElement(locator).sendKeys(input);
			  
		} catch (NoSuchElementException nsee) {
			System.out.println(stepname+"unable to identifie element"+nsee.getMessage());
			
		}catch(ElementNotInteractableException enie) {
			
			System.out.println(stepname+"your given element not visible and not displyed"+enie.getMessage());
		}catch(IllegalArgumentException iae) {
			
			System.out.println(stepname+"your given wrong input"+iae.getMessage());
		}
	  }
	  
	  
	  public static void click_Btn(By locator, String stepname) {
		  try{
			  hilight_the_element(Data.driver.findElement(locator));
				Data.driver.findElement(locator).click();
			} catch (NoSuchElementException nse) {
				System.out.println(stepname + " ; Unable to click the button as button could not be found.");
				System.exit(0);
			} catch (ElementNotInteractableException eie){
				try{
					JavascriptExecutor jse = (JavascriptExecutor) Data.driver;
					jse.executeScript("arguments[0].click();", Data.driver.findElement(locator));
				} catch(Exception e){
					System.out.println(stepname + " ; Unable to click the button as button either element not visible or disabled.");
					System.exit(0);
				}
				
			} 
	  
	}
	  
	  public static boolean verify_element_exist(By locator) {
		  try {
			  Data.driver.findElement(locator);
			  return true;
		} catch (NoSuchElementException nsee) {
			return false;
			
		}
		  
	       
	}
	  public static void hilight_the_element(WebElement element) {
		  
		  JavascriptExecutor js = (JavascriptExecutor) Data.driver;  
		  js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",element);
	
	}
} 
	
