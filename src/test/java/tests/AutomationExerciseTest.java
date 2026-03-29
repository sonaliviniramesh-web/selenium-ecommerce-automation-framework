package tests;

import base.BaseTest;
import pages.LoginPageAE;
import pages.ProductPageAE;
import pages.SignupPageAE;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import listeners.TestListener;
import java.util.UUID;

@Listeners(TestListener.class)

public class AutomationExerciseTest extends BaseTest {

    private static final String PASSWORD     = "Test@123";
    private static final String PRODUCT_NAME = "Blue Top";

    private static String uniqueEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@testmail.com";
    }


    @Test(description = "Signup then logout")
    public void fullFlowTest() throws InterruptedException {
        String email = uniqueEmail();

        SignupPageAE signupPage = new SignupPageAE(driver);
        LoginPageAE  loginPage  = new LoginPageAE(driver);

        signupPage.signup("John Doe", email);
        signupPage.fillSignupForm(
                PASSWORD, "John", "Doe",
                "Street 1", "India", "Karnataka",
                "Bangalore", "560001", "9999999999"
        );
        Thread.sleep(2000);

        Assert.assertTrue(
                signupPage.isUserLoggedIn(),
                "Signup failed — user not logged in"
        );

        loginPage.clickLogout();

        loginPage.login(email, PASSWORD);
        Thread.sleep(2000);
        Assert.assertTrue(
                loginPage.isUserLoggedIn(),
                "Login failed after signup"
        );

        ProductPageAE productPage = new ProductPageAE(driver);
        productPage.openProducts();
        productPage.addProductToCart(1);
        productPage.clickContinue();
        productPage.clickViewCart();

        Assert.assertTrue(
                productPage.isProductDisplayed(PRODUCT_NAME),
                "Cart verification failed — product not found"
        );

        loginPage.clickLogout();
        Assert.assertFalse(
                loginPage.isUserLoggedIn(),
                "Logout failed — user still logged in"
        );
    }

    @Test(description = "Invalid login shows error")
    public void invalidLoginTest() {
        LoginPageAE loginPage = new LoginPageAE(driver);
        loginPage.login("wrong@email.com", "wrongpassword");
        Assert.assertTrue(
                loginPage.isLoginErrorDisplayed(),
                "Login error message not displayed"
        );
    }
}