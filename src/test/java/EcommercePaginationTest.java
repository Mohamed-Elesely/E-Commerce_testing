import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.EcommercePage;
import pages.HomePage;
import pages.PracticePageSelection;

import java.time.Duration;

@Listeners(AllureTestNg.class)
@Feature("E-commerce")
public class EcommercePaginationTest extends BaseTest {

    @Test
    @Story("Product Pagination")
    @Description("Verify that the user can navigate between product pages and that the selected page is displayed correctly.")
    public void testNavigateBetweenProductPages() {

        // Navigate to the E-commerce page
        HomePage homePage = new HomePage(driver);
        homePage.clickStartPracticingButton();

        PracticePageSelection selectionPage =
                new PracticePageSelection(driver);
        selectionPage.clickStartEcommerceButton();

        EcommercePage ecommercePage = new EcommercePage(driver);

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // Navigate to page 2
        ecommercePage.goToPage(2);

        By activePage2 = By.xpath(
                "//li[contains(@class,'active')]" +
                        "/button[@data-testid='ecom-page-2']"
        );

        wait.until(
                ExpectedConditions.presenceOfElementLocated(activePage2)
        );

        Assert.assertTrue(
                driver.findElement(activePage2).isDisplayed(),
                "Page 2 was not selected"
        );

        // Navigate to page 3
        ecommercePage.goToPage(3);

        By activePage3 = By.xpath(
                "//li[contains(@class,'active')]" +
                        "/button[@data-testid='ecom-page-3']"
        );

        wait.until(
                ExpectedConditions.presenceOfElementLocated(activePage3)
        );

        Assert.assertTrue(
                driver.findElement(activePage3).isDisplayed(),
                "Page 3 was not selected"
        );
    }
}
