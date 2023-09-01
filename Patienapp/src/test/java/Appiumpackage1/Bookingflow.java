package Appiumpackage1;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.time.Duration;

public class Bookingflow extends BaseTest {
	
	@Test
	public void BookAppointment() throws MalformedURLException {
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        for (int i = 1; i <= 3; i++) {
            WebElement allowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_button")));
            allowButton.click();
        }
        
         wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    	// Wait for the element to be visible
    	WebElement BookNow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/book_appointments_button")));

    	// Click on Book Now
    	BookNow.click();
    	
    	
    	 wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Use XPath to locate the element and wait for it to be clickable
        WebElement BookAppBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.Button")));

        // Click the element
        BookAppBtn.click();
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Use XPath to locate the element and wait for it to be clickable
        WebElement Slot = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[1]/android.widget.TextView")));

        // Select Slot
        Slot.click();
    	
        
        
     // Click on Proceed Button
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    	// Wait for the element to be visible
    	WebElement ProceedBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/proceed_button")));

    	// Click on Book Now
    	ProceedBtn.click();
        
    	// Click on the switch element
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement switchElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.eshaafi.patient.consultation:id/switch1")));
        switchElement.click(); 
        
        
     // Click on Proceed Button
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    	// Wait for the element to be visible
    	WebElement ProceedBtn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/pay_now_button")));

    	// Click on Book Now
    	ProceedBtn1.click();
    	
    	//Click on Pay now Button
    	wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    	// Wait for the element to be visible
    	WebElement PayNowBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.Button")));

    	// Click on Pay Now
    	PayNowBtn.click();
		
		
	}


}
