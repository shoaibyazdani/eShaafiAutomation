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
        

    	
    	//Login Using Method in BaseTest
    	 	 //Login("3066163246","999999");
    	
    	
    	//Call Instant Call Method
    		//instantcall();
    	
    	
    	//Call Book now Method
    		BookNow();
    		
    		//checkwallet();
    		
    		Logout();
    	
    	
    
    	

    	
    	/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    	// Wait for the element to be visible
    	WebElement createAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/create_account_button")));

    	// Click on the visible element
    	createAccountButton.click();
    	
    	WebElement phoneInput = driver.findElement(By.id("com.eshaafi.patient.consultation:id/phoneno_edittext"));
        phoneInput.sendKeys("3066163246");
    	
        // Find and click the "Continue" button
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/create_account_button")));
        continueButton.click();
        
     // Input OTP
        String otp = "999999"; // Your OTP
        for (int i = 0; i < otp.length(); i++) {
            WebElement otpField = driver.findElement(By.id("com.eshaafi.patient.consultation:id/otp" + (i + 1) + "_textview"));
            otpField.sendKeys(String.valueOf(otp.charAt(i)));
        } 
        
     // Handle permissions dialogs
    	WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        for (int i = 1; i <= 3; i++) {
            WebElement allowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_button")));
            allowButton.click();
        }
        
     // Click on the "Instant Call" button
    	WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement InstantCallBtn = driver.findElement(By.id("com.eshaafi.patient.consultation:id/guide"));
        InstantCallBtn.click();
   
     // Wait for the "Proceed" button to appear and then click it
           wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/proceed_button")));
        proceedButton.click();

        
        
        
        
        
     // Click on the switch element
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement switchElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/switch1")));
        switchElement.click(); 

        

        
        
        
        // Click on the "Pay Now" button
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement payNowButton = driver.findElement(By.id("com.eshaafi.patient.consultation:id/pay_now_button"));
        payNowButton.click(); */
    }
}
