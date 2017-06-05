package ControllerPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import ModulePackage.HomePage;
import io.appium.java_client.android.AndroidDriver;

public class HomePageController extends ControllerBase{

	public HomePage homepage = null;
	

	public HomePageController(WebDriver driver) {

		super(driver);
		homepage = new HomePage(driver);
	}

	public void searchCompany(String stockSymbol){
		homepage.getSearchField().sendKeys(stockSymbol);;
	}
	
	public void clickSearchBtn(){
		homepage.getSearchBtn().click();
	}
	public String getCompanyLabel(String companyName){
		return homepage.getCompanyName(companyName).getText();
	}
	public String getStockPrice(){
		return homepage.getStockPrice().getText();
	}
	public String return52WeeksYearlyHigh(){
		return homepage.getYearlyHighElement().getText();
	}
	public String return52WeeksYearlyLow(){
		return homepage.getYearlyLowElement().getText();
	}
	
}
