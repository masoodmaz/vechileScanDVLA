package uk.co.dvla.scandirnselenium;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import uk.co.dvla.model.VehicleBean;
import uk.co.dvla.services.vechileinfoservice.VehicleInfoService;
import uk.co.dvla.util.ReadExcel;

public class VehicleInfoServiceTest {

	/**
	 * This test uses webdriver to launch the <dvlaURL> in Chrome 
	 * Uses the Vehicle information read from the excel file 
	 * Uses AssertEquals to compare values iteratively 
	 */

	// Global domain values
	private WebDriver driver;
	private VehicleInfoService page;
	private ReadExcel excelUtil;

	private String dvlaURL = "https://www.gov.uk/get-vehicle-information-from-dvla";
	private static final String filePath = "C:\\Users\\masoo\\eclipse-workspace\\filescanautomation\\src\\main\\resources\\Vechiles.xlsx";
	private int timeOutPeriod = 5;
	private int sleepMillisec = 2000;
	
	List<VehicleBean> vehicleList = null;

	@Before
	public void setup() throws Throwable {

		System.setProperty("webdriver.chrome.driver", "C:\\Temp\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(timeOutPeriod, TimeUnit.SECONDS);
		new WebDriverWait(driver, timeOutPeriod);
		excelUtil = new ReadExcel();
		page = new VehicleInfoService();
		page.setDriver(driver);
		driver.get(dvlaURL);
		vehicleList = excelUtil.getAllVehiclesFromFile(filePath);
				

	}

	@After
	public void tearDown() {
		if (driver != null) {
			//driver.quit();
		}
	}

	
	/**
	 * This test uses webdriver to launch the <dvlaURL> in Chrome 
	 * Idenities the Page Elements using XPATH using Chrome Inspect tool
	*  Uses the Vehicle information read from the excel file 
	 * Uses AssertEquals to compare values iteratively 
	 */
	@Test
	public void testVehicleInformation() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement startButtonLink = driver.findElement(By.xpath("//*[@id=\"get-started\"]/a"));
		
		if (startButtonLink.isDisplayed()) {
			startButtonLink.click();
		}
		
		for (int i = 0; i <= vehicleList.size(); i++) {
			
		
		VehicleBean vehicle = vehicleList.get(i);



		waitForPageLoaded();

		WebElement textboxForReg = driver
				.findElement(By.xpath("html/body/main/form/div/div/div[2]/fieldset/div/input"));
		wait.until(ExpectedConditions.visibilityOf(textboxForReg));

		if (textboxForReg.isDisplayed()) {
			textboxForReg.sendKeys(vehicle.getReg());
		}
		WebElement continueButton = driver
				.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/div[2]/fieldset/button"));
		Thread.sleep(sleepMillisec);
				
		continueButton.click();

		waitForPageLoaded();
		WebElement vehicle_make = driver.findElement(By.xpath("//*[@id=\"pr3\"]//li[2]//strong"));

		wait.until(ExpectedConditions.visibilityOf(vehicle_make));

		WebElement vehicle_colour = driver.findElement(By.xpath("//*[@id=\"pr3\"]//li[3]//strong"));
		System.out.println(vehicle.getMake()+" Excel--Web "+ vehicle_make.getText());
		assertEquals(vehicle.getMake(), vehicle_make.getText());
		assertEquals(vehicle.getColour(), vehicle_colour.getText());
		Thread.sleep(sleepMillisec);
		driver.navigate().back();
		
		}
	}
	
	/**
	* Method used to wait for a new Page to be loaded
	* Checks the document.readyState 
	*/

	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}
}
