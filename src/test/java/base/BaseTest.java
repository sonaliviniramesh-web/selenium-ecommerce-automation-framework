package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BaseTest {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driverThread.set(driver);
        driver.get(ConfigReader.getProperty("url"));
        removeAds();
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public void removeAds() {
        try {
            js().executeScript(
                    "document.querySelectorAll(" +
                            "'iframe,.adsbygoogle,.ad,.popup,[id*=google],[class*=advert]')" +
                            ".forEach(el => el.remove());"
            );
        } catch (Exception ignored) {}
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }

    public static WebDriver getStaticDriver() {
        return driverThread.get();
    }

    protected JavascriptExecutor js() {
        return (JavascriptExecutor) driver;
    }

    protected void takeScreenshot(String fileName) {
        try {
            File folder = new File("screenshots");
            if (!folder.exists()) folder.mkdirs();
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(),
                    Paths.get("screenshots/" + fileName + ".png"),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Screenshot failed: " + e.getMessage());
        }
    }
}