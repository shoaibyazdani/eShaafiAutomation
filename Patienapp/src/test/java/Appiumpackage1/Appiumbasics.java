package Appiumpackage1;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import io.appium.java_client.android.Activity;

import java.net.MalformedURLException;
import java.time.Duration;
import Appiumpackage.LoginScreen;

public class Appiumbasics extends BaseTest {

	@Test
	public void AppiumTest() throws MalformedURLException, InterruptedException {

		LoginScreen loginscreen = new LoginScreen(driver);

		//    	

		//		***************************************** Regression Testing *******************************************************************

		//			
		//					
	


					if (Login()) {
//		//		    		
//
//		//		    	//Allow all Permissions	
//		//		    		allowpermissions();		
////			   		clock();
		//		    	//How many slots you want to book
							BookNow(1);
							BookNowSub(1);
//		////		    	CorporateLogin()
//		////		    			    		
//		//		    		
//		//////		    //Call Instant Call Method
				    		instantcall();
//		////		    		
				    		MedicalRecord();
//		////		    		
//		////		    	//Check user's balance and record it
				    		checkwallet();
//		////		    		
//		//////		    	//to Add and Delete Profile
				    		AddnDeleteProfile();
//		//		    		
				    		Prescription();
//		//		    		
//		//		    		
//		////		    		
//		////		    	//Logout flow
		    					SideMenu();
//		//////		    		Logout();
//		////		    		
//		//////		    		CorporateLogin();
//		////		    		
//		//////		    		Logout();
//		////		    		
//		//////		    		driver.navigate().back();
//		////		    		
				    		Signup("999999");
//				    		Subscription();
//		////		    		
				    		referal();
//
//
//		//		    		BookNowSub(1);
//
//

				   	}
		//		




		//  		**************List of all Methods**************

		// Login With new account details
		//Login("3066163246", "999999");

		//Logout flow
		//Logout();

		//Book now Method with wallet change Value as per your need
		//BookNow(1);

		//Book now Method with Subscription Plan change Value as per your need
		//BookNowSub(1);

		//Call Instant Call Method
		//instantcall();

		//Buy Subscription plan Using JAzzcash
		//Subscription();

		//Medical Record Caputre image and upload
		//MedicalRecord();	

		//Check user's balance and record it		
		//checkwallet();

		//Logout flow
		//Logout();

	}
}
