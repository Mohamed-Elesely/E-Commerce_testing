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
public class AddToCartTest extends BaseTest {

    @Test
    @Story("Add and Remove Products from Cart")
    @Description("Verify that products can be added to the cart, removed from the cart, and the user can proceed to buy.")
    public void testAddRemoveProductsAndProceedToBuy() {

        // Open the E-commerce page
        HomePage homePage = new HomePage(driver);
        homePage.clickStartPracticingButton();

        PracticePageSelection selectionPage =
                new PracticePageSelection(driver);
        selectionPage.clickStartEcommerceButton();

        EcommercePage ecommercePage = new EcommercePage(driver);

        // Page 1: Add product 3
        ecommercePage.setProductQuantity(3, 1);
        ecommercePage.addProductToCart(3);

        // Page 2: Add product 10
        ecommercePage.goToPage(2);
        ecommercePage.setProductQuantity(10, 1);
        ecommercePage.addProductToCart(10);

        // Page 3: Add product 18
        ecommercePage.goToPage(3);
        ecommercePage.setProductQuantity(18, 1);
        ecommercePage.addProductToCart(18);

        // Verify that 3 products were added
        Assert.assertEquals(
                ecommercePage.getCartBadgeCount(),
                "3",
                "Cart should contain 3 products"
        );

        // Open the cart
        ecommercePage.openCart();

        // Remove product 10
        ecommercePage.removeProductFromCart(10);

        // Verify that 2 products remain
        Assert.assertEquals(
                ecommercePage.getCartBadgeCount(),
                "2",
                "Cart should contain 2 products after removal"
        );

        // Proceed to buy
        ecommercePage.clickProceedToBuy();
    }
}
