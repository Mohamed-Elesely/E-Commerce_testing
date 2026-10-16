# E-commerce Website Test Automation

A UI test automation project built with **Java, Selenium WebDriver,
TestNG, Maven, and Page Object Model (POM)**. The project automates key
user journeys on the practice e-commerce website provided by
[QAPractice](https://www.qapractice.com/).

## Project Goals

-   Practice maintainable UI automation using the Page Object Model.
-   Validate common e-commerce user flows.
-   Organize and execute tests with TestNG.
-   Generate readable test reports with Allure.
-   Keep tests reusable through a shared `BaseTest` setup.

## Tech Stack

-   **Java 17**
-   **Selenium WebDriver**
-   **TestNG**
-   **Maven**
-   **Allure Report**
-   **Page Object Model (POM)**
-   **Chrome / ChromeDriver**

## Automated Test Scenarios

  -----------------------------------------------------------------------
  Test Class                          Scenario
  ----------------------------------- -----------------------------------
  `HomePageTest`                      Verify navigation using the **Start
                                      Practicing** button.

  `EcommerceNavigationTest`           Verify navigation to the e-commerce
                                      practice page.

  `EcommercePaginationTest`           Verify navigation between product
                                      pages.

  `ProductQuantityTest`               Verify that selected product
                                      quantities can be updated and read
                                      correctly across all three product
                                      pages.

  `AddToCartTest`                     Verify adding products from
                                      different pages, removing a product
                                      from the cart, and proceeding to
                                      checkout.

  `CheckoutTest`                      Verify the checkout flow, including
                                      shipping details, payment form
                                      submission, and the success
                                      message.
  -----------------------------------------------------------------------

### Product Pagination Coverage

The e-commerce page contains **20 products across 3 pages**:

-   Page 1: Products 1--8
-   Page 2: Products 9--16
-   Page 3: Products 17--20

The tests use product IDs to target products across the different pages.

## Project Structure

The exact package names may vary depending on your project setup. The
main components are:

``` text
project-root/
├── src/
│   └── test/
│       └── java/
│           ├── base/
│           │   └── BaseTest.java
│           ├── pages/
│           │   ├── HomePage.java
│           │   ├── PracticePageSelection.java
│           │   └── EcommercePage.java
│           └── tests/
│               ├── HomePageTest.java
│               ├── EcommerceNavigationTest.java
│               ├── EcommercePaginationTest.java
│               ├── ProductQuantityTest.java
│               ├── AddToCartTest.java
│               └── CheckoutTest.java
├── testng.xml
├── pom.xml
└── README.md
```

## Prerequisites

Install or configure the following before running the tests:

1.  **JDK 17** or a compatible Java version.
2.  **Maven** (or Maven support through your IDE).
3.  **Google Chrome**.
4.  **ChromeDriver** compatible with your installed Chrome version,
    unless your Selenium setup manages the driver automatically.
5.  **Allure Commandline**, if you want to open the generated Allure
    report from the terminal.

## How to Run

### Run the TestNG suite from IntelliJ IDEA

1.  Open the project in IntelliJ IDEA.
2.  Allow Maven to import the project dependencies.
3.  Open `testng.xml`.
4.  Right-click the file and select **Run**.

The class order in `testng.xml` can be used to control the suite
execution order.

### Run with Maven

From the project root, run:

``` bash
mvn clean test
```

To run the suite through Maven, make sure your `pom.xml` is configured
to use the intended TestNG suite file. If Maven is not available in your
terminal, run the `clean` and `test` lifecycle goals from the Maven tool
window in your IDE.

## Allure Reporting

The test classes use Allure metadata such as:

-   `@Feature` to group related tests.
-   `@Story` to describe the user story or scenario.
-   `@Description` to explain the purpose of a test.

After running the tests, Allure result files should be generated in the
configured results directory, commonly `allure-results`.

If Allure Commandline is installed and configured, open the report with:

``` bash
allure serve allure-results
```

This command generates and opens a local report from the latest results.
The exact results directory depends on the Allure configuration in the
project.

## Design Approach

-   **Page Object Model:** Page classes encapsulate page locators and
    user actions.
-   **Shared test setup:** `BaseTest` provides common WebDriver setup
    and teardown.
-   **Explicit waits:** Waits are used where needed to allow page
    elements and navigation to become ready.
-   **Assertions:** TestNG assertions validate expected results.
-   **Test metadata:** Allure annotations make test results easier to
    navigate and understand.

## Notes

-   The website is a practice environment; its UI or behavior may change
    over time.
-   Checkout test data should be **dummy test data only**. Do not use
    real payment card details or private personal information.
-   Avoid committing API keys, passwords, browser profiles, generated
    reports, or other sensitive data.
-   Browser and driver versions may need to be updated when Chrome
    changes.

## Future Improvements

-   Attach screenshots automatically when a test fails.
-   Add more negative and edge-case scenarios.
-   Improve reporting and CI execution.
-   Run the suite in a CI pipeline such as GitHub Actions.

## Author

**QA Automation Portfolio Project**

Built to practice web UI automation and demonstrate foundational skills
in Java, Selenium WebDriver, TestNG, Maven, POM, and Allure reporting.
