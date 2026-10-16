
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EcommercePage {

    private static final int WAIT_SECONDS = 10;
    private static final int MIN_PAGE = 1;
    private static final int MAX_PAGE = 3;

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ==================== General Locators ====================

    private final By cartButton =
            By.cssSelector("[data-testid='ecom-cart-button']");

    private final By cartBadge =
            By.cssSelector("[data-testid='ecom-cart-button'] .badge");

    private final By proceedToBuyButton =
            By.cssSelector("[data-testid='ecom-proceed-to-buy']");

    // ==================== Checkout Sections ====================

    private final By shippingAddressCard =
            By.xpath("//div[contains(@class,'card-body')]" +
                    "[.//h2[normalize-space()='Shipping Address']]");

    private final By paymentDetailsCard =
            By.xpath("//div[contains(@class,'card-body')]" +
                    "[.//h2[normalize-space()='Payment Details']]");

    private final By orderSuccessMessage =
            By.cssSelector("[data-testid='ecom-order-success']");

    // ==================== Shipping Address Locators ====================

    private final By fullNameInput =
            By.cssSelector("[data-testid='ecom-address-name']");

    private final By streetAddressInput =
            By.cssSelector("[data-testid='ecom-address-street']");

    private final By cityInput =
            By.cssSelector("[data-testid='ecom-address-city']");

    private final By stateInput =
            By.cssSelector("[data-testid='ecom-address-state']");

    private final By zipCodeInput =
            By.cssSelector("[data-testid='ecom-address-zip']");

    private final By saveAddressButton =
            By.cssSelector("[data-testid='ecom-save-address']");

    // ==================== Payment Locators ====================

    private final By cardNumberInput =
            By.cssSelector("[data-testid='ecom-card-number']");

    private final By expiryDateInput =
            By.cssSelector("[data-testid='ecom-expiry']");

    private final By cvvInput =
            By.cssSelector("[data-testid='ecom-cvv']");

    private final By buyNowButton =
            By.cssSelector("[data-testid='ecom-buy-now']");

    private final By doneButton =
            By.xpath("//button[normalize-space()='Done']");

    // ==================== Constructor ====================

    public EcommercePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(WAIT_SECONDS)
        );
    }

    // ==================== Dynamic Locators ====================

    private By getPageButton(int pageNumber) {
        return By.cssSelector(
                "[data-testid='ecom-page-" + pageNumber + "']"
        );
    }

    private By getAddToCartButton(int productId) {
        return By.cssSelector(
                "[data-testid='add-to-cart-" + productId + "']"
        );
    }

    private By getQuantityInput(int productId) {
        return By.cssSelector(
                "[data-testid='quantity-" + productId + "']"
        );
    }

    private By getRemoveButton(int productId) {
        return By.cssSelector(
                "[data-testid='remove-from-cart-" + productId + "']"
        );
    }

    // ==================== Reusable Helpers ====================

    private WebElement waitForClickable(By locator) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    private WebElement waitForVisible(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );
    }

    private void clickWhenReady(By locator) {
        WebElement element = waitForVisible(locator);
        scrollToElement(element);

        new Actions(driver)
                .moveToElement(element)
                .perform();

        waitForClickable(locator).click();
    }

    private WebElement getAddressInput(By locator) {
        waitForVisible(shippingAddressCard);
        return waitForClickable(locator);
    }

    private WebElement getPaymentInput(By locator) {
        waitForVisible(paymentDetailsCard);
        return waitForClickable(locator);
    }

    private void enterText(
            By locator,
            String fieldName,
            String value) {

        WebElement input = getAddressInput(locator);
        input.click();
        input.clear();

        getAddressInput(locator).sendKeys(value);

        verifyInputValue(locator, fieldName, value);
    }

    private void enterPaymentText(
            By locator,
            String fieldName,
            String value) {

        WebElement input = getPaymentInput(locator);
        input.click();
        input.clear();

        getPaymentInput(locator).sendKeys(value);

        verifyInputValue(locator, fieldName, value);
    }

    private void verifyInputValue(
            By locator,
            String fieldName,
            String expectedValue) {

        try {
            wait.until(currentDriver -> {
                WebElement element = currentDriver.findElement(locator);
                return expectedValue.equals(
                        element.getDomProperty("value")
                );
            });
        } catch (TimeoutException exception) {
            String actualValue = driver.findElement(locator)
                    .getDomProperty("value");

            throw new AssertionError(
                    "Field '" + fieldName +
                            "' expected <" + expectedValue +
                            "> but was <" + actualValue + ">",
                    exception
            );
        }
    }

    // ==================== Pagination ====================

    public void goToPage(int pageNumber) {
        if (pageNumber < MIN_PAGE || pageNumber > MAX_PAGE) {
            throw new IllegalArgumentException(
                    "Page number must be between " +
                            MIN_PAGE + " and " + MAX_PAGE
            );
        }

        clickWhenReady(getPageButton(pageNumber));

        By activePage = By.cssSelector(
                "li.active [data-testid='ecom-page-" +
                        pageNumber + "']"
        );

        waitForVisible(activePage);
    }

    // ==================== Product Quantity ====================

    public void setProductQuantity(int productId, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(
                    "Product quantity must be at least 1"
            );
        }

        By quantityLocator = getQuantityInput(productId);

        WebElement input = waitForVisible(quantityLocator);
        scrollToElement(input);

        input = waitForClickable(quantityLocator);
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(String.valueOf(quantity));
        input.sendKeys(Keys.TAB);

        wait.until(currentDriver ->
                String.valueOf(quantity).equals(
                        currentDriver.findElement(quantityLocator)
                                .getDomProperty("value")
                )
        );
    }

    public String getProductQuantity(int productId) {
        return waitForVisible(
                getQuantityInput(productId)
        ).getDomProperty("value");
    }

    // ==================== Cart Operations ====================

    public void addProductToCart(int productId) {
        clickWhenReady(getAddToCartButton(productId));
    }

    public void openCart() {
        clickWhenReady(cartButton);
    }

    public String getCartBadgeCount() {
        return waitForVisible(cartBadge).getText();
    }

    public void removeProductFromCart(int productId) {
        By removeButton = getRemoveButton(productId);

        clickWhenReady(removeButton);

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        removeButton
                )
        );
    }

    // ==================== Checkout ====================

    public void clickProceedToBuy() {
        clickWhenReady(proceedToBuyButton);
    }

    public void fillShippingAddress(
            String fullName,
            String street,
            String city,
            String state,
            String zipCode) {

        waitForVisible(shippingAddressCard);

        enterText(fullNameInput, "Full Name", fullName);
        enterText(streetAddressInput, "Street", street);
        enterText(cityInput, "City", city);
        enterText(stateInput, "State", state);
        enterText(zipCodeInput, "ZIP Code", zipCode);

        verifyInputValue(fullNameInput, "Full Name", fullName);
        verifyInputValue(streetAddressInput, "Street", street);
        verifyInputValue(cityInput, "City", city);
        verifyInputValue(stateInput, "State", state);
        verifyInputValue(zipCodeInput, "ZIP Code", zipCode);
    }

    public void saveAddressAndContinue() {
        clickWhenReady(saveAddressButton);

        waitForVisible(paymentDetailsCard);
        waitForClickable(cardNumberInput);
    }

    public void fillPaymentDetails(
            String cardNumber,
            String expiryDate,
            String cvv) {

        waitForVisible(paymentDetailsCard);

        enterPaymentText(
                cardNumberInput,
                "Card Number",
                cardNumber
        );

        enterPaymentText(
                expiryDateInput,
                "Expiry Date",
                expiryDate
        );

        enterPaymentText(
                cvvInput,
                "CVV",
                cvv
        );

        verifyInputValue(
                cardNumberInput,
                "Card Number",
                cardNumber
        );

        verifyInputValue(
                expiryDateInput,
                "Expiry Date",
                expiryDate
        );

        verifyInputValue(
                cvvInput,
                "CVV",
                cvv
        );
    }

    public void clickBuyNow() {
        clickWhenReady(buyNowButton);
    }

    public String getOrderSuccessMessage() {
        return waitForVisible(orderSuccessMessage).getText();
    }

    public void clickDoneButton() {
        clickWhenReady(doneButton);
    }
}
