package Appiumpackage1;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException {
		
		 service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\LG\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
				service.start();
		 
		 
				
				UiAutomator2Options options = new UiAutomator2Options();
				options.setDeviceName("Pixel 5");
				options.setApp("F:\\eShaafi Automation\\git\\repository\\Patienapp\\src\\test\\java\\Resources\\app-minApi21-development-debug (3).apk");
				//options.setCapability("appPackage", "com.eshaafi.patient.consultation");
				options.setCapability("appWaitActivity","com.eshaafi.patient.consultation.ui.auth.AuthActivity");
				//options.setCapability("appActivity", "com.eshaafi.patient.consultation.ui.auth.AuthActivity");
				 driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
				 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	//@AfterClass
	//public void TearDown() {
		//driver.quit();
		//service.stop();
	//}

}
