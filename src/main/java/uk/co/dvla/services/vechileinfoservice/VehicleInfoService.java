package uk.co.dvla.services.vechileinfoservice;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import uk.co.dvla.model.VehicleBean;
import uk.co.dvla.util.ReadExcel;


public class VehicleInfoService {
	
	private WebDriver driver;
	private WebDriverWait wait;

	public VehicleInfoService() {
	}
	
	public List<VehicleBean> getAllVehiclesFromFile(String filename){
		ReadExcel rd = new ReadExcel();
		List<VehicleBean> vehicles = rd.getAllVehiclesFromFile(filename);
		return vehicles;
		
	}
	
	

	//@FindBy(css = ".button")
	@FindBy(xpath="//*[@id=\"get-started\"]/a")
	public WebElement START_BUTTON_LINK;

	public void clickOnStartHyperLink() {
		wait.until(ExpectedConditions.visibilityOf(START_BUTTON_LINK));
		START_BUTTON_LINK.click();
	}
	//@FindBy(css = "#Vrm")
	@FindBy(xpath="html/body/main/form/div/div/div[2]/fieldset/div/input")
	public WebElement QUERY_TEXTAREA;

	@FindBy(css = "button[name='Continue']")
	public WebElement QUERY_SUBMIT_BUTTON;

	@FindBy(css = ".heading-large")
	public WebElement VERIFICATION_QUESTION;

	@FindBy(xpath = "//*[@id=\"pr3\"]//li[2]//strong")
	public WebElement VEHICLE_MAKE;

	@FindBy(xpath = "//*[@id=\"pr3\"]//li[3]//strong")
	public WebElement VEHICLE_COLOUR;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	

}
