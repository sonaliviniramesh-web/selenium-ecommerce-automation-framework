package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductPageAE;
import pages.SignupPageAE;

import java.util.UUID;

public class LoginTestAE extends BaseTest {

    private static String uniqueEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@testmail.com";
    }

    @Test
    public void completeFlowTest() {
        String email    = uniqueEmail();
        String password = "Test@123";

        SignupPageAE signupPage = new SignupPageAE(driver);

        signupPage.signup("TestUser", email);
        signupPage.fillSignupForm(
                password, "Test", "User",
                "Street 1", "India", "Karnataka",
                "Bangalore", "560001", "9999999999"
        );

        Assert.assertTrue(
                signupPage.isUserLoggedIn(),
                "Signup failed — 'Logged in as' not visible"
        );

        driver.findElement(
                org.openqa.selenium.By.xpath("//a[contains(text(),'Logout')]")
        ).click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("login"),
                "Logout failed — URL does not contain 'login'"
        );
    }

    @Test
    public void addToCartTest() {
        String email    = uniqueEmail();
        String password = "Test@123";

        SignupPageAE signupPage    = new SignupPageAE(driver);
        ProductPageAE productPage = new ProductPageAE(driver);

        signupPage.signup("CartUser", email);
        signupPage.fillSignupForm(
                password, "Cart", "User",
                "Street 1", "India", "Karnataka",
                "Bangalore", "560001", "9999999999"
        );

        Assert.assertTrue(
                signupPage.isUserLoggedIn(),
                "Signup failed before cart test"
        );

        productPage.openProducts();
        productPage.addProductToCart(1);
        productPage.clickContinue();
        productPage.clickViewCart();

        Assert.assertTrue(
                productPage.isProductDisplayed("Blue Top"),
                "Product not found in cart!"
        );
    }
}