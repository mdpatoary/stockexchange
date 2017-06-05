package MainPackage;

import org.testng.annotations.Test;

import ControllerPackage.HomePageController;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class BaseTest {

	protected WebDriver driver = null;
	protected HomePageController homePageController = null;




	@BeforeMethod
	public void beforeMethod() {

		DesiredCapabilities capa = new DesiredCapabilities();
		capa.setCapability(MobileCapabilityType.PLATFORM_NAME,MobilePlatform.ANDROID );
		capa.setCapability(MobileCapabilityType.DEVICE_NAME,"Google Nexus 4" );
		capa.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		capa.setCapability(MobileCapabilityType.PLATFORM_VERSION,"5.0.0");
		capa.setCapability(MobileCapabilityType.UDID,"192.168.44.101:5555" );


		try {
			setDriver(new AndroidDriver (new URL("http://0.0.0.0:4723/wd/hub"),capa));
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}

		getDriver().get("http://m.nasdaq.com");
	}

	@Test
	public void testing(){
		Assert.assertEquals(driver.getTitle(), "Nasdaq Stock Market | Stock Quotes & Stock Exchange News");
		System.out.println(driver.getTitle());
	}
	@AfterMethod
	public void afterMethod() {


		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(driver != null){
			driver.quit();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver  driver) {
		this.driver = driver;
	}
	

	public HomePageController getHomePageController() {
		setHomePageController(this.homePageController);
		return homePageController;
	}

	public void setHomePageController(HomePageController homePageController) {
		this.homePageController = new HomePageController(getDriver());
	}
}
