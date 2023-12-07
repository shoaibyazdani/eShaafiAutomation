package Appiumpackage1;

import java.io.IOException;

/**
 * Enters the specified OTP using ADB Shell command.
 *
 * @ShoaibYazdani otp The OTP to be entered.
 */

public class AddOTP {
	 public static void enterText(String otp) {
	        try {
	            String adbCommand = "adb shell input text " + otp;
	            Process process = Runtime.getRuntime().exec(adbCommand);
	            process.waitFor();
	            java.util.Scanner s = new java.util.Scanner(process.getInputStream()).useDelimiter("\\A");
	            String output = s.hasNext() ? s.next() : "";
	            System.out.println(output);
	            
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	
}
