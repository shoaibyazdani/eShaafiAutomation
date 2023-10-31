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
        

    	//NewProfile("Shoaib");
    	
    	
    	
//    	if (Login("3066163246", "999999")) {
    	//How many slots you want to book
//    		BookNow(2);
//    		NewProfile("Shoaib");
    		instantcall();
//    		checkwallet();
//    	    Logout();
    	    
//    	}

    	    
    	
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
//    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//    	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
//	            By.xpath("//androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[1]/android.widget.TextView")));
//        boolean isEnabled = element.isEnabled();
//        if (isEnabled) {
//            System.out.println("The button is enabled.");
//            // Perform actions when the button is enabled
//        } else {
//            System.out.println("The button is disabled.");
//            // Perform actions when the button is disabled
//        }
        
		//toast();
    	
    
    	

    	
    	
        
//     // Handle permissions dialogs
//    	WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        for (int i = 1; i <= 3; i++) {
//            WebElement allowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_button")));
//            allowButton.click();
//        }
//        
//     // Click on the "Instant Call" button
//    	WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        WebElement InstantCallBtn = driver.findElement(By.id("com.eshaafi.patient.consultation:id/guide"));
//        InstantCallBtn.click();
//   
//     // Wait for the "Proceed" button to appear and then click it
//           wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/proceed_button")));
//        proceedButton.click();
//
//        
//        
//        
//        
//        
//     // Click on the switch element
//        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        WebElement switchElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/switch1")));
//        switchElement.click(); 
//
//        
//
//        
//        
//        
//        // Click on the "Pay Now" button
//        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        WebElement payNowButton = driver.findElement(By.id("com.eshaafi.patient.consultation:id/pay_now_button"));
//        payNowButton.click(); */
    }
}
