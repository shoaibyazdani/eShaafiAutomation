package Appiumpackage1;

import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import static org.testng.AssertJUnit.assertEquals;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.netty.handler.timeout.TimeoutException;

public class BaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException {
		
		 service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\LG\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
				service.start();
		 
		 
				
				UiAutomator2Options options = new UiAutomator2Options();
			
				options.setDeviceName("Pixel 7");
				options.setCapability("appPackage", "com.eshaafi.patient.consultation");
				options.setCapability("appActivity", "com.eshaafi.patient.consultation.ui.auth.AuthActivity");
				options.setCapability("appWaitActivity", "com.eshaafi.patient.consultation.ui.auth.AuthActivity");
				

				options.setCapability(MobileCapabilityType.NO_RESET, true);
				options.setCapability(MobileCapabilityType.FULL_RESET, false);

				
				
				 driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
				 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	public void slotstate() {
		
		

		// Find the WebElement for the TextView
		WebElement textView = driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[2]/android.widget.TextView"));

		// Get the color of the text
		String textColor = textView.getCssValue("color");

		if (textColor.equals("rgba(87, 87, 87, 1)")) {
		    // The color is dark gray (#575757), perform the action for dark gray color
		    textView.click();
		} else if (textColor.equals("rgba(206, 206, 206, 1)")) {
		    // The color is gray (#CECECE), perform the action for gray color
		    // Tap on the next slot
		    WebElement nextSlot = driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[4]/android.widget.TextView"));
		    nextSlot.click();
		} else {
		    // Handle the case if the color is neither gray nor dark gray
		    System.out.println("Unknown Color");
		}




	}
	
	//Booking flow
	public void BookNow() {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	
	 	// Wait for the element to be visible
	 	WebElement BookNow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/book_appointments_button")));
	
	 	// Click on Book Now
	 	BookNow.click();
	 	
	 	
	 	 wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	 	 //Scroll down
	 	//driver.findElement (AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"SHA...\"));"));
	 	
	 // Wait for a short while to ensure elements are loaded
	 	
	     // Click on second Profile of Doctor
	     WebElement BookAppBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.Button")));
	    
	
	     // Click the element
	     BookAppBtn.click();
     
	    // wait and Select second slot from the list
	    WebElement slot = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[2]/android.widget.TextView")));

	    // Click on the slot
	    slot.click();
	    
	    

	    // Wait for "Proceed" button to be click able
	    WebElement proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.id("com.eshaafi.patient.consultation:id/proceed_button")));
	    proceedBtn.click();
	    
//	    String toastMessage= driver.findElement(By.xpath("(//android.widget.Toast)")).getAttribute( "name");
//	    
//	    AssertJUnit.assertEquals(toastMessage, "Please select slot first");
//	    
	    //Select Profile
	    WebElement Selectprofile = wait.until(ExpectedConditions.elementToBeClickable(
	             By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.ImageView[1]")));
	    Selectprofile.click();
	    

	    WebElement switchElement = wait.until(ExpectedConditions.elementToBeClickable(
	             By.id("com.eshaafi.patient.consultation:id/switch1")));
	     switchElement.click();
	     
	  // Click on Proceed Button
	     wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
	 	// Wait for the element to be visible
	 	WebElement ProceedBtn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/pay_now_button")));
	
	 	// Click on Book Now
	 	ProceedBtn1.click();
	 	
	 	//Click on Pay now Button
	 	wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
	 	// Wait for the element to be visible
	 	WebElement PayNowBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.Button")));
	
	 	// Click on Pay Now
	 	PayNowBtn.click();
 	
	 	WebElement Gotohome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.TextView[2]")));
	
	 	// Click on Pay Now
	 	Gotohome.click();
 	
 	
	
}


	public void checkwallet() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement Wallet = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@content-desc=\"Wallet\"]/android.widget.FrameLayout/android.widget.ImageView")));
		Wallet.click();
		
		// Find the element that displays the wallet balance
		WebElement walletBalanceElement = driver.findElement(By.id("com.eshaafi.patient.consultation:id/current_balance_textview"));

		 wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		// Get the text containing the wallet balance
		String walletBalanceText = walletBalanceElement.getText();

		// Output the wallet balance to the console
		System.out.println("Wallet Balance: " + walletBalanceText);

	}
		//Instant Call Flow
	public void instantcall () {
		
		// Click on the "Instant Call" button
    	WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement InstantCallBtn = driver.findElement(By.id("com.eshaafi.patient.consultation:id/guide"));
        InstantCallBtn.click();
   
     // Wait for the "Proceed" button to appear and then click it
           wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/proceed_button")));
        proceedButton.click();
        
      //Select Profile
        
	    WebElement Selectprofile = wait.until(ExpectedConditions.elementToBeClickable(
	             By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.ImageView[1]")));
	    Selectprofile.click();	    
        
     // Click on the switch element
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement switchElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/switch1")));
        switchElement.click(); 

        
        
        // Click on the "Pay Now" button
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement payNowButton = driver.findElement(By.id("com.eshaafi.patient.consultation:id/pay_now_button"));
        payNowButton.click();
        
        WebElement Gotohome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.TextView[2]")));

     	// Click on Pay Now
     	Gotohome.click();
    }
	//Login Flow
	public void Login(String phoneNumber, String otp) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

//	    // Wait for the "Create Account" button to be visible and click it
//	    WebElement createAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/create_account_button")));
//	    createAccountButton.click();

	    // Find and enter the phone number
	    WebElement phoneInput = driver.findElement(By.id("com.eshaafi.patient.consultation:id/phoneno_edittext"));
	    phoneInput.sendKeys(phoneNumber);

	    // Find and click the "Continue" button
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/create_account_button")));
	    continueButton.click();

	    // Enter the OTP
	    for (int i = 0; i < otp.length(); i++) {
	        WebElement otpField = driver.findElement(By.id("com.eshaafi.patient.consultation:id/otp" + (i + 1) + "_textview"));
	        otpField.sendKeys(String.valueOf(otp.charAt(i)));
	        
	        
	    }
	    System.out.println("Login Successful"); 
	}
	
	public void Logout() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement sidemenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/left_imageview")));
		sidemenu.click();
		
		WebElement logout = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/androidx.cardview.widget.CardView/android.view.ViewGroup[2]/android.widget.TextView"));
		
		logout.click();
		
		System.out.println("Logout Successful");
		
	}
	    
	//Allow Permission
	public void allowpermissions () {
	    
	 // Handle permissions dialogs
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        for (int i = 1; i <= 3; i++) {
            WebElement allowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_button")));
            allowButton.click();
            System.out.println("Permissions Allowed");
            
        }
        
	    }
        
        
        
        
      
	}
	
	//@AfterClass
	//public void TearDown() {
		//driver.quit();
		//service.stop();
	//}


