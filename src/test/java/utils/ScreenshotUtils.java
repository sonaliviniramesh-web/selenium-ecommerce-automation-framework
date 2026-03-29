package utils;

import base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(String testName) {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = "screenshots/" + testName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) BaseTest.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            File dest = new File(path);
            FileHandler.copy(src, dest);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}