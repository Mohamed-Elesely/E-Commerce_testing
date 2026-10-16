
import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.EcommercePage;
import pages.HomePage;
import pages.PracticePageSelection;

@Listeners(AllureTestNg.class)
@Feature("E-commerce")
public class CheckoutTest extends BaseTest {

    @Test
    @Story("Checkout with Shipping Address")
    @Description("Verify that a user can add a product to the cart, enter shipping and payment details, place an order, and receive a success confirmation.")
    public void testCheckoutWithShippingAddress() {

        // Step 1: Navigate to the E-commerce page
        HomePage homePage = new HomePage(driver);
        homePage.clickStartPracticingButton();

        PracticePageSelection selectionPage =
                new PracticePageSelection(driver);
        selectionPage.clickStartEcommerceButton();

        EcommercePage ecommercePage = new EcommercePage(driver);

        // Step 2: Add a product to the cart
        ecommercePage.setProductQuantity(3, 1);
        ecommercePage.addProductToCart(3);
        ecommercePage.openCart();

        Assert.assertEquals(
                ecommercePage.getCartBadgeCount(),
                "1",
                "Cart should contain one product."
        );

        // Step 3: Proceed to checkout
        ecommercePage.clickProceedToBuy();

        // Step 4: Fill shipping address
        ecommercePage.fillShippingAddress(
                "Test User",
                "123 Test Street",
                "Cairo",
                "Cairo",
                "11511"
        );

        ecommercePage.saveAddressAndContinue();

        // Step 5: Fill payment details
        ecommercePage.fillPaymentDetails(
                "4111111111111111",
                "12/28",
                "123"
        );

        // Step 6: Place the order
        ecommercePage.clickBuyNow();

        // Step 7: Verify successful order confirmation
        Assert.assertEquals(
                ecommercePage.getOrderSuccessMessage(),
                "Order Successful!",
                "Order confirmation message was not displayed."
        );

        ecommercePage.clickDoneButton();
    }
}
