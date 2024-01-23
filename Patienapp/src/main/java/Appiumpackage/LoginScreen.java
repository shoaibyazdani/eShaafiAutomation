package Appiumpackage;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginScreen {
	
	AndroidDriver driver;
	
	public LoginScreen (AndroidDriver driver) {
	
	this.driver = driver;
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//driver.findElement (By.id ("com.androidsample-generalstore: id/nameField")).sendKeys("Rahul. Shetty.");
	@AndroidFindBy (id="com.eshaafi.patient.consultation:id/phoneno_edittext") 
	private WebElement PhoneNofield;
	
	
	@AndroidFindBy (id="com.eshaafi.patient.consultation:id/create_account_button")
	private WebElement ContinueBtn;
	
	@AndroidFindBy (id="com.eshaafi.patient.consultation:id/otp1_textview")
	private WebElement Clickotp;
	
	@AndroidFindBy (id="com.eshaafi.patient.consultation:id/guide")
	private WebElement homeScreenElement;
	
	@AndroidFindBy (id="com.eshaafi.patient.consultation:id/error_textview")
	private WebElement ErrorMessage;
	
	@AndroidFindBy (xpath="(//android.widget.ImageView[@resource-id=\"com.eshaafi.patient.consultation:id/profile_image\"])[3]")
	private WebElement selectprofile;
	
	@AndroidFindBy (id="com.eshaafi.patient.consultation:id/corporate_login")
	private WebElement CorporateBtn;
	
	
	
	
	
	
	public void enterphoneNo(String Phone) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(PhoneNofield));
		
		PhoneNofield.sendKeys(Phone);
		System.out.println("Entring Phone No.");
		}
	
	
	public void ClickContinue() {
		
		ContinueBtn.click();
		}
	
	public void Clickotpfield() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(Clickotp));
		Clickotp.click();
		System.out.println("OTP Entered");
		
	}
	
	
	public void SelectProfile() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(selectprofile));
        selectprofile.click();
		System.out.println("Profile Selected");
	}
	
	
	

}
