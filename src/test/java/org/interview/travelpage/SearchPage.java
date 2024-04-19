package org.interview.travelpage;

import org.interview.acceptancetests.TestRunner;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    private WebDriver driver;

    private static final String EXPLORE_BUTTON_LABEL = "vLv7Lb";
    private static final String TICKET_JSNAME = "Fb0Bif";
    private static final String TICKET_TYPE_ID = "i7";
    private static final String NUMBER_OF_PASSENGERS_JS_NAME = "xAX4ff";
    private static final String TICKET_CLASS_ID = "i19";
    private static final String AIRPORT_LIST_CLASS = "DFGgtd";
    private static final String AIRPORT_SELECTION_CLASS = "zsRT0d";
    private static final String ORIGIN_INPUT_PLACEHOLDER_VALUE = "Where from?";
    private static final String DESTINATION_INPUT_PLACEHOLDER_VALUE = "Where to? "; // NB! Extra space.
    private static final String DEPARTURE_DATE_INPUT_PLACEHOLDER_VALUE = "Departure";
    private static final String RETURN_DATE_INPUT_PLACEHOLDER_VALUE = "Return";

    public SearchPage(int waitMS) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", TestRunner.CHROMEDRIVER);
        driver = new ChromeDriver();
        driver.get("https://www.google.com/travel/flights");
        Thread.sleep(waitMS);
    }

    public boolean hasExploreButton() {
        By locator = By.cssSelector("[jsname='" + EXPLORE_BUTTON_LABEL + "']");
        return !driver.findElements(locator).isEmpty() && driver.findElement(locator).isEnabled();
    }

    public String getTicketType() {
        By locator = By.cssSelector("[id='" + TICKET_TYPE_ID + "'][jsname='" + TICKET_JSNAME + "']");
        // System.out.println("FOUND NUMBER: " + driver.findElements(locator).size());
        return driver.findElement(locator).getText();
    }

    public String getNumberOfPassengers() {
        By locator = By.cssSelector("[jsname='" + NUMBER_OF_PASSENGERS_JS_NAME + "']");
        // System.out.println("FOUND NUMBER: " + driver.findElements(locator).size());
        return driver.findElement(locator).getText();
    }

    public String getTicketClass() {
        By locator = By.cssSelector("[id='" + TICKET_CLASS_ID + "'][jsname='" + TICKET_JSNAME + "']");
        // System.out.println("FOUND NUMBER: " + driver.findElements(locator).size());
        return driver.findElement(locator).getText();
    }

    public void setOrigin(String origin) throws InterruptedException {
        setAirportHelper(ORIGIN_INPUT_PLACEHOLDER_VALUE, origin);
    }

    public void setDestination(String destination) throws InterruptedException {
        setAirportHelper(DESTINATION_INPUT_PLACEHOLDER_VALUE, destination);
    }

    private void setAirportHelper(String airportLabel, String value) throws InterruptedException {
        WebElement input = driver.findElement(By.cssSelector("[type='text'][aria-label='" + airportLabel + "']"));
        // WebElement input = driver.findElement(By.cssSelector("[type='text'][placeholder='" + airportLabel + "']"));
        String script = "arguments[0].value ='" + value + "';";
        ((JavascriptExecutor) driver).executeScript(script, input);
        input.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(AIRPORT_LIST_CLASS)));

        // Click on the first suggestion from the autocomplete dropdown
        WebElement firstSuggestion = dropdown.findElement(By.className(AIRPORT_SELECTION_CLASS));
        firstSuggestion.click();
    }

    public void setDepartureDate(String date) throws InterruptedException {
        setDateHelper(DEPARTURE_DATE_INPUT_PLACEHOLDER_VALUE, date);
    }

    public void setReturnDate(String date) throws InterruptedException {
        setDateHelper(RETURN_DATE_INPUT_PLACEHOLDER_VALUE, date);
    }

    private void setDateHelper(String dateKind, String date) throws InterruptedException {
        WebElement input = driver.findElement(By.cssSelector("[type='text'][placeholder='" + dateKind + "']"));
        String script = "arguments[0].value ='" + date + "';";
        ((JavascriptExecutor) driver).executeScript(script, input);
        input.sendKeys(Keys.ENTER);
        Thread.sleep(5000); // Needs pause to let the date picker return prices and display fully!
    }

    public ResultsPage clickExploreButton() throws InterruptedException {
        WebElement exploreButton = driver.findElement(By.cssSelector("[jsname='" + EXPLORE_BUTTON_LABEL + "']"));
        exploreButton.click();
        return new ResultsPage(5000, driver); // Has to wait for results page to load.
    }

    public void close() {
        // driver.close();
    }
}
