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
//		    		
		    		
		    	//Allow all Permissions	
		    		allowpermissions();		
	   		
		    	//How many slots you want to book
//		    		BookNowSub(1);
		    		
		    		BookNow(1);
		    		
		    	//Call Instant Call Method
		    		instantcall();
		    		
		    	//Check user's balance and record it
		    		checkwallet();
		    		
		    	//to Add and Delete Profile
		    		AddnDeleteProfile();
		    		
		    		Prescription();
		    		
		    	//Logout flow
		    		Logout();
		    		

		    		
		   	}
		
		
		
		
		
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
