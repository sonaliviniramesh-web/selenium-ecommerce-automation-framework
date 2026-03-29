package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SignupPageAE extends BasePage {

    private final By loginSignupBtn   = By.xpath("//a[contains(text(),'Signup / Login')]");
    private final By nameField        = By.xpath("//input[@data-qa='signup-name']");
    private final By emailField       = By.xpath("//input[@data-qa='signup-email']");
    private final By signupButton     = By.xpath("//button[@data-qa='signup-button']");
    private final By emailExistsError = By.xpath("//p[contains(text(),'Email Address already exist')]");

    private final By titleMr          = By.id("id_gender1");
    private final By passwordField    = By.id("password");
    private final By days             = By.id("days");
    private final By months           = By.id("months");
    private final By years            = By.id("years");
    private final By newsletterChk    = By.id("newsletter");
    private final By offersChk        = By.id("optin");
    private final By firstName        = By.id("first_name");
    private final By lastName         = By.id("last_name");
    private final By address1         = By.id("address1");
    private final By country          = By.id("country");
    private final By state            = By.id("state");
    private final By city             = By.id("city");
    private final By zipcode          = By.id("zipcode");
    private final By mobileNumber     = By.id("mobile_number");
    private final By createAccountBtn = By.xpath("//button[@data-qa='create-account']");
    private final By continueBtn      = By.xpath("//a[contains(text(),'Continue')]");
    private final By loggedInUser     = By.xpath("//a[contains(text(),'Logged in as')]");

    public SignupPageAE(WebDriver driver) {
        super(driver);
    }

    public void signup(String name, String email) {
        wait.waitForElementClickable(loginSignupBtn).click();
        type(nameField, name);
        type(emailField, email);
        wait.waitForElementClickable(signupButton).click();

        try {
            WebElement error = driver.findElement(emailExistsError);
            if (error.isDisplayed()) {
                throw new RuntimeException("Email already registered: " + email);
            }
        } catch (NoSuchElementException ignored) {}
    }

    public void fillSignupForm(
            String userPassword,
            String userFirstName,
            String userLastName,
            String userAddress,
            String userCountry,
            String userState,
            String userCity,
            String userZip,
            String userMobile
    ) {
        // Remove ads at the start of the form
        removeAds();

        wait.waitForElementClickable(titleMr).click();
        type(passwordField, userPassword);

        new Select(wait.waitForElementVisible(days)).selectByValue("10");
        new Select(wait.waitForElementVisible(months)).selectByVisibleText("May");
        new Select(wait.waitForElementVisible(years)).selectByValue("1995");

        checkIfUnchecked(newsletterChk);
        checkIfUnchecked(offersChk);

        // Remove ads again before filling address section — ads reload as you scroll
        removeAds();

        type(firstName, userFirstName);
        type(lastName, userLastName);
        type(address1, userAddress);
        new Select(wait.waitForElementVisible(country)).selectByVisibleText(userCountry);
        type(state, userState);
        type(city, userCity);
        type(zipcode, userZip);
        type(mobileNumber, userMobile);

        // Remove ads one final time before clicking Create Account
        removeAds();

        WebElement btn = wait.waitForElementVisible(createAccountBtn);
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        js.executeScript("arguments[0].click();", btn);

        // Wait up to 30s for account_created URL
        wait.waitForUrlContains("account_created", 30);

        // Remove ads on confirmation page then click Continue
        removeAds();
        WebElement continueEl = wait.waitForElementVisible(continueBtn);
        js.executeScript("arguments[0].click();", continueEl);
    }

    public boolean isUserLoggedIn() {
        try {
            return wait.waitForElementVisible(loggedInUser).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Scroll field into view, remove ads, then type
    private void type(By locator, String value) {
        WebElement el = wait.waitForElementVisible(locator);
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        removeAds();
        el.clear();
        el.sendKeys(value);
    }

    private void checkIfUnchecked(By locator) {
        WebElement checkbox = wait.waitForElementVisible(locator);
        if (!checkbox.isSelected()) {
            js.executeScript("arguments[0].click();", checkbox);
        }
    }
}