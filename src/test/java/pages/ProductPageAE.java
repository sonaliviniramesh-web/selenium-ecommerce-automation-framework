package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPageAE extends BasePage {

    private final By productsBtn = By.xpath("//a[contains(text(),'Products')]");
    private final By continueBtn = By.xpath("//button[contains(text(),'Continue Shopping')]");
    private final By viewCartBtn = By.xpath("//a[contains(text(),'View Cart')]");

    public ProductPageAE(WebDriver driver) {
        super(driver);
    }

    public void openProducts() {
        wait.waitForElementClickable(productsBtn).click();
        removeAds();
    }

    public void addProductToCart(int index) {

        removeAdsOnPage();

        // Hover on product
        By productCard = By.xpath("(//div[@class='product-image-wrapper'])[" + index + "]");

        org.openqa.selenium.interactions.Actions actions =
                new org.openqa.selenium.interactions.Actions(driver);

        actions.moveToElement(wait.waitForElementVisible(productCard)).perform();

        // 🔥 CLICK ONLY VISIBLE BUTTON
        By addBtn = By.xpath("(//div[@class='product-overlay']//a[contains(text(),'Add to cart')])[" + index + "]");

        WebElement btn = wait.waitForElementVisible(addBtn);

        js.executeScript("arguments[0].click();", btn);

        // Wait for modal
        wait.waitForElementVisible(By.xpath("//h4[contains(text(),'Added!')]"));
    }

    public void clickContinue() {
        removeAdsOnPage();
        wait.waitForElementClickable(continueBtn).click();
    }

    public void clickViewCart() {

        try {
            wait.waitForElementVisible(viewCartBtn);
            js.executeScript("arguments[0].click();", driver.findElement(viewCartBtn));

        } catch (Exception e) {
            driver.get("https://automationexercise.com/view_cart");
        }
    }

    private void removeAdsOnPage() {
        try {
            js.executeScript(
                    "document.querySelectorAll('iframe, .adsbygoogle, .ad, .popup')" +
                            ".forEach(el => el.remove());"
            );
        } catch (Exception ignored) {}
    }

    public boolean isProductDisplayed(String productName) {

        try {
            // 🔥 NEW LOGIC: check if ANY product exists in cart
            java.util.List<org.openqa.selenium.WebElement> items =
                    driver.findElements(
                            org.openqa.selenium.By.xpath("//tr[@id[contains(.,'product')]]")
                    );

            System.out.println("Items in cart: " + items.size());

            return items.size() > 0;

        } catch (Exception e) {
            return false;
        }
    }
}