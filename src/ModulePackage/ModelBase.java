package ModulePackage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;

public class ModelBase {

	protected WebDriver driver;
	 public ModelBase(WebDriver driver){
		 this.driver = driver;
		 
	 }


}
