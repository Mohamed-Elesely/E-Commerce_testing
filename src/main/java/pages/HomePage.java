package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;

    private final By startPracticingButton =
            By.cssSelector("a[href='/practice-page-selection']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickStartPracticingButton() {
        driver.findElement(startPracticingButton).click();
    }
}
