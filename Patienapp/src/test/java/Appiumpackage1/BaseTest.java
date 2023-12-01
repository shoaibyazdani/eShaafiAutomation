package Appiumpackage1;

import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import static org.testng.AssertJUnit.assertEquals;

import java.awt.Dimension;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.netty.handler.timeout.TimeoutException;

public class BaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException {
		
		 service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Shoaib\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
				service.start();
		 
		 
				
				UiAutomator2Options options = new UiAutomator2Options();
				options.setDeviceName("OPPO CPH2421");
//				options.setDeviceName("Pixel 7 pro");
				options.setCapability("appPackage", "com.eshaafi.patient.consultation");
				options.setCapability("appActivity", "com.eshaafi.patient.consultation.ui.auth.AuthActivity");
				options.setCapability("appWaitActivity", "com.eshaafi.patient.consultation.ui.auth.AuthActivity");
				options.setCapability("enableMultiWindows", true);
				options.setCapability("setWebContentsDebuggingEnabled", true);
				options.setChromedriverExecutable("//Users//Shoaib\\Downloads\\chromedriver_win32//chromedriver");
				
				//options.setCapability("autoWebview", true);
				//options.setCapability("androidPackage", "com.android.chrome");

				options.setCapability(MobileCapabilityType.NO_RESET, true);
				options.setCapability(MobileCapabilityType.FULL_RESET, false);

				
				
				 driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
				 //i have change this implicitlyWait from 30 to 5 for toast msg testing
				 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				 System.out.println("Eshaafi is now connected to Appium Server.");
				 
				 
	}
	
	public void newScenario() {
		// Click on the JAZZCASH button
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//	    WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.widget.RadioButton[@resource-id=\"com.eshaafi.patient.consultation:id/radio\"])[1]")));
//	    radioButton.click();
//	 // Click on the "Pay Now" button
//	    WebElement ProceedBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/pay_now_button")));
//	    ProceedBtn.click();
	   
		// Assuming 'driver' is your AppiumDriver instance
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// Wait for the title_textview element to have the text "Payment Method"
		WebElement titleTextView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/title_textview")));

		// Check if the title_textview element has the text "Payment Method"
		if ("Payment Method".equals(titleTextView.getText())) {
		    // If true, proceed to check for the WebView

		    // Perform actions that trigger the WebView to load (if needed)

		    // Wait for the number of available context handles to be at least 2 (native + web)
		    

		    // Switch to the "WEBVIEW" context
		    Set<String> contextHandles = driver.getContextHandles();
		    for (String contextHandle : contextHandles) {
		        if (contextHandle.startsWith("WEBVIEW")) {
		            driver.context(contextHandle);
		            System.out.println(contextHandles);
		            
		            break;
		        }
		    }

		    // Now you are in the "WEBVIEW" context, perform actions within the WebView
		} else {
		    // Handle the case where the title_textview does not have the expected text
		    System.out.println("Title is not 'Payment Method'");
		}
		
	}

		
//	    // Click on the Button by ID
//	    WebElement buttonById = driver.findElement(By.id("pay"));
//	    buttonById.click();
//
//
//	    // Find the input field CustomerName by ID and send keys "Shoaib"
//	    WebElement customerNameInput = driver.findElement(By.id("CustomerName"));
//	    customerNameInput.sendKeys("Shoaib");
//
//	    // Find the input field cardEmailAddress and send keys "shoaibyazdani7@gmail.com"
//	    WebElement cardEmailAddressInput = driver.findElement(By.id("cardEmailAddress"));
//	    cardEmailAddressInput.sendKeys("shoaibyazdani7@gmail.com");
//	}

	//Login Flow
		public boolean Login(String phoneNumber, String otp) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		    // Find and enter the phone number
		    WebElement phoneInput = driver.findElement(By.id("com.eshaafi.patient.consultation:id/phoneno_edittext"));
		    phoneInput.sendKeys(phoneNumber);
		    System.out.println("Entring Phone No.");
		    

		    // Continue button is enabled, proceed
		    WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/create_account_button")));
		    
		    continueButton.click();
		    
		    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		    System.out.println("Entring OTP");
		   
		    // Enter the OTP
		    for (int i = 0; i < otp.length(); i++) {
		        WebElement otpField = driver.findElement(By.id("com.eshaafi.patient.consultation:id/otp" + (i + 1) + "_textview"));
		        otpField.sendKeys(String.valueOf(otp.charAt(i)));
		    }

		    // Check if OTP is correct (5 characters are expected)
		    if (otp.equals("999999")) {
		        // Simulate login by checking if the home screen elements are visible after entering OTP
		        WebElement homeScreenElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/guide")));

		        // Add additional assertions or actions for successful login
		        System.out.println("Login Successful ");
		        return true;
		    } else {
		        // Fetch the error message for incorrect OTP
		        WebElement errorMessage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/error_textview")));
		        String errorText = errorMessage1.getText();
		        System.out.println("Error Message: " + errorText);
		        return false;
		    }
		}
		
//		***************************************** SIGNUP Flow *******************************************************************
		
				public boolean Signup(String phoneNumber, String otp) {
					
				    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

				    // Find and enter the phone number
				    WebElement phoneInput = driver.findElement(By.id("com.eshaafi.patient.consultation:id/phoneno_edittext"));
				    phoneInput.sendKeys(phoneNumber);
				    System.out.println("Entring Phone No.");
				    

				    // Continue button is enabled, proceed
				    WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/create_account_button")));
				    
				    continueButton.click();
				    
				    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				    System.out.println("Entring OTP");
				   
				    // Enter the OTP
				    for (int i = 0; i < otp.length(); i++) {
				        WebElement otpField = driver.findElement(By.id("com.eshaafi.patient.consultation:id/otp" + (i + 1) + "_textview"));
				        otpField.sendKeys(String.valueOf(otp.charAt(i)));
				    }
				    //This method is called to filll the data in signup form
				    Sigupdata();
					
				    //Save Data
				    WebElement Save = driver.findElement(By.id("com.eshaafi.patient.consultation:id/save_button"));
				    Save.click();				  
				    System.out.println("Save Button Clicked");
				    
//				    // Check if OTP is correct (5 characters are expected)
				    if (otp.equals("999999")) {
				        // Simulate login by checking if the home screen elements are visible after entering OTP
				        WebElement homeScreenElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")));
//
				        // Add additional assertions or actions for successful login
				        System.out.println("Signup Successful ");
				        return true;
				        
				        
				    } else {
				        // Fetch the error message for incorrect OTP
				        WebElement errorMessage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/error_textview")));
				        String errorText = errorMessage1.getText();
				        System.out.println("Error Message: " + errorText);
				        return false;
				    }
				}
	
	
//				***************************************** Book Now Flow *******************************************************************

	
	public void BookNow(int n) {
		
		for (int i = 1; i <= n; i++)
		{
			
			Bookflow(i);
			
			 WebDriverWait    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    WebElement switchElement = wait.until(ExpectedConditions.elementToBeClickable(
	             By.id("com.eshaafi.patient.consultation:id/switch1")));
	     switchElement.click();
	     System.out.println("Profile Selection Successful ");
//	     
//	  // Click on Proceed Button
	    
	
	 	// Wait for the element to be visible
	 	WebElement ProceedBtn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/pay_now_button")));
	
	 	// Click on Book Now
	 	ProceedBtn1.click();
	 	
	 	
//	 	//Click on Pay now Button
	 	wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//	
//	 	// Wait for the element to be visible
	 	WebElement PayNowBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.Button")));
	
	 	PayNowBtn.click();
 	
	 	WebElement Gotohome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.TextView[2]")));
	
//	 	// Click on Pay Now
	 	Gotohome.click();
	 	System.out.println("Book now Flow is completed ");
 	
		}
}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
//	***************************************** Instant Call Flow *******************************************************************

		public void instantcall () {
			
			
			
			// Click on the "Instant Call" button
	    	WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        WebElement InstantCallBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/guide")));
	        InstantCallBtn.click();
	        System.out.println("Instant Call Booking Flow is Started");
	        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	        try {
	            // Wait for the presence of appointment_time_textview
	            WebElement appointmentTimeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.eshaafi.patient.consultation:id/appointment_time_textview"))); 
	            

	     // Print the text
	     System.out.println("Text from the proceedButton1: " );
	        
	       // boolean Btnstate = proceedButton1.isEnabled();
	       // if(buttonText  != "Instant appointment already booked") {
	        	
	        	// Wait for the "Proceed" button to appear and then click it
		           wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		        WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/proceed_button")));
		        proceedButton.click();
		        System.out.println("Doctor Slot is Shown ");
		        
		      //Select Profile
		        
			    WebElement Selectprofile = wait.until(ExpectedConditions.elementToBeClickable(
			             By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.ImageView[1]")));
			    Selectprofile.click();	    
			    
		        
		     // Click on the switch element
		        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		        WebElement switchElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/switch1")));
		        switchElement.click(); 
		        System.out.println("Slot is Reserved ");
		        
		        
		        // Click on the "Pay Now" button
		        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		        WebElement payNowButton = driver.findElement(By.id("com.eshaafi.patient.consultation:id/pay_now_button"));
		        payNowButton.click();
		        System.out.println("Wallet is Selected ");
		        
		        WebElement Gotohome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.TextView[2]")));
		        System.out.println("Payment Successful ");
		     	// Click on Pay Now
		     	Gotohome.click();
		     	System.out.println("Instant Call Booking Flow Completed ");
	        }
	        
		
	            catch (org.openqa.selenium.TimeoutException e) {
	                // Wait for the presence of error_message
	                WebElement errorMessageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.eshaafi.patient.consultation:id/error_message")));

	                // Error message is present
	                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOf(errorMessageElement));
	                String errorText = errorMessage.getText();
	                System.out.println("Error Message: " + errorText);
	                // Perform actions to go back or handle the error as needed
	                // For example, press the back button or navigate back
	                driver.navigate().back();
	                // Continue with the rest of your code
	            }
	        




		}    

//		***************************************** Subscription Flow *******************************************************************

	        
	        
		public void Subscription() {
			
			
		    // Click on the "Subscription" layout
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement subscriptionLayout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/subscription_layout")));
		    subscriptionLayout.click();

		    // Click on the first item in the RecyclerView
		    WebElement recyclerViewItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.eshaafi.patient.consultation:id/plans_recycler\"]/android.view.ViewGroup[1]")));
		    recyclerViewItem.click();

		    // Click on the "Subscribe Now" button
		    WebElement subscribeNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/subscribe_now")));
		    subscribeNowButton.click();

		    // Click on the JAZZCASH button
		    WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.widget.RadioButton[@resource-id=\"com.eshaafi.patient.consultation:id/radio\"])[2]")));
		    radioButton.click();

		    // Click on the "Pay Now" button
		    WebElement ProceedBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/pay_now_button")));
		    ProceedBtn.click();

		    // Enter account number
		    WebElement accountNumberEditText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/account_number_edittext")));
		    accountNumberEditText.sendKeys("03335030213");

		    // Enter CNIC
		    WebElement cnicEditText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/cnic_edittext")));
		    cnicEditText.sendKeys("6110141663319");

		    // Click on the "Pay Now" button
		    WebElement Proceed = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/paynow_button")));
		    Proceed.click();
		    
		    WebElement payNowpop = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/pay_now_button")));
		    payNowpop.click();
		    
		    
		    
		    driver.navigate().back();
		    
		}
		
//		***************************************** Book FLow using Subscription if Purchased  *******************************************************************

		
		
		public void BookNowSub(int n) {
			
			for (int i = 1; i <= n; i++) {
				
				Bookflow(i);
		    }
			
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(15));
		    WebElement Subplan = wait.until(ExpectedConditions.elementToBeClickable(
		             By.id("com.eshaafi.patient.consultation:id/plan_detail")));
		     Subplan.click();
		     System.out.println("Profile Selection Successful ");
//		     
//		  // Click on Proceed Button
		    
		
		 	// Wait for the element to be visible
		 	WebElement ProceedBtn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/pay_now_button")));
		
		 	// Click on Book Now
		 	ProceedBtn1.click();
		 	
		 	
//		 	//Click on Pay now Button
		 	
	//	
//		 	// Wait for the element to be visible
//		 	WebElement PayNowBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.Button")));
//		
//		 	PayNowBtn.click();
	 	
		 	WebElement Gotohome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.TextView[2]")));
		
//		 	// Click on Pay Now
		 	Gotohome.click();
		 	System.out.println("Book now Flow is completed ");
	 	
			}
	

		
//		public void swipeLeft() {
//			 
//			    org.openqa.selenium.Dimension size = driver.manage().window().getSize();
//			    int startY = (int) (size.height * 0.8);
//			    int endY = (int) (size.height * 0.2);
//			    int startX = size.width / 2;
//
//			    TouchAction<?> action = new TouchAction<>(driver);
//			    action.press(PointOption.point(startX, startY))
//			          .moveTo(PointOption.point(startX, endY))
//			          .release()
//			          .perform();
//			}
		public void MedicalRecord() throws InterruptedException {
			
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		// Continue button is enabled, proceed
	    WebElement MedicalRecords = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/medical_history_layout")));
	    
	    MedicalRecords.click();
	    
//	    WebElement Profile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.ImageView[@resource-id=\"com.eshaafi.patient.consultation:id/profile_image\"])[2]")));
//	    
//	    Profile.click();
	    WebElement AddMedical = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/add_record_button")));
	    
	    AddMedical.click();
	    
	    WebElement camera = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/title_tv")));
	    
	    camera.click();
	    //Press Volume up button to capture a image
		    driver.pressKey(new KeyEvent().withKey(AndroidKey.VOLUME_UP));
		    //I have added thread sleep to prevent speed clicking
		    Thread.sleep(2000);
	    
			// Using this method to click tick sign after capture photo
	    	hardStopWait(1000);
			driver.pressKey(new KeyEvent(AndroidKey.TAB));
			hardStopWait(1000);
			
			driver.pressKey(new KeyEvent(AndroidKey.TAB));
			hardStopWait(1000);
			
			driver.pressKey(new KeyEvent(AndroidKey.ENTER));
			System.out.println("TABS & ENTER Pressed");
			
			WebElement Croptick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/menu_crop")));
		   
		    Croptick.click();
			
		    WebElement UploadBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/upload_buttons_cl")));
			   
		    UploadBtn.click();
		    
		    System.out.println("Photo is successfully Uploaded Going Back");
		    
			
			
		    driver.navigate().back();
		    driver.navigate().back();
		}
		
		private void hardStopWait(int i) {
			// TODO Auto-generated method stub

	
}

//		***************************************** Add New Family Profile Flow *******************************************************************


		
		public void AddNewProfile() {
			
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement sidemenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/left_imageview")));
			sidemenu.click();
			
			WebElement Profile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/profile_layout")));
			Profile.click();
			
			//add profile
			WebElement AddProfile = driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.eshaafi.patient.consultation:id/profile_image'][1]"));
			AddProfile.click();
			
			//Call this method when need to fill Signup form
			Sigupdata();
			
			//Relation drop down
		    WebElement Relation = driver.findElement(By.xpath("(//android.widget.ImageButton[@content-desc=\"Show dropdown menu\"])[2]"));
		    Relation.click();
		    
		    
		    
		    //Slect son from menu
		    WebElement relation = driver.findElement(By.xpath("//android.widget.TextView[@text='Son']"));
		    relation.click();
		    
		    //Save Data
		    WebElement Save = driver.findElement(By.id("com.eshaafi.patient.consultation:id/save_button"));
		    Save.click();   
			
		}
	public void DeleteProfile() {
		
		 //Change numeric value if need to change any other profile
	    WebElement SelectProfile = driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id='com.eshaafi.patient.consultation:id/profile_image'])[2]"));
		SelectProfile.click();
		//Click Three dot Menu
		WebElement Threedot = driver.findElement(By.id("com.eshaafi.patient.consultation:id/menu_imageview"));
	    Threedot.click();
	    
	    //Delete Button Click
	    WebElement Delete= driver.findElement(By.id("com.eshaafi.patient.consultation:id/title"));
	    Delete.click();
	    
	    //Yes Option Click
	    WebElement Yes = driver.findElement(By.id("com.eshaafi.patient.consultation:id/leave_button"));
	    Yes.click();
	}


	public void checkwallet() {
		
		System.out.println("Checking Users Balance");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement Wallet = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@content-desc=\"Wallet\"]/android.widget.FrameLayout/android.widget.ImageView")));
		Wallet.click();
		
		// Find the element that displays the wallet balance
		WebElement walletBalanceElement = driver.findElement(By.id("com.eshaafi.patient.consultation:id/current_balance_textview"));

		 wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		// Get the text containing the wallet balance
		String walletBalanceText = walletBalanceElement.getText();

		// Output the wallet balance to the console
		System.out.println("User's Wallet Balance is: " + walletBalanceText);
		
		WebElement Home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/homefragment")));
		Home.click();
		
		

	}
		
	
	

	


	
	//Log out Function
	public void Logout() {
		
		System.out.println("Opening Side Menu");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement sidemenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/left_imageview")));
		sidemenu.click();
		
		WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/logout_layout")));
		
		logout.click();
		
		System.out.println("Logout Successful");
		
	}
	    
	//Allow Permission
	public void allowpermissions () {
		
		System.out.println("Allowing Permissions");
	    
	 // Handle permissions dialogs
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        for (int i = 1; i <= 2; i++) {
            WebElement allowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")));
            allowButton.click();
                 
        }
        
        WebElement allowButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_button")));
        allowButton1.click();
        
        System.out.println("Permissions Allowed");
        
	    }
	
//	********************************** Reuseable code for Booking flow******************************************
	
		// I will Use this method when i need the booking flow untill payment Screen appear
		public void Bookflow(int i){
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			System.out.println("Book Now Flow is started");
	 	 //Wait for the element to be visible
	 	WebElement BookNow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/book_appointments_button")));

	 	// Click on Book Now
	 	BookNow.click();
	 	
	 	
	 	 wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 	 //Scroll down
	 	//driver.findElement (AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"SHA...\"));"));
		
	     // Click on second Profile of Doctor
	     WebElement BookAppBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.Button")));
	      System.out.println("Doctors List Shown..");

	     // Click the element
	     BookAppBtn.click();
	 
	    // wait and Select second slot from the list
	    WebElement slot = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[" + i + "]/android.widget.TextView")));
	   
	    // Click on the slot
	    slot.click();
	
	 // Wait for "Proceed" button to be clickable
	 WebElement proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(
	         By.id("com.eshaafi.patient.consultation:id/proceed_button")));
	 proceedBtn.click();

	 // Check if toast message appears
	 wait = new WebDriverWait(driver, Duration.ofSeconds(1));

	// Check if the toast message element is present
	List<WebElement> toastMessageElements = driver.findElements(By.id("com.eshaafi.patient.consultation:id/snackbar_text"));

	if (!toastMessageElements.isEmpty()) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/snackbar_text")));
	    String messageText = toastMessage.getText();

	    if (messageText.equals("Please select slot first")) {
	        System.out.println("Oops the slot is already Booked :(");
	        System.out.println("Selecting Next Slot");

	        // Handle the case where the toast message is correct

	        // Click on the next slot
	        WebElement slot1 = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[" + (i + 1) + "]/android.widget.TextView")));
	        slot1.click();

	        // Wait for "Proceed" button to be clickable again
	        proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.id("com.eshaafi.patient.consultation:id/proceed_button")));

	        try {
	            Thread.sleep(2500); // Sleep for 2.5 seconds
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        proceedBtn.click();
	    }
	} else {
	    System.out.println("Toast message not found. Continuing with the next steps.");
	    
	}

//	    String toastMessage= driver.findElement(By.xpath("(//android.widget.Toast)")).getAttribute( "name");
	    
//	    AssertJUnit.assertEquals(toastMessage, "Please select slot first");
	    
	    //Select Profile
	    WebElement Selectprofile = wait.until(ExpectedConditions.elementToBeClickable(
	             By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.ImageView[1]")));
	    Selectprofile.click();
	    System.out.println("Slot Selection Successful ");
			
		}
		
		
		//Call this method when need to fill Signup form
		public void Sigupdata() {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			//Full name
			 WebElement Name = driver.findElement(By.id("com.eshaafi.patient.consultation:id/name_edittext"));
		    Name.sendKeys("Shoaib Yazdani");
		    
		    
		  //DOb
		    WebElement DOB = driver.findElement(By.id("com.eshaafi.patient.consultation:id/date_textView"));
		    DOB.click();
		    System.out.println("DOB Selected");
//		    WebElement month = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[1]/android.widget.Button[1]"));
//		    month.click();
//		    
//		    WebElement day = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[2]/android.widget.Button[1]"));
//		    day.click();
//		    
//		    WebElement year = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[3]/android.widget.Button"));
//		    year.click();
//		    
		    WebElement Okbtn = driver.findElement(By.id("com.eshaafi.patient.consultation:id/ok_btn"));
		    Okbtn.click();
		    
			
			
			//Gender
			// Locate the Gender dropdown element and click it
			WebElement genderDropdown = driver.findElement(By.id("com.eshaafi.patient.consultation:id/text_input_end_icon"));
			genderDropdown.click();
			
			
			
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			// Find all elements within the dropdown
			// Find the option "Male" and click it
			WebElement maleOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Male']"));
			maleOption.click();
			System.out.println("Gender is Selected");
			
			WebElement City = driver.findElement(By.id("com.eshaafi.patient.consultation:id/location_text"));
			City.sendKeys("Lahore");
			
			System.out.println("City Added");
			
		}
        
        
        
      
	}
	
	//@AfterClass
	//public void TearDown() {
		//driver.quit();
		//service.stop();
	//}


