package ControllerPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;

public class ControllerBase {

	
	protected WebDriver driver;
	
	public ControllerBase(WebDriver driver) {
		this.driver = driver;
	}
}
