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
        removeAds();
        By addToCartBtn = By.xpath(
                "(//a[contains(text(),'Add to cart')])[" + index + "]"
        );
        WebElement btn = wait.waitForElementVisible(addToCartBtn);
        js.executeScript("arguments[0].scrollIntoView(true);", btn);
        js.executeScript("arguments[0].click();", btn);
    }

    public void clickContinue() {
        wait.waitForElementClickable(continueBtn).click();
    }

    public void clickViewCart() {
        removeAds();
        WebElement btn = wait.waitForElementVisible(viewCartBtn);
        js.executeScript("arguments[0].click();", btn);
    }

    public boolean isProductDisplayed(String productName) {
        By cartProduct = By.xpath(
                "//td[@class='cart_description']//p[text()='" + productName + "']"
        );
        return wait.waitForElementVisible(cartProduct).isDisplayed();
    }
}