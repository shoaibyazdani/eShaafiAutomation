package Appiumpackage1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Appiumpackage.LoginScreen;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.AssertJUnit;
import static org.testng.AssertJUnit.assertEquals;

import java.io.BufferedReader;
//import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.LogEntry;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.clipboard.HasClipboard;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.netty.handler.timeout.TimeoutException;
import static Appiumpackage1.Variablesclass.*;
import static Appiumpackage1.AddOTP.*;
import java.net.MalformedURLException;



public class BaseTest {



	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	 private ExtentReports extent;
	    private ExtentTest test;
	    


	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException {



		service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Shoaib\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();



		UiAutomator2Options options = new UiAutomator2Options();
		//		options.setDeviceName("OPPO CPH2421");
		options.setDeviceName("Pixel 6a ");
		options.setCapability("appPackage", "com.eshaafi.patient.consultation");
		options.setCapability("appActivity", "com.eshaafi.patient.consultation.ui.auth.AuthActivity");
		options.setCapability("appWaitActivity", "com.eshaafi.patient.consultation.ui.auth.AuthActivity");
		options.setCapability("autoGrantPermissions", true); // it will auto grant all the permissions
		options.setCapability("enableMultiWindows", true);
		options.setCapability("setWebContentsDebuggingEnabled", true);
		options.setCapability("enforceIdleStateWait", false);

		//		options.setChromedriverExecutable("//Users//Shoaib\\Downloads\\chromedriver_win32//chromedriver");

		//options.setCapability("autoWebview", true);
		//options.setCapability("androidPackage", "com.android.chrome");

		//these two below line are very important will not let the app uninstalled or close after running a test and ther test will start ferom any screen you want
		options.setCapability(MobileCapabilityType.NO_RESET, true);
		options.setCapability(MobileCapabilityType.FULL_RESET, false);



		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		//i have change this implicitlyWait from 30 to 5 for toast msg testing
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Eshaafi is now connected to Appium Server.");


	}
	
	 @BeforeSuite
	    public void setUp() {
	        extent = new ExtentReports();
	        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/SideMenuTestReport.html");
	        extent.attachReporter(spark);
	    }









	//Login Flow
	public boolean Login() {

		 test = extent.createTest("Verify Login Functionality");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));

		//Used Page object Pattern
		LoginScreen  loginscreen = new LoginScreen(driver);
		loginscreen.enterphoneNo("3066163246");
		loginscreen.ClickContinue();
		//Enter OTP in With just Long Press
		String otpValue = "999999";
		driver.setClipboardText(otpValue);

		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(13));
		WebElement CLickfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id((Clickotp))));

		// Perform long click
		Actions actions = new Actions(driver);
		actions.clickAndHold(CLickfield).perform();



		try {
			wait2.until(ExpectedConditions.visibilityOfElementLocated(
					By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")));

			// Click on the allow button if it appears
			WebElement allowWhileUsing = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id(("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))));
			allowWhileUsing.click();

		} catch (Exception e) {
			
		}
		loginscreen.SelectProfile();
		WebElement Instantcallicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id((InstantCallBtn))));
		
		swipeDown();
		
		Instantcallicon = wait.until(ExpectedConditions.elementToBeClickable(By.id(InstantCallBtn)));
		if (Instantcallicon != null) {
		    System.out.println("Login Success");
		    test.log(Status.PASS, "Login Successful");
		    if (Instantcallicon.isDisplayed()) {
		        test.log(Status.INFO, "Instant call icon is visible, confirming successful login");
		    }
		    return true;
		} else {
		    System.out.println("Login Failed");
		    test.log(Status.FAIL, "Login Failed - Instant call icon not found");
		    // Optionally, you can add more details about the failure
		    test.log(Status.INFO, "Current URL: " + driver.getCurrentUrl());
		   
		    return false;
		}
		

	}

	//Login Flow
	public boolean CorporateLogin() {


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		//Used Page object Pattern
		LoginScreen  loginscreen = new LoginScreen(driver);
		WebElement CorporateBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id((CorporateLoginBtn))));
		CorporateBtn.click();
		loginscreen.enterphoneNo("3066163246");
		loginscreen.ClickContinue();
		loginscreen.Clickotpfield();

		//Command line Approach to enter otp
		String otpValue = "999999";
		AddOTP.enterText(otpValue);
		System.out.println("Before clicking Continue button");
		loginscreen.SelectProfile();

		System.out.println("After clicking Continue button");

		WebElement Instantcallicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id((InstantCallBtn))));
		swipeDown();
		if (Instantcallicon!= null) {
			System.out.println("Login Success");
			return true;
		} else {
			System.out.println("Login Failed");
			return false;

		}


	}



	//		***************************************** SIGNUP Flow *******************************************************************
	
	//This method Auto genrate a number everytime i start
	public boolean Signup(String otp) throws InterruptedException {

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

			//Enter OTP in With just Long Press
			String otpValue = "999999";
			driver.setClipboardText(otpValue);


			WebElement CLickfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id((Clickotp))));

			// Perform long click
			Actions actions = new Actions(driver);
			actions.clickAndHold(CLickfield).perform();

			//			String otpValue = "999999";
			//			AddOTP.enterText(otpValue);

			Sigupdata();


			//Save Data
			WebElement Save = driver.findElement(By.id("com.eshaafi.patient.consultation:id/save_button"));
			Save.click();	

			System.out.println("Save Button Clicked");
			return true;



		}
	}

	//Call this method when need to fill Signup form
	public void Sigupdata() throws InterruptedException {
		// Generate a random name
		String randomName = generateRandomName();  
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		//Full name
		WebElement Name =wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/name_edittext")));
		Name.sendKeys(randomName);


		//DOb
		WebElement DOB = driver.findElement(By.id("com.eshaafi.patient.consultation:id/date_textView"));
		DOB.click();
		System.out.println("DOB Selected");
		Thread.sleep(1000);
		WebElement Okbtn = driver.findElement(By.id("com.eshaafi.patient.consultation:id/ok_btn"));
		Okbtn.click();


		// Locate the Gender dropdown element and click it
		WebElement genderDropdown = driver.findElement(By.id("com.eshaafi.patient.consultation:id/text_input_end_icon"));
		genderDropdown.click();

		// Find the option "Male" and click it
		WebElement maleOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Male']"));
		maleOption.click();
		System.out.println("Gender is Selected");

		WebElement City = driver.findElement(By.id("com.eshaafi.patient.consultation:id/location_text"));
		City.sendKeys("Lahore");

		System.out.println("City Added");

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


	public void BookNow(int n) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		for (int i = 1; i <= n; i++)
		{
			System.out.println("Book Now Flow using Wallet Started");
			//Booking flow until Payment screen
			Bookflow(i);

			WebDriverWait    wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			WebElement walletBalance = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/walletPlanLayout")));
			walletBalance.click();

			// Wait for the element to be visible
			WebElement ProceedBtn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(proceedButtonid)));

			// Click on Book Now
			ProceedBtn1.click();


			//Click on Pay now Button
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));


//			WebElement PayNowBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(payNowid)));
//			PayNowBtn.click();
			Thread.sleep(3000); // Sleep for 2.5 seconds
			WebElement backBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));

			//Goto Home
			backBtn.click();
			System.out.println("i am sleeping");
			
//			WebElement Gotohome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(GoToHomeElement)));
//
//			//Goto Home
//			Gotohome.click();
			System.out.println("Book now Flow is completed ");


			Thread.sleep(2500); // Sleep for 2.5 seconds


		}
	}
	public void BookNowJAzzCash(int n) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		for (int i = 1; i <= n; i++) {
			System.out.println("Book Now Flow using Jazzcash Started");
			//Booking flow until Payment screen
			Bookflow(i);

			// Click on the "Payment Method" button
			WebElement selectPayMethod = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/payment_method_name")));
			selectPayMethod.click();

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

			// Print payment details
			WebElement paymentInfo = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/payment_method_tv")));
			System.out.println("Payment Details: " + paymentInfo.getText());

			// Click on the "Pay Now" popup
			WebElement payNowPopup = wait.until(ExpectedConditions.elementToBeClickable(By.id(payNowid)));
			payNowPopup.click();

			try {
				System.out.println(" I am Here");
				WebElement goToHome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GoToHomeElement)));

				// Click on Go to Home
				goToHome.click();
				System.out.println("Book now Flow is completed ");
			} catch (Exception e) {
				System.out.println("Jazzcash Payment Not Received");
				driver.navigate().back();

				// Wait for the BackButton element to be visible
				WebElement backButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));
				backButton.click();

				System.out.println("Back Button Clicked");

				// Wait for the confirmation popup and click on Yes
				WebElement confirmYes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/action_button")));
				confirmYes.click();

				for (int j = 0; j < 2; j++) {
					// Wait for the BackButton element to be visible
					WebElement backButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));

					// Click the BackButton element
					backButton1.click();

					// Add a small delay to wait for the next screen to load (if necessary)
					Thread.sleep(1000); // Adjust the delay time as needed
				}
			}

			Thread.sleep(2500); // Sleep for 2.5 seconds
		}
	}

	//	***************************************** Book FLow using Subscription if Purchased  *******************************************************************



	public void BookNowSub(int n) throws InterruptedException {

		for (int i = 1; i <= n; i++) {

			System.out.println("Book Now Flow using Subscription Plan  Started");

			//Called This Booknow flow
			Bookflow(i);

			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(15));
			//Select Plan as payment method
			WebElement Subplan = wait.until(ExpectedConditions.elementToBeClickable(By.id(individualPlan)));

			Subplan.click();
			System.out.println("Profile Selection Successful");

			// Click on Proceed Button
			WebElement ProceedBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(confirmPlanBtn)));
			ProceedBtn.click();

			Thread.sleep(3000); // Sleep for 2.5 seconds
			WebElement backBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));

			//Goto Home
			backBtn.click();
			
			System.out.println("Book now Flow is completed ");

		}
	}


	public void bookwithwallet() {

		System.out.println("Plan Not Found Selecting Wallet ");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement switchElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/switch12")));
		switchElement.click();

		// Wait for the element to be visible
		WebElement ProceedBtn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(proceedButtonid)));

		// Click on Book Now
		ProceedBtn1.click();

		// Click on Pay now Button
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement PayNowBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(payNowid)));
		PayNowBtn.click();

		WebElement Gotohome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GoToHomeElement)));

		// Click on Pay Now
		Gotohome.click();
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
			System.out.println("Oops The " + errorText);
			// Perform actions to go back or handle the error as needed
			// For example, press the back button or navigate back
			driver.navigate().back();
			// Continue with the rest of your code
		}





	}    

	//		***************************************** Subscription Flow *******************************************************************



	public void Subscription() throws InterruptedException {


		// Click on the "Subscription" layout
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement subscriptionLayout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(subscriptionLayoutid)));
		subscriptionLayout.click();

		Thread.sleep(3000);
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



		WebElement Loadingmodal = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/payment_method_label_tv")));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/payment_method_label_tv")));

		driver.navigate().back();
		driver.navigate().back();

	}






	public void MedicalRecord() throws InterruptedException {



		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
		// Continue button is enabled, proceed
		WebElement medicalRecords = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(medicalRecordsId)));
		medicalRecords.click();
		Thread.sleep(3000);
		//		WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(profileImageXpath)));
		//		profile.click();

		WebElement addMedical = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(addRecordButtonId)));
		addMedical.click();
		try {

			WebElement camera = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(OpenCameraID)));
			camera.click();

		}
		catch(Exception e){

			WebElement allowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")));
			allowButton.click();

			WebElement allowButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_button")));
			allowButton1.click();

			WebElement addMedicalbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(addRecordButtonId)));
			addMedicalbtn.click();

			WebElement camera = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OpenCameraID)));
			camera.click();
		}

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

		Thread.sleep(4500); // Sleep for 2.5 seconds

		WebElement backbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));
		backbtn.click();

		System.out.println("Back Button clicked");






	}

	// We Are calling hardStopWait in medical record whilecapturing the image
	private void hardStopWait(int i) {
		// TODO Auto-generated method stub


	}

	public void Prescription() throws InterruptedException {


		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement prescriptiontile = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(PrescriptionHomeId)));
		prescriptiontile.click();
		System.out.println("Prescription Tile Clicked");

		WebElement PresFristItem = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PrescriptionFirstitemId)));
		PresFristItem.click();
		System.out.println("Prescription List Opened");

		WebElement Prescriptionview = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PrescriptionOpenedId)));
		// Check if the element is visible
		if (Prescriptionview.isDisplayed()) {
			System.out.println("Prescription Opened Successfully");
		}

		WebElement PresDownload = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(PrescriptiondownloadId)));
		PresDownload.click();

		System.out.println("Download button Clickd");
		Thread.sleep(3000);

		driver.navigate().back();

		System.out.println("going Back to Home");

		WebElement BackBtn = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));
		BackBtn.click();

		driver.navigate().back();


	}




	//		***************************************** Add New Family Profile Flow *******************************************************************



	public void AddnDeleteProfile() throws InterruptedException {

		LoginScreen  loginscreen = new LoginScreen(driver);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement sidemenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(sidemenuId)));
		sidemenu.click();

		System.out.println("Adding New Child Profile");

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

		System.out.println("Deleting Profile");

		//Click Three dot Menu
		WebElement Threedot = driver.findElement(By.id(threeDotMenuId));
		Threedot.click();

		System.out.println("3 Dot Menu Clicked");

		//Delete Button Click
		WebElement Delete= driver.findElement(By.id(deleteButtonId));
		Delete.click();

		//Yes Option Click
		WebElement Yes = driver.findElement(By.id(yesOptionId));
		Yes.click();

		System.out.println("Profile Deleted Successfully");

		WebElement BackBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));
		BackBtn.click();



		loginscreen.SelectProfile();


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
		 test.log(Status.PASS, "Login Successful");
	}




	public void checkwallet() {

		System.out.println("Checking Users Balance");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement sidemenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(sidemenuId)));
		sidemenu.click();
		WebElement Wallet = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(walletIcon)));
		Wallet.click();

		// Find the element that displays the wallet balance
		WebElement walletBalanceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(walletBalanceElementId)));
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// Get the text containing the wallet balance
		String walletBalanceText = walletBalanceElement.getText();

		// Output the wallet balance to the console
		System.out.println("User's Wallet Balance is: " + walletBalanceText);

		WebElement WalletHistory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/transaction_textview")));
		WalletHistory.click();

		// Wait for the visibility of any ViewGroup within the RecyclerView
		WebElement firstVisibleItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.eshaafi.patient.consultation:id/transaction_recyclerview']/android.view.ViewGroup")));

		firstVisibleItem.click();
		
		// Wait for the visibility of the payment mode element
		WebElement paymentModeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/relation")));
		String paymentMode = paymentModeElement.getText();

		// Wait for the visibility of the price element
		WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/name")));
		String price = priceElement.getText();

		// Concatenate the payment mode and price, and print it
		String paymentInfo = "Mode: " + paymentMode + " | Amount: " + price;
		System.out.println("Last Transaction: " + paymentInfo);

		
		
		
		// Check if the element is visible
//		if (firstVisibleItem.isDisplayed()) {
//			System.out.println("Wallet History shown");
//		}
		
		driver.navigate().back();

		WebElement Backbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));
		Backbutton.click();

		WebElement Home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(HomeButtonId)));
		Home.click();
//		 test.log(Status.PASS, "Wallet balance fetching ");
//		 test.log(Status.PASS, "Transaction history");

	}



	public void referal() throws InterruptedException {

		WebDriverWait    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		Thread.sleep(3500); // Sleep for 2.5 seconds

		WebElement Wallet = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/walletFragment")));
		Wallet.click();
		System.out.println("Wallet Clicked");
		WebElement Applycodebtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/plan_type_d")));
		Applycodebtn.click();
		System.out.println("Apply Clicked");

		WebElement EnterCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/phoneno_edittext")));
		EnterCode.sendKeys("TVNGFR1E");
		System.out.println("Code Added");

		WebElement ApplyButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/sharecode")));
		ApplyButton.click();
		System.out.println("Apply Clicked Again");

		//		WebElement ShareCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/backHome")));
		//		ShareCode.click();
		//		System.out.println("Share Code Clicked");

		WebElement CrossButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/icCross")));
		CrossButton.click();

		System.out.println("Cross Button Clicked");

		driver.navigate().back();

		Thread.sleep(3500); // Sleep for 3.5 seconds

		WebElement Home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(HomeButtonId)));
		Home.click();



		System.out.println("Home Clicked");

	}


	//	private void executeAdbCommand(String command) {
	//	    try {
	//	        String adbCommand = "adb " + command;
	//	        System.out.println("Executing ADB command: " + adbCommand);
	//	        Process process = Runtime.getRuntime().exec(adbCommand);
	//	        int exitCode = process.waitFor();
	//	        if (exitCode != 0) {
	//	            System.err.println("Error executing ADB command. Exit code: " + exitCode);
	//	        }
	//	    } catch (IOException | InterruptedException e) {
	//	        System.err.println("Exception while executing ADB command: " + e.getMessage());
	//	        e.printStackTrace();
	//	    }
	//	}

	 
	    public void SideMenuTest() throws InterruptedException {
	        test = extent.createTest("Side Menu Functionality Test", "Verify all side menu items and logout");
	        System.out.println("Testing SideMenu Items");
	        test.log(Status.INFO, "Starting Side Menu test");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	        try {
	            // Side menu elements
	            WebElement sidebarButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sidebarButtonXPath)));
	            sidebarButton.click();
	            System.out.println("Sidebar button clicked.");
	            test.log(Status.PASS, "Sidebar button clicked successfully");

	            // Clicking on profile image
	            clickAndWait(wait, By.xpath(profileImageXPath), "Profile");
	            Thread.sleep(2000);
	            clickAndWait(wait, By.xpath(appointmentButtonXPath), "Appointment History");
	            Thread.sleep(2000);
	            clickAndWait(wait, By.xpath(LabOrdersID), "Lab Orders");
	            Thread.sleep(2000);
	            clickAndWait(wait, By.id(NotificationsId), "Notifications");
	            clickAndWait(wait, By.id(SharewithfriendsId), "Share with Friends");
	            clickAndWait(wait, By.id(HealthHistoryId), "Health History");
	            clickAndWait(wait, By.id(SubscriptionPlanId), "Subscription Plans");
	            clickAndWait(wait, By.xpath(feedbackButtonXPath), "Feedback");
	            clickAndWait(wait, By.id(FaqsId), "FAQs");
	            clickAndWait(wait, By.id(TermandConditionsId), "Terms and Conditions");
	            clickAndWait(wait, By.id(PrivacyPolicyId), "Privacy Policy");
	            clickAndWait(wait, By.id(AccountSettingsId), "Account Settings");

	            // Logging out
	            WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logoutButtonXPath)));
	            logoutButton.click();
	            System.out.println("Logged out successfully.");
	            test.log(Status.PASS, "Logged out successfully");

	            test.log(Status.PASS, "Side Menu test completed successfully");
	        } catch (Exception e) {
	            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
	            test.fail(e);
	        }
	    }

	    private void clickAndWait(WebDriverWait wait, By locator, String elementName) {
	        try {
	            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	            element.click();
	            System.out.println(elementName + " Clicked");
	            test.log(Status.PASS, elementName + " clicked successfully");
	            goBack(wait);
	        } catch (Exception e) {
	            test.log(Status.FAIL, "Failed to click " + elementName + ": " + e.getMessage());
	        }
	    }

	    private void goBack(WebDriverWait wait) {
	        try {
	            Thread.sleep(4000);
	            WebElement backButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));
	            backButton.click();
	            test.log(Status.PASS, "Navigated back successfully");
	        } catch (Exception e) {
	            System.out.println("Error while clicking the back button: " + e.getMessage());
	            test.log(Status.INFO, "Error while clicking the back button: " + e.getMessage());
	        }

	        // After clicking the back button, navigate back to the sidebar
	        try {
	            WebElement sidebarButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sidebarButtonXPath)));
	            sidebarButton.click();
	            test.log(Status.PASS, "Sidebar reopened successfully");
	        } catch (Exception e) {
	            System.out.println("Error while clicking the sidebar button: " + e.getMessage());
	            test.log(Status.INFO, "Error while clicking the sidebar button: " + e.getMessage());
	        }
	    }

	    @AfterSuite
	    public void tearDown() {
	        extent.flush();
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
		 test.log(Status.PASS, "Logout Function Test");

	}

	
	public void RegressionTest() throws InterruptedException{

		  if (!Login()) {
	            System.out.println("Login failed. Aborting regression test.");
	            return;
	        }


			//Allow all Permissions	
			 //allowpermissions();		
			//clock();
			//How many slots you want to book

			BookNow(1);

			try {
				BookNowSub(1);
			}
			catch (Exception e) { 

				System.out.println("Plan Not available Found Selecting Wallet ");

				bookwithwallet();

			}	

			//	BookNowJAzzCash(1);


			//CorporateLogin();
			//Call Instant Call Method
			//instantcall();

			BookLab();  

			MedicalRecord();

			//Check user's balance and History
			checkwallet();

			//to Add and Delete Profile
			AddnDeleteProfile();
			//Prescription List , Open and Download
			Prescription();	    		
			//Sidemenu Flow with Logout 
			SideMenuTest();

			Signup("999999");
			//Subscription();

			//			referal();

			//				    		



		}	

	

	public void Countdown() {
		{
			int durationInSeconds = 60; // Set the duration of loading in seconds

			System.out.println("Waiting for Payment...");
			for (int i = 0; i < durationInSeconds; i++) {
				System.out.print("."); // Print a dot to indicate loading progress
				try {
					Thread.sleep(1000); // Pause for 1 second (1000 milliseconds)
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("\nLoading complete!");
		}
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
		 test.log(Status.PASS, "Permissions Test");

	}
	/* 

 					Reuseable code for Booking flow

	 */

	// I will Use this method when i need the booking flow untill payment Screen appear
	public void Bookflow(int i){

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


//		//Wait for the element to be visible
//		WebElement BookNow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/book_appointments_button")));
//
//		// Click on Book Now
//		BookNow.click();


		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		//Scroll down
		//driver.findElement (AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"SHA...\"));"));

		// Click on second Profile of Doctor
		WebElement BookAppBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.widget.TextView[@resource-id=\"com.eshaafi.patient.consultation:id/bookNow_btn\"])[1]")));
		System.out.println("Doctors List Shown..");

		// Click the element
		BookAppBtn.click();

		//the will find the available slot automatically and change the Sections from morning to afternoon or afternoon toevening automatically if the slot not found
		//The scroll is pending if you want to book the one day's all slots
		try {
			boolean afternoonChecked = false;
			boolean eveningChecked = false;

			while (true) {
				// Start with the current section, which should be automatically selected
				List<WebElement> slots = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView")));

				boolean slotFound = false;
				for (WebElement slot : slots) {
					if (slot.isDisplayed() && slot.isEnabled()) {
						System.out.println("Selected Slot: " + slot.getText());
						slot.click();
						slotFound = true;
						break; // Slot is found, break the loop
					} else {
						System.out.println("Slot already booked: " + slot.getText());
					}
				}

				// If a slot is found, we don't need to proceed further
				if (slotFound) {
					break;
				}

				// If no slot is found in the current section, move to the next section if not already checked
				if (!afternoonChecked) {
					WebElement afternoonElement = driver.findElement(By.id("com.eshaafi.patient.consultation:id/afternoon_tv"));
					if (afternoonElement.getAttribute("selected").equals("false")) {
						afternoonElement.click();
						System.out.println("Afternoon Checked");
						afternoonChecked = true;
						continue; // Go back to checking slots in the new section
					}
				}

				if (!eveningChecked) {
					WebElement eveningElement = driver.findElement(By.id("com.eshaafi.patient.consultation:id/evening_tv"));
					if (eveningElement.getAttribute("selected").equals("false")) {
						eveningElement.click();
						System.out.println("Evening Checked");
						eveningChecked = true;
						continue; // Go back to checking slots in the new section
					}
				}

				// If all sections have been checked and no slots are available, log the status and break
				if (afternoonChecked && eveningChecked) {
					System.out.println("All slots are booked");
					break;
				}


			}
		} catch (TimeoutException e) {
			System.out.println("Timed out waiting for slot to become available");
			// Handle the TimeoutException if needed
		}


		// Wait for "Proceed" button to be clickable
		WebElement proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(
				By.id("com.eshaafi.patient.consultation:id/proceed_button")));

		proceedBtn.click();

		//		//Select Profile
		//		WebElement Selectprofile = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.ImageView[1]")));
		//		Selectprofile.click();

		System.out.println("Slot Selection Successful ");
		 //test.log(Status.PASS, "Booking Flow Test");
	}

	// Function to perform a swipe down
	protected void swipeDown() {
		int screenWidth = driver.manage().window().getSize().getWidth();
		int screenHeight = driver.manage().window().getSize().getHeight();
		int startX = screenWidth / 2;
		int startY = (int) (screenHeight * 0.5); // Start from the center of the screen
		int endY = (int) (screenHeight * 0.8); // You can adjust the endY value based on your needs

		@SuppressWarnings("deprecation")
		TouchAction<?> touchAction = new TouchAction<>(driver);
		touchAction.press(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release().perform();
	}

	// Function to perform a swipe up
	protected void swipeUp() {
		int screenWidth = driver.manage().window().getSize().getWidth();
		int screenHeight = driver.manage().window().getSize().getHeight();
		int startX = screenWidth / 2;
		int startY = (int) (screenHeight * 0.8); // Start from the lower part of the screen
		int endY = (int) (screenHeight * 0.2); // End at the middle of the screen

		TouchAction<?> touchAction = new TouchAction<>(driver);
		// The swipe action is performed by moving from startY to endY
		touchAction.press(PointOption.point(startX, startY))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))) // Wait to simulate long press
		.moveTo(PointOption.point(startX, endY))
		.release()
		.perform();
	}


	public static void changeDriverContextToWeb(AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		if (driver instanceof SupportsContextSwitching) {
			SupportsContextSwitching contextSwitchingDriver = (SupportsContextSwitching) driver;
			Set<String> contextHandles = contextSwitchingDriver.getContextHandles();
			for (String contextHandle : contextHandles) {
				if (contextHandle.contains("WEBVIEW")) {
					contextSwitchingDriver.context(contextHandle);
					System.out.println("Successfully switched to context: " + contextHandle);
					try {
						// Try to clear session and local storage
						driver.executeScript("window.sessionStorage.clear();");
						driver.executeScript("window.localStorage.clear();");
						// Refresh the webview
						driver.navigate().refresh();
						// Wait for the specific element you want to interact with
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[normalize-space()='Proceed'])[1]")));
						// If the element is found, proceed with your test
						System.out.println("Element is visible now.");
						break; // Break out of the loop if element is found
					} catch (TimeoutException e) {
						System.out.println("Element not visible after refresh, trying again.");
						// Here you can implement logic to retry refreshing or navigate to the page again
					}
					// Implement additional logic as needed based on your retry strategy
				}
			}
			System.out.println("No WEBVIEW context found.");
		} else {
			System.out.println("Driver does not support context switching.");
		}
	}


	public void ClearCache() throws InterruptedException, IOException {


		// Clear the app's cache using ADB
		String appId = "com.eshaafi.patient.consultation"; // Replace with your app's package name
		ProcessBuilder pb = new ProcessBuilder("adb", "shell", "pm", "clear", appId);
		pb.redirectErrorStream(true);
		Process process = pb.start();
		int exitCode = process.waitFor();
		if (exitCode == 0) {
			System.out.println("Cache cleared successfully.");
		} else {
			System.out.println("Failed to clear cache.");
		}

	}




	public void ContextSwitching() throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		//		// Click on the JAZZCASH button
		//		WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.widget.RadioButton[@resource-id=\"com.eshaafi.patient.consultation:id/radio\"])[1]")));
		//		radioButton.click();
		//
		//		// Click on the "Pay Now" button
		//		WebElement proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/pay_now_button")));
		//		proceedBtn.click();
		////
		////		// Wait for the title_textview element to have the text "Payment Method"
		//		WebElement titleTextView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/title_textview")));
		////		Thread.sleep(2000);

		// Check if the title_textview element has the text "Payment Method"
		//		if ("Payment Method".equals(titleTextView.getText())) {
		// If true, proceed to check for the WebView





		// Switch to the "WEBVIEW" context
		changeDriverContextToWeb(driver);

		// Now you are in the "WEBVIEW" context, perform actions within the WebView
		Thread.sleep(5000);

		// Retry settings
		int maxRetries = 3;
		int retryCount = 0;
		boolean success = false;

		while (!success && retryCount < maxRetries) {
			try {
				// Click on the Button by ID
				// Reinitialize WebDriverWait inside the loop
				//			    	driver.navigate().refresh();
				//			    	ClearCache();
				//			        String pageSource = driver.getPageSource();
				//			        System.out.println("Page source in WebView context: " + pageSource);


				driver.executeScript("window.sessionStorage.clear();");
				//			    	driver.navigate().refresh();
				String pageSource = driver.getPageSource();
				System.out.println("Page source in WebView context: " + pageSource);
				WebElement buttonById = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[normalize-space()='Proceed'])[1]")));
				System.out.println("Button found. Proceeding to click.");
				buttonById.click();
				driver.navigate().refresh();

				success = true; // Set success to true if click is successful


			} catch (NoSuchElementException | TimeoutException e) {
				// If element is not found or click times out, increment retry count and try again
				retryCount++;
				System.out.println("Attempt " + retryCount + ": Failed to find or click the button. Retrying...");
				Thread.sleep(1000); // Wait for 1 second before retrying
			}
		}

		if (!success) {
			System.out.println("Maximum retries reached. Unable to find and click the button.");
		}
		driver.navigate().refresh();
		// Find the input field CustomerName by ID and send keys "Shoaib"
		WebElement customerNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id=\"CustomerName\"]")));
		customerNameInput.sendKeys("Shoaib");

		// Find the input field cardEmailAddress and send keys "shoaibyazdani7@gmail.com"
		WebElement cardEmailAddressInput = driver.findElement(By.id("cardEmailAddress"));
		cardEmailAddressInput.sendKeys("shoaibyazdani7@gmail.com");
		//		} else {
		// Handle the case where the title_textview does not have the expected text
		System.out.println("Title is not 'Payment Method'");
		//		}
	}

	private static final String[] FIRST_NAMES = {"John", "Emily", "Michael", "Sophia", "David", "Emma", "Daniel", "Olivia", "Matthew", "Ava"};
	private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson"};
	private static final Random RANDOM = new Random();



	public static String generateRandomName() {

		String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
		String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
		return firstName + " " + lastName;
	}
	public void BookLab() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Wait for the Labs Tile element and click on it
		WebElement labsTile = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/labFragment")));
		labsTile.click();

		//         // Wait for the Upload Prescription element and click on it
		//         WebElement uploadPrescription = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/uploadPrescription")));
		//         uploadPrescription.click();

		// Wait for the Permission Allow Foreground Only Button element and click on it
		//         WebElement permissionAllow = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")));
		//         permissionAllow.click();

		// Assuming driver is already initialized


		// Perform actions where you want to disable idle wait
		WebElement selectTestChughtai = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.widget.TextView[@resource-id='com.eshaafi.patient.consultation:id/selectTest'])[1]")));
		selectTestChughtai.click();
		Thread.sleep(5000);

		WebElement Plustest = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AddTest)));
		Plustest.click();
		WebElement viewcart = wait.until(ExpectedConditions.elementToBeClickable(By.id(ViewCart)));
		viewcart.click();
		WebElement Proceed = wait.until(ExpectedConditions.elementToBeClickable(By.id(Proceedtocheckout)));
		Proceed.click();

		WebElement Labvisit = wait.until(ExpectedConditions.elementToBeClickable(By.id(LabVisitId)));
		Labvisit.click();

		WebElement Placeorder =wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PlaceOrderxpath))); // No expected condition
		Placeorder.click();

		WebElement backBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BackButton)));
		backBtn.click();
		
		 test.log(Status.PASS, "Lab Booking Flow");
	}

	public void selectRandomTest() {
		// Get the size of the screen

		//swipeUp();

		// After scrolling, get all the visible plus buttons
		List<WebElement> visiblePlusButtons = driver.findElements(By.xpath("//android.widget.ImageView[@resource-id='com.eshaafi.patient.consultation:id/plus']"));

		Random random = new Random();

		// Ensure you have at least 3 visible "plus" buttons, otherwise adjust the range accordingly
		int maxIndex = Math.min(visiblePlusButtons.size(), 3); // 3 or the number of visible plus buttons, whichever is smaller

		// Generate a random index between 1 and the smaller of 3 or the list size (subtract 1 because list is 0 indexed)
		int randomIndex = 1 + random.nextInt(maxIndex); // This will generate a random number between 1 and 3

		// Adjust index for zero-based indexing used by lists
		int adjustedIndex = randomIndex - 1;

		// Click the plus button at the adjusted random index
		visiblePlusButtons.get(adjustedIndex).click();
	}






}

//@AfterClass
//public void TearDown() {
//driver.quit();
//service.stop();
//}


