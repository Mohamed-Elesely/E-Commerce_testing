import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PracticePageSelection;

import java.time.Duration;

@Listeners(AllureTestNg.class)
@Feature("E-commerce")
public class EcommerceNavigationTest extends BaseTest {

    @Test
    @Story("Navigate to E-commerce Page")
    @Description("Verify that the user can navigate from the home page to the E-commerce practice page.")
    public void testNavigateToEcommercePage() {

        HomePage homePage = new HomePage(driver);
        homePage.clickStartPracticingButton();

        PracticePageSelection selectionPage =
                new PracticePageSelection(driver);
        selectionPage.clickStartEcommerceButton();

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        boolean navigated = wait.until(
                ExpectedConditions.urlContains(
                        "practice-ecommerece-website"
                )
        );

        Assert.assertTrue(
                navigated,
                "Navigation to Ecommerce page failed"
        );
    }
}
