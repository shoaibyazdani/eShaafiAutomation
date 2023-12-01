package Appiumpackage1;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import io.appium.java_client.android.Activity;

import java.net.MalformedURLException;
import java.time.Duration;

public class Appiumbasics extends BaseTest {
    
    @Test
    public void AppiumTest() throws MalformedURLException, InterruptedException {
        

//    	instantcall();
//    	AddNewProfile();
//    	BookNowSub(1);
    	
////		***************************************** Regression Testing *******************************************************************
//
		if (Login("3066163246", "99999")) {
//		    		
		    		
		    	//Allow all Permissions	
		    	//	allowpermissions();
		    		
		    		
		    		
//		    	//User will logout
		    		//Logout();
		    		
		   		// Login With new account details
		    		//Login("3066163246", "999999");
		    		
		    	//How many slots you want to book
		    		//BookNowSub(1);
		    		
		    	//Call Instant Call Method
		    		instantcall();
    	
    			//Medical Record Caputre image and upload
    				MedicalRecord();
		    		
		    	//Check user's balance and record it
		    		checkwallet();
		    		
		    	//Logout flow
		    		Logout();
		    		
		    		
		    	       	}
//    	
//  						**************List of all Methods**************
		
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
		
					//Medical Record Caputre image and upload
						//MedicalRecord();	
								
					//Check user's balance and record it		
						//checkwallet();
		
					//Logout flow
						//Logout();
					
    }
}
