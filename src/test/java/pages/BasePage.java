package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitUtils wait;
    protected final JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WaitUtils(driver);
        this.js     = (JavascriptExecutor) driver;
    }

    protected void removeAds() {
        try {
            js.executeScript(
                    "document.querySelectorAll(" +
                            "'iframe,.adsbygoogle,.ad,.popup,[id*=google],[class*=advert]')" +
                            ".forEach(el => el.remove());"
            );
        } catch (Exception ignored) {}
    }
}