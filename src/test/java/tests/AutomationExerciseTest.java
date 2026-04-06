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


    private static final String PASSWORD = "Test@123";

    private static String uniqueEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@testmail.com";
    }

    @Test(description = "Signup Test")
    public void signupTest() {

        String email = uniqueEmail();
        SignupPageAE signupPage = new SignupPageAE(driver);

        signupPage.signup("John Doe", email);
        signupPage.fillSignupForm(
                PASSWORD, "John", "Doe",
                "Street 1", "India", "Karnataka",
                "Bangalore", "560001", "9999999999"
        );

        Assert.assertTrue(
                signupPage.isUserLoggedIn(),
                "Signup failed — user not logged in"
        );
    }


    @Test(description = "Add to Cart Test")
    public void addToCartTest() {

        LoginPageAE loginPage = new LoginPageAE(driver);
        ProductPageAE productPage = new ProductPageAE(driver);

        loginPage.login("testuser@gmail.com", "123456");

        productPage.openProducts();
        productPage.addProductToCart(1);
        productPage.clickContinue();
        productPage.clickViewCart();

        Assert.assertTrue(
                productPage.isProductDisplayed("Blue Top"),
                "Cart verification failed — product not found"
        );
    }

    @Test(description = "Invalid Login Test")
    public void invalidLoginTest() {

        LoginPageAE loginPage = new LoginPageAE(driver);

        loginPage.login("wrong@email.com", "wrongpassword");

        Assert.assertTrue(
                loginPage.isLoginErrorDisplayed(),
                "Login error message not displayed"
        );
    }

}
