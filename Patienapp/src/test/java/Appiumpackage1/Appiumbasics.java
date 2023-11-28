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
    public void AppiumTest() throws MalformedURLException {
        

    	
    	
    	
//		***************************************** Regression Testing *******************************************************************

		if (signup("3063381177", "999999")) {
		    		
//		    		NewProfile();
		    	//Allow all Permissions	
		    		allowpermissions();
		    		
		    		Logout();
		    		
		    		Login("3063381177", "999999");
		    		
		    	//How many slots you want to book
		    		BookNow(2);
		    		
		    	//NewProfile("Shoaib");
		    		
		    	//Call Instant Call Method
		    		instantcall();
		    		
		    	//Check user's balance and record it
		    		checkwallet();
		    		
		    	//Logout flow
		    		Logout();
		    		
		    	       	}
    	
    	
    	
//    	newScenario();
//    	NewProfile();
    	
    	
//    	if (signup("3068981891", "999999")) {
    		
    	//	NewProfile();
////    	//Allow all Permissions	
//    		allowpermissions ();
    		
//    		Logout();
    		
//    		Login("3068981891", "999999");
////    		
////    	//How many slots you want to book
////    		BookNow(1);
//    		
//    		//NewProfile("Shoaib");
//    		
//    	//Call Instant Call Method
//    		instantcall();
//    		
//    	//Check user's balance and record it
//    		checkwallet();
//    		
//    	//Logout flow
    	    
//    	       	}

    	    
    	
		    	//Login Using Method in BaseTest
//		    	 	 Login("3066163246","999999");
		
		    	 	
		    	 	

		    	 	 
		    	 	 
		    	
		    	//Call Instant Call Method
		    	//	instantcall();
		    	
		    	
		    	//Call Book now Method
		    		//BookNow(5);
		    		
		    	//Check user's balance and record it
		    	//	checkwallet();
		    		
		    	//Logout flow
		    	//	Logout();

    	
    	//NewProfile("Shoaib");
        
    	//newScenario();
    	
//    	BookNowSub(1);
//    	Subscription();
// 
    
    	

    	
    	
        
//   
    }
}
