package ModulePackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.events.WebDriverEventListener;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class HomePage extends ModelBase {

	protected WebElement searchfield = null;
	protected WebElement searchBtn = null;
	protected WebElement companyLabel = null;
	protected WebElement sPriceElement = null;
	protected WebElement yearlyHigh = null;
	protected WebElement yearlyLow = null;
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public WebElement getSearchField(){
		searchfield = driver.findElement(By.id("stock-symbol"));	
		return searchfield;
	}

	public WebElement getSearchBtn(){
		searchBtn = driver.findElement(By.id("stock-submit"));	
		return searchBtn;
	}

	public WebElement getCompanyName(String companyName){
		companyLabel = driver.findElement(By.xpath("//h1[contains(text(),"
				+ "'"
				+ companyName
				+ " Quote & Summary Data')]"));

		return companyLabel;
	}

	public WebElement getStockPrice(){
		sPriceElement = driver.findElement(By.xpath("//div[@class='last-sale padded-bottom displayIB paddingR5p']"));
		return sPriceElement;
	}

	public WebElement getYearlyHighElement(){

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		yearlyHigh = driver.findElement(By.xpath("//span[contains(text(),'52 Week High')]//following::li/span"));
		return yearlyHigh;
	}
	
	public WebElement getYearlyLowElement(){
		yearlyLow = driver.findElement(By.xpath("//span[contains(text(),'52 Week Low')]//following::li/span"));
		return yearlyLow;
	}


}
