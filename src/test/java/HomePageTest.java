import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

@Listeners(AllureTestNg.class)
@Feature("Home Page")
public class HomePageTest extends BaseTest {

    @Test
    @Story("Start Practicing Navigation")
    @Description("Verify that the user can click the Start Practicing button on the home page.")
    public void testClickStartPracticingButton() {

        HomePage homePage = new HomePage(driver);
        homePage.clickStartPracticingButton();
    }
}
