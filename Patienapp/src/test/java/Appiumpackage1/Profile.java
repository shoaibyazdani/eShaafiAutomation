package Appiumpackage1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class Profile extends BaseTest {
	@Test
	public void AddNewProfile() throws InterruptedException {


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement sidemenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/left_imageview")));
		sidemenu.click();

		WebElement Profile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eshaafi.patient.consultation:id/profile_layout")));
		Profile.click();

		//add profile
		WebElement AddProfile = driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.eshaafi.patient.consultation:id/profile_image'][1]"));
		AddProfile.click();

		//Call this method when need to fill Signup form
		Sigupdata();

		//Relation drop down
		WebElement Relation = driver.findElement(By.xpath("(//android.widget.ImageButton[@content-desc=\"Show dropdown menu\"])[2]"));
		Relation.click();



		//Slect son from menu
		WebElement relation = driver.findElement(By.xpath("//android.widget.TextView[@text='Son']"));
		relation.click();

		//Save Data
		WebElement Save = driver.findElement(By.id("com.eshaafi.patient.consultation:id/save_button"));
		Save.click();   

	}
	@Test
	public void DeleteProfile() {

		//Change numeric value if need to change any other profile
		WebElement SelectProfile = driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id='com.eshaafi.patient.consultation:id/profile_image'])[2]"));
		SelectProfile.click();
		//Click Three dot Menu
		WebElement Threedot = driver.findElement(By.id("com.eshaafi.patient.consultation:id/menu_imageview"));
		Threedot.click();

		//Delete Button Click
		WebElement Delete= driver.findElement(By.id("com.eshaafi.patient.consultation:id/title"));
		Delete.click();

		//Yes Option Click
		WebElement Yes = driver.findElement(By.id("com.eshaafi.patient.consultation:id/leave_button"));
		Yes.click();
	}
}

