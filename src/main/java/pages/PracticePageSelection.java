
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class PracticePageSelection {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By startEcommerceButton =
            By.cssSelector("a[href='/practice-ecommerece-website']");

    public PracticePageSelection(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
        );
    }

    public void clickStartEcommerceButton() {

        WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        startEcommerceButton
                )
        );

        new Actions(driver)
                .moveToElement(button)
                .perform();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        startEcommerceButton
                )
        ).click();
    }
}
