package frameWork;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNg {

	@Test
	public static void method1() {
		System.out.println("prabhas");
	}

	@AfterMethod
	public static void method2() {
		System.out.println("darling");
	}

	@BeforeMethod
	public static void method3() {
		System.out.println("munna");

	}

}
