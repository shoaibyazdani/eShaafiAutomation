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

            java.util.Scanner errorScanner = new java.util.Scanner(process.getErrorStream()).useDelimiter("\\A");
            String errorOutput = errorScanner.hasNext() ? errorScanner.next() : "";

            if (!output.isEmpty()) {
                System.out.println("Output: " + output);
            }

            if (!errorOutput.isEmpty()) {
                System.out.println("Error: " + errorOutput);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


	

