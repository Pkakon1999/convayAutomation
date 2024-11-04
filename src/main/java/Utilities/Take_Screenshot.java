package Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Take_Screenshot {

	public static void TakeScreenshot(WebDriver driver, String screenshotname) throws IOException {
		// Get the current date and time in the desired format
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		// Create the file name with date and time
		String filename = screenshotname + "_" + timestamp + ".png";

		File screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotfile, new File("./Screenshots/" + filename));
		return;
	}

}