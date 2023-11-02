package Appiumpackage1;

import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import static org.testng.AssertJUnit.assertEquals;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
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
		
		 service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Shoaib\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
				service.start();
		 
		 
				
				UiAutomator2Options options = new UiAutomator2Options();
			
				options.setDeviceName("Pixel 7 pro");
				options.setCapability("appPackage", "com.eshaafi.patient.consultation");
				options.setCapability("appActivity", "com.eshaafi.patient.consultation.ui.auth.AuthActivity");
				options.setCapability("appWaitActivity", "com.eshaafi.patient.consultation.ui.auth.AuthActivity");
				options.setCapability("enableMultiWindows", true);

				options.setCapability(MobileCapabilityType.NO_RESET, true);
				options.setCapability(MobileCapabilityType.FULL_RESET, false);

				
				
				 driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
				 //i have change this implicitlyWait from 30 to 5 for toast msg testing
				 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				 System.out.println("Eshaafi is now connected to Appium Server.");
				 
				 
	}
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
		
	
	
	//Booking flow
	
	public void BookNow(int n) {
		System.out.println("Book Now Flow is started");
		for (int i = 4; i <= n; i++) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	 	 //Wait for the element to be visible
	 	WebElement BookNow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/book_appointments_button")));
	
	 	// Click on Book Now
	 	BookNow.click();
	 	
	 	
	 	 wait = new WebDriverWait(driver, Duration.ofSeconds(2));
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
	            Thread.sleep(2500); // Sleep for 3.5 seconds
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

	    WebElement switchElement = wait.until(ExpectedConditions.elementToBeClickable(
	             By.id("com.eshaafi.patient.consultation:id/switch1")));
	     switchElement.click();
	     System.out.println("Profile Selection Successful ");
//	     
//	  // Click on Proceed Button
	     wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
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
	
	//Instant Call Flow
		public void instantcall () {
			
			
			
			// Click on the "Instant Call" button
	    	WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        WebElement InstantCallBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/guide")));
	        InstantCallBtn.click();
	        System.out.println("Instant Call Booking Flow is Started");
	        
	     // Find the button by its ID
	        WebElement proceedButton1 = driver.findElement(By.id("com.eshaafi.patient.consultation:id/proceed_button"));

	        // Check if the button is enabled
	        boolean Btnstate = proceedButton1.isEnabled();
	        if(Btnstate  == true) {
	        	
	        	// Wait for the "Proceed" button to appear and then click it
		           wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
	        else {
	        	
	        	System.out.println("The appointment is already booked ");
		    }
	        }

	        // Print the result
	        
	        
	     
		
		public void NewProfile(String name) {
			
			//add profile
//			WebElement AddProfile = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/androidx.cardview.widget.CardView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.ImageView"));
//			AddProfile.click();
//			
//			
//			
//			//Full name
//			 WebElement phoneInput = driver.findElement(By.id("com.eshaafi.patient.consultation:id/name_edittext"));
//		    phoneInput.sendKeys(name);
		    
		    
		  //DOb
//		    WebElement DOB = driver.findElement(By.id("com.eshaafi.patient.consultation:id/date_textView"));
//		    DOB.click();
//		    
//		    WebElement month = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[1]/android.widget.Button[1]"));
//		    month.click();
//		    
//		    WebElement day = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[2]/android.widget.Button[1]"));
//		    day.click();
//		    
//		    WebElement year = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[3]/android.widget.Button"));
//		    year.click();
//		    
//		    WebElement Okbtn = driver.findElement(By.xpath("com.eshaafi.patient.consultation:id/ok_btn"));
//		    Okbtn.click();
			
			
			//Gender
			// Locate the Gender dropdown element and click it
			WebElement genderDropdown = driver.findElement(By.id("com.eshaafi.patient.consultation:id/gender_spinner"));
			genderDropdown.click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			// Find all elements within the dropdown
			List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("android:id/text1")));

			// Assuming "Male" is the second option, click it
			if (options.size() >= 2) {
			    options.get(1).click(); // Index 1 corresponds to the second option (assuming 0-based indexing)
			} else {
			    System.out.println("Male option not found.");
			}

			
			//Relation
//		    WebElement Relation = driver.findElement(By.id("com.eshaafi.patient.consultation:id/relation_spinner"));
//		    Relation.click();
//		    WebElement Maleoption = driver.findElement(By.xpath("//android.widget.TextView[@text='Son']"));
//		    Maleoption.click();
		    //Save Data
//		    WebElement Save = driver.findElement(By.id("com.eshaafi.patient.consultation:id/save_button"));
//		    Save.click();
		    
//		    System.out.println("Profile Creation Successful");
		    
			
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

	public void toast(){
		
		// Wait for toast message to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast")));

		// Retrieve the toast message
		String toastMessage = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.Toast\")")).getText();

		// Print the toast message
		System.out.println("Toast Message: " + toastMessage);

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
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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


