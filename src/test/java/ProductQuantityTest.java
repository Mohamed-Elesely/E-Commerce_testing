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
public class ProductQuantityTest extends BaseTest {

    @Test
    @Story("Product Quantity Validation")
    @Description("Verify that the expected quantities can be set and retrieved for selected products across all three product pages.")
    public void testProductQuantitiesAcrossAllPages() {

        HomePage homePage = new HomePage(driver);
        homePage.clickStartPracticingButton();

        PracticePageSelection selectionPage =
                new PracticePageSelection(driver);
        selectionPage.clickStartEcommerceButton();

        EcommercePage ecommercePage = new EcommercePage(driver);

        // Page 1: Products 2 and 7
        ecommercePage.goToPage(1);

        testQuantity(ecommercePage, 2, 3);
        testQuantity(ecommercePage, 7, 2);

        // Page 2: Products 10, 13 and 16
        ecommercePage.goToPage(2);

        testQuantity(ecommercePage, 10, 4);
        testQuantity(ecommercePage, 13, 2);
        testQuantity(ecommercePage, 16, 3);

        // Page 3: Product 19
        ecommercePage.goToPage(3);

        testQuantity(ecommercePage, 19, 5);
    }

    private void testQuantity(
            EcommercePage ecommercePage,
            int productId,
            int expectedQuantity
    ) {

        ecommercePage.setProductQuantity(
                productId,
                expectedQuantity
        );

        String actualQuantity =
                ecommercePage.getProductQuantity(productId);

        Assert.assertEquals(
                actualQuantity,
                String.valueOf(expectedQuantity),
                "Incorrect quantity for product " + productId
        );
    }
}
