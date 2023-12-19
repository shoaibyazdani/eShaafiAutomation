package Appiumpackage1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Appiumpackage.LoginScreen;

import org.testng.AssertJUnit;
import static org.testng.AssertJUnit.assertEquals;

import java.awt.Dimension;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
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
import static Appiumpackage1.Variablesclass.*;
import static Appiumpackage1.AddOTP.*;

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
			
		//these two below line are very important will not let the app uninstalled or close after running a test and ther test will start ferom any screen you want
		options.setCapability(MobileCapabilityType.NO_RESET, true);
		options.setCapability(MobileCapabilityType.FULL_RESET, false);



		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		//i have change this implicitlyWait from 30 to 5 for toast msg testing
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Eshaafi is now connected to Appium Server.");


	}
	

	
	
	
	
	//Login Flow
	public boolean Login() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		//Used Page object Pattern
		LoginScreen  loginscreen = new LoginScreen(driver);
		loginscreen.enterphoneNo("3066163246");
		loginscreen.ClickContinue();
		loginscreen.Clickotpfield();
		
//		/Command line Approach to enter otp
		String otpValue = "999999";
		AddOTP.enterText(otpValue);
		
//		loginscreen.SelectProfile();
		

		// Check if OTP is correct (5 characters are expected)
		if (otpValue.equals("999999")) {
			// Simulate login by checking if the home screen elements are visible after entering OTP
			WebElement homeScreenElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(InstantCallBtn)));

			// Add additional assertions or actions for successful login
			System.out.println("Login Successful ");
			return true;
		} else {
			// Fetch the error message for incorrect OTP
			WebElement errorMessage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ErrorMessage)));
			String errorText = errorMessage1.getText();
			System.out.println("Error Message: " + errorText);
			return false;
		}

		//		WebElement phoneInput = driver.findElement(By.id(PhoneNofield));
		//		phoneInput.sendKeys(phoneNumber);
		//		System.out.println("Entring Phone No.");
		//		// Continue button is enabled, proceed
		//		WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Createaccountbtn)));
		//
		//		continueButton.click();
		//		System.out.println("Entring OTP");
		//		WebElement OtpField = driver.findElement(By.id("com.eshaafi.patient.consultation:id/otp1_textview"));
		//		OtpField.click();	
		//		

		//		// Enter the OTP
		//		for (int i = 0; i < otp.length(); i++) {
		//			WebElement otpField = driver.findElement(By.id("com.eshaafi.patient.consultation:id/otp" + (i + 1) + "_textview"));
		//			otpField.sendKeys(String.valueOf(otp.charAt(i)));
		//		}
		// Check if OTP is correct (5 characters are expected)
		//				if (otpValue.equals("999999")) {
		//					// Simulate login by checking if the home screen elements are visible after entering OTP
		//					WebElement homeScreenElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(InstantCallBtn)));
		//
		//					// Add additional assertions or actions for successful login
		//					System.out.println("Login Successful ");
		//					return true;
		//				} else {
		//					// Fetch the error message for incorrect OTP
		//					WebElement errorMessage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ErrorMessage)));
		//					String errorText = errorMessage1.getText();
		//					System.out.println("Error Message: " + errorText);
		//					return false;
		//				}
//		
		
		
	}



	//		***************************************** SIGNUP Flow *******************************************************************
	//This method Auto genrate a number everytime i start
	public boolean Signup(String otp) {
		
		LoginScreen loginscreen = new LoginScreen(driver);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		{
			// Generate a random phone number with the prefix "306" and random digits
			String phoneNumber = generateRandomPhoneNumber(10); // Adjust the length as needed


			// Find and enter the phone number
			WebElement phoneInput = driver.findElement(By.id(PhoneNofield));
			phoneInput.sendKeys(phoneNumber);
			System.out.println("Entering Phone No.");

			// Continue button is enabled, proceed
			WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Createaccountbtn)));
			continueButton.click();
			
			loginscreen.Clickotpfield();

			
			System.out.println("Entering OTP");
			
			String otpValue = "999999";
			AddOTP.enterText(otpValue);

//			// Enter the OTP
//			for (int i = 0; i < otp.length(); i++) {
//				WebElement otpField = driver.findElement(By.id("com.eshaafi.patient.consultation:id/otp" + (i + 1) + "_textview"));
//				otpField.sendKeys(String.valueOf(otp.charAt(i)));
//			}


			//This method is called to fill the data in signup form
			Sigupdata();


			//Save Data
			WebElement Save = driver.findElement(By.id("com.eshaafi.patient.consultation:id/save_button"));
			Save.click();				  
			System.out.println("Save Button Clicked");
			return true;

			//		    // Check if OTP is correct (5 characters are expected)
//			if (otpValue.equals("999999")) {
//				// Simulate login by checking if the home screen elements are visible after entering OTP
//				WebElement homeScreenElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")));
//				//
//				// Add additional assertions or actions for successful login
//				System.out.println("Signup Successful ");
//				return true;
//
//
//			} else {
//				// Fetch the error message for incorrect OTP
//				WebElement errorMessage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ErrorMessage)));
//				String errorText = errorMessage1.getText();
//				System.out.println("Error Message: " + errorText);
//				return false;
//			}
		}
	}


	// Helper method to generate a random phone number with the same prefix and random digits
	public String generateRandomPhoneNumber(int length) {
		Random random = new Random();
		StringBuilder phoneNumber = new StringBuilder("306");

		// Generate random digits for the remaining part of the phone number
		for (int i = 0; i < length - 3; i++) {
			phoneNumber.append(random.nextInt(10));
		}

		return phoneNumber.toString();
	}




	//				***************************************** Book Now Flow *******************************************************************


	public void BookNow(int n) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		for (int i = 1; i <= n; i++)
		{
			//Booking flow until Payment screen
			Bookflow(i);
			
			WebDriverWait    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			WebElement switchElement = wait.until(ExpectedConditions.elementToBeClickable(
					By.id("com.eshaafi.patient.consultation:id/switch1")));
			switchElement.click();
			System.out.println("Profile Selection Successful ");
			//	     
			//	  // Click on Proceed Button

			//
			// Wait for the element to be visible
			WebElement ProceedBtn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(proceedButtonid)));

			// Click on Book Now
			ProceedBtn1.click();


			//Click on Pay now Button
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));


			WebElement PayNowBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(payNowid)));
			PayNowBtn.click();

			WebElement Gotohome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GoToHomeElement)));

			//Click on Pay Now
			Gotohome.click();
			System.out.println("Book now Flow is completed ");

		}
	}

	



	//	***************************************** Instant Call Flow *******************************************************************

	public void instantcall () {


		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// Click on the "Instant Call" button
		WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement InstantCallBtnn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(InstantCallBtn)));
		InstantCallBtnn.click();
		System.out.println("Instant Call Booking Flow is Started");

		try {
			// Wait for the presence of appointment_time_textview
			WebElement appointmentTimeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(AppointmentTimeText))); 


			// boolean Btnstate = proceedButton1.isEnabled();
			// if(buttonText  != "Instant appointment already booked") {

			// Wait for the "Proceed" button to appear and then click it

			WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(ProceedButton)));
			proceedButton.click();
			System.out.println("Doctor Slot is Shown ");

			//Select Profile

			WebElement Selectprofile = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath(ProfileSelection)));
			Selectprofile.click();	    


			// Click on the switch element

			WebElement switchElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(SelectWallet)));
			switchElement.click(); 
			System.out.println("Slot is Reserved ");


			// Click on the "Pay Now" button

			WebElement payNowButton = driver.findElement(By.id(PayNowBtn));
			payNowButton.click();
			System.out.println("Wallet is Selected ");

			WebElement Gotohome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GoToHomeElement)));
			System.out.println("Payment Successful ");
			// Click on Pay Now
			Gotohome.click();
			System.out.println("Instant Call Booking Flow Completed ");
		}


		catch (org.openqa.selenium.TimeoutException e) {
			// Wait for the presence of error_message
			WebElement errorMessageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(ErrorMessageE)));

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
		WebElement subscriptionLayout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(subscriptionLayoutid)));
		subscriptionLayout.click();

		// Click on the first item in the RecyclerView
		WebElement recyclerViewItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(recyclerViewItemid)));
		recyclerViewItem.click();

		// Click on the "Subscribe Now" button
		WebElement subscribeNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(subscribeNowButtonid)));
		subscribeNowButton.click();

		// Click on the JAZZCASH button
		WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(jazzCashRadioButtonid)));
		radioButton.click();

		// Click on the "Pay Now" button
		WebElement proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id(proceedButtonid)));
		proceedBtn.click();

		// Enter account number
		WebElement accountNumberEditText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(accountNumberEditTextid)));
		accountNumberEditText.sendKeys("03335030213");

		// Enter CNIC
		WebElement cnicEditText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(cnicEditTextid)));
		cnicEditText.sendKeys("6110141663319");

		// Click on the "Pay Now" button
		WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(By.id(proceedPayNowButtonid)));
		proceed.click();

		WebElement payNowPopup = wait.until(ExpectedConditions.elementToBeClickable(By.id(payNowid)));
		payNowPopup.click();





		driver.navigate().back();

	}

	//		***************************************** Book FLow using Subscription if Purchased  *******************************************************************



	public void BookNowSub(int n) {

		for (int i = 1; i <= n; i++) {

			Bookflow(i);
		}

		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(15));
		//Select Plan as payment method
		WebElement Subplan = wait.until(ExpectedConditions.elementToBeClickable(By.id(PlanPaymentmethod)));

		Subplan.click();
		System.out.println("Profile Selection Successful");
		
		// Click on Proceed Button
		WebElement ProceedBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(payNowid)));
		ProceedBtn.click();
		
		
		WebElement Gotohome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GoToHomeElement)));
		Gotohome.click();
		System.out.println("Book now Flow is completed ");

	}






	public void MedicalRecord() throws InterruptedException {



		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		// Continue button is enabled, proceed
		WebElement medicalRecords = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(medicalRecordsId)));
		medicalRecords.click();

//		WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(profileImageXpath)));
//		profile.click();

		WebElement addMedical = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(addRecordButtonId)));
		addMedical.click();

		WebElement camera = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OpenCameraID)));
		camera.click();
		
		Thread.sleep(2000);
		//Press Volume up button to capture a image
		driver.pressKey(new KeyEvent().withKey(AndroidKey.VOLUME_UP));
		//I have added thread sleep to prevent speed clicking
		
		// Using this method to click tick sign after capture photo
		hardStopWait(1000);
		driver.pressKey(new KeyEvent(AndroidKey.TAB));
		hardStopWait(1000);

		driver.pressKey(new KeyEvent(AndroidKey.TAB));
		hardStopWait(1000);


		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		System.out.println("TABS & ENTER Pressed");

		WebElement Croptick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(cropTickId)));
		Croptick.click();

		WebElement UploadBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(uploadButtonsId)));
		UploadBtn.click();

		System.out.println("Photo is successfully Uploaded Going Back");



		WebElement backbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));
		backbtn.click();
		
		driver.navigate().back();
	}

	private void hardStopWait(int i) {
		// TODO Auto-generated method stub


	}
	
	public void Prescription() {
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement prescriptiontile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PrescriptionHomeId)));
		prescriptiontile.click();
		
		WebElement PresFristItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PrescriptionFirstitemId)));
		PresFristItem.click();
		System.out.println("Prescription List Opened");
		
		WebElement Prescriptionview = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PrescriptionOpenedId)));
		System.out.println("Prescription Opened Successfully");
		
		WebElement PresDownload = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PrescriptiondownloadId)));
		PresDownload.click();
		
		System.out.println("Download button Clickd");
		
		driver.navigate().back();
		
		System.out.println("going Back to Home");
		
		WebElement BackBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));
		BackBtn.click();
		
		driver.navigate().back();
		
		
	}
	
	
	

	//		***************************************** Add New Family Profile Flow *******************************************************************



	public void AddnDeleteProfile() {

		LoginScreen  loginscreen = new LoginScreen(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement sidemenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(sidemenuId)));
		sidemenu.click();

		WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(profileId)));
		profile.click();

		// Add profile
		WebElement addProfile = driver.findElement(By.xpath(addProfileId));
		addProfile.click();

		// Call this method when need to fill Signup form
		Sigupdata();

		// Relation drop down
		WebElement relationDropdown = driver.findElement(By.xpath(relationDropdownId));
		relationDropdown.click();

		// Select 'Son' from menu
		WebElement relationSon = driver.findElement(By.xpath(selectSonId));
		relationSon.click();

		// Save Data
		WebElement saveButton = driver.findElement(By.id(saveButtonId));
		saveButton.click();  
		
		//Delete Profile
				WebElement SelectProfile = driver.findElement(By.xpath(selectProfileId));
				SelectProfile.click();
				//Click Three dot Menu
				WebElement Threedot = driver.findElement(By.id(threeDotMenuId));
				Threedot.click();

				//Delete Button Click
				WebElement Delete= driver.findElement(By.id(deleteButtonId));
				Delete.click();

				//Yes Option Click
				WebElement Yes = driver.findElement(By.id(yesOptionId));
				Yes.click();
				WebElement BackBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));
				BackBtn.click();
				
//				loginscreen.SelectProfile();
				
				driver.navigate().back();

	}



	public void DeleteProfile() {

		//Change numeric value if need to change any other profile
		WebElement SelectProfile = driver.findElement(By.xpath(selectProfileId));
		SelectProfile.click();
		//Click Three dot Menu
		WebElement Threedot = driver.findElement(By.id(threeDotMenuId));
		Threedot.click();

		//Delete Button Click
		WebElement Delete= driver.findElement(By.id(deleteButtonId));
		Delete.click();

		//Yes Option Click
		WebElement Yes = driver.findElement(By.id(yesOptionId));
		Yes.click();
	}



	public void checkwallet() {

		System.out.println("Checking Users Balance");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement Wallet = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(walletIconXpath)));
		Wallet.click();

		// Find the element that displays the wallet balance
		WebElement walletBalanceElement = driver.findElement(By.id(walletBalanceElementId));

		wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		// Get the text containing the wallet balance
		String walletBalanceText = walletBalanceElement.getText();

		// Output the wallet balance to the console
		System.out.println("User's Wallet Balance is: " + walletBalanceText);

		WebElement Home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(HomeButtonId)));
		Home.click();



	}




	//Log out Function
	public void Logout() {

		System.out.println("Opening Side Menu");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement sidemenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(sidemenuId)));
		sidemenu.click();

		WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logoutButtonXPath)));
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
		WebElement slot = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[" + i + "]/android.widget.TextView")));

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
				proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/proceed_button")));

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
		WebElement Selectprofile = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.ImageView[1]")));
		Selectprofile.click();
		System.out.println("Slot Selection Successful ");

	}


	//Call this method when need to fill Signup form
	public void Sigupdata() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));


		//Full name
		WebElement Name =wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/name_edittext")));
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

	public void newScenario() {
		// Click on the JAZZCASH button
		//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		//		    WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.widget.RadioButton[@resource-id=\"com.eshaafi.patient.consultation:id/radio\"])[1]")));
		//		    radioButton.click();
		//		 // Click on the "Pay Now" button
		//		    WebElement ProceedBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/pay_now_button")));
		//		    ProceedBtn.click();

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


	//		    // Click on the Button by ID
	//		    WebElement buttonById = driver.findElement(By.id("pay"));
	//		    buttonById.click();
	//
	//
	//		    // Find the input field CustomerName by ID and send keys "Shoaib"
	//		    WebElement customerNameInput = driver.findElement(By.id("CustomerName"));
	//		    customerNameInput.sendKeys("Shoaib");
	//
	//		    // Find the input field cardEmailAddress and send keys "shoaibyazdani7@gmail.com"
	//		    WebElement cardEmailAddressInput = driver.findElement(By.id("cardEmailAddress"));
	//		    cardEmailAddressInput.sendKeys("shoaibyazdani7@gmail.com");
	//		}





}

//@AfterClass
//public void TearDown() {
//driver.quit();
//service.stop();
//}


