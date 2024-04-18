package org.interview.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleFlightStepsDefinitions  {

    private WebDriver driver;

    private static final String EXPLORE_BUTTON_LABEL = "vLv7Lb";
    private static final String ORIGIN_INPUT_PLACEHOLDER_VALUE = "Where from?";
    private static final String DESTINATION_INPUT_PLACEHOLDER_VALUE = "Where to?";
    private static final String DEPARTURE_DATE_INPUT_PLACEHOLDER_VALUE = "Departure";
    private static final String RETURN_DATE_INPUT_PLACEHOLDER_VALUE = "Return";
    private static final String RESULT_PAGE_JSNAME = "Atyanb";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Public\\Nelly\\chromedriver.exe"); // Replace with the path to your chromedriver executable!
        driver = new ChromeDriver();
    }

    @Given("^The Google Travel Flight Search page loaded successfully$")
    public void theGoogleTravelFlightSearchPageLoadedSuccessfully() {
        driver.get("https://www.google.com/travel/flights");

        By locator = By.cssSelector("[jsname='" + EXPLORE_BUTTON_LABEL + "']");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    @And("Default ticket settings are used")
    public void defaultTicketSettingsAreUsed() {
        // found element value = Round trip, 1, Economy

    }

    @When("User enters valid {string} and {string} and {string} and {string}")
    public void userEntersValidOriginAndDestinationAndDepartureAndReturn(String origin, String destination, String departureDate, String returnDate) throws InterruptedException {
        // FIXME: Consider if better to use "ENTER" and "TAB" to navigate instead of going directly specific
        // FIXME: div/class name as this is too reliant on implementation knowledge - bad test practice!
/*
        WebElement divWrapperOrigin = driver.findElement(By.className(ORIGIN_INPUT_DIV_WRAPPER));
        WebElement input = divWrapperOrigin.findElement(By.cssSelector("[jsname='" + INPUT_JS_NAME + "']"));

        String script = "arguments[0].value = '" + origin + "';"; // set value using a script associated with the input element directly!
        ((JavascriptExecutor) driver).executeScript(script, input);

        WebElement divWrapperDestination = driver.findElement(By.className(DESTINATION_INPUT_DIV_WRAPPER));
        input = divWrapperDestination.findElement(By.cssSelector("[jsname='" + INPUT_JS_NAME + "']"));

        script = "arguments[0].value = '" + destination + "';"; // set value using a script associated with the input element directly!
        ((JavascriptExecutor) driver).executeScript(script, input);

//        WebElement divWrapperDepartureDate = driver.findElement(By.className(DEPARTURE_DATE_INPUT_DIV_WRAPPER));
//        WebElement departureDate = divWrapperDepartureDate.findElement(By.cssSelector("[jsname='" + DEPARTURE_DATE_JS_NAME + "']"));
        String dateValue = "2024-04-18";
//        input = divWrapperDepartureDate.findElement(By.cssSelector("[jsname='" + INPUT_JS_NAME + "'][placeholder='Departure']"));
        WebElement input2 = driver.findElement(By.cssSelector("[type='text'][placeholder='Return']"));
//        input.click();
//        script = "arguments[0].setAttribute('data-value', '" + dateValue + "')";
         script = "arguments[0].value ='" + dateValue + "';";
        ((JavascriptExecutor) driver).executeScript(script, input2);
*/
        WebElement input = driver.findElement(By.cssSelector("[type='text'][aria-label='" + ORIGIN_INPUT_PLACEHOLDER_VALUE + "']"));
        String script = "arguments[0].value ='" + origin + "';";
        ((JavascriptExecutor) driver).executeScript(script, input);
        input.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement originDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("DFGgtd")));

        // Click on the first suggestion from the autocomplete dropdown
        WebElement firstSuggestion = originDropdown.findElement(By.className("zsRT0d"));
        firstSuggestion.click();

        input = driver.findElement(By.cssSelector("[type='text'][placeholder='" + DESTINATION_INPUT_PLACEHOLDER_VALUE + "']"));
        script = "arguments[0].value ='" + destination + "';";
        ((JavascriptExecutor) driver).executeScript(script, input);
        input.click();
        wait = new WebDriverWait(driver, 10);
        originDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("DFGgtd")));

        // Click on the first suggestion from the autocomplete dropdown
        firstSuggestion = originDropdown.findElement(By.className("zsRT0d"));
        firstSuggestion.click();

        Thread.sleep(5000);

        input = driver.findElement(By.cssSelector("[type='text'][placeholder='" + DEPARTURE_DATE_INPUT_PLACEHOLDER_VALUE + "']"));
        script = "arguments[0].value ='" + departureDate + "';";
        ((JavascriptExecutor) driver).executeScript(script, input);
        input.sendKeys(Keys.ENTER);

        input = driver.findElement(By.cssSelector("[type='text'][placeholder='" + RETURN_DATE_INPUT_PLACEHOLDER_VALUE + "']"));
        script = "arguments[0].value ='" + returnDate + "';";
        ((JavascriptExecutor) driver).executeScript(script, input);
        input.sendKeys(Keys.ENTER);

    }

    @And("User selects Explore button")
    public void userSelectsExploreButton() throws InterruptedException {
        Thread.sleep(5000);
        WebElement exploreButton = driver.findElement(By.cssSelector("[jsname='" + EXPLORE_BUTTON_LABEL + "']"));
        exploreButton.click();
    }

    @Then("Flight results will be returned on the search results page")
    public void flightResultsWillBeReturnedAsExpected() throws InterruptedException {
        Thread.sleep(5000);
        WebElement searchResult = driver.findElement(By.xpath("//div[@class='FXkZv']//h2"));

        Assert.assertEquals("Search results", searchResult.getText());
        Assert.assertTrue((searchResult.findElement(By.xpath("//div[@class='HeMQ4'][@role='alert']")).getText()).contains("results returned"));

    }

    @After
    public void cleanUp() {
        // driver.close();
    }


}
