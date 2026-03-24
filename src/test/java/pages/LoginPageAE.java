package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageAE extends BasePage {

    private final By loginSignupBtn = By.xpath("//a[contains(text(),'Signup / Login')]");
    private final By emailField     = By.xpath("//input[@data-qa='login-email']");
    private final By passwordField  = By.xpath("//input[@data-qa='login-password']");
    private final By loginButton    = By.xpath("//button[@data-qa='login-button']");
    private final By logoutButton   = By.xpath("//a[contains(text(),'Logout')]");
    private final By loggedInUser   = By.xpath("//a[contains(text(),'Logged in as')]");
    private final By loginError     = By.xpath(
            "//p[contains(text(),'incorrect') or contains(text(),'Invalid') or contains(text(),'invalid')]"
    );

    public LoginPageAE(WebDriver driver) {
        super(driver);
    }

    public void clickLoginSignup() {
        wait.waitForElementClickable(loginSignupBtn).click();
    }

    public void enterEmail(String email) {
        WebElement field = wait.waitForElementVisible(emailField);
        field.clear();
        field.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement field = wait.waitForElementVisible(passwordField);
        field.clear();
        field.sendKeys(password);
    }

    public void clickLogin() {
        wait.waitForElementClickable(loginButton).click();
    }

    public LoginPageAE login(String email, String password) {
        clickLoginSignup();
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        return this;
    }

    public boolean isUserLoggedIn() {
        return wait.waitForElementVisible(loggedInUser).isDisplayed();
    }

    public boolean isLoginErrorDisplayed() {
        return wait.waitForElementVisible(loginError).isDisplayed();
    }

    public void clickLogout() {
        wait.waitForElementClickable(logoutButton).click();
    }
}