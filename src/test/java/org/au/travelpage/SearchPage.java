package org.au.travelpage;

import org.au.regression.TestRunner;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// class for search page objects
// contain methods available from the search page
public class SearchPage {

    private final WebDriver driver;

    // object identifiers and values from source page
    private static final String EXPLORE_BUTTON_JSNAME = "vLv7Lb";
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
    private static final String TICKET_TYPE_DROPDOWN_XPATH = "//div[2]/div/div/div/div/div/div/div/div/div/div/div";
    private static final String PASSENGER_TYPE_JSNAME = "QqIbod";
    private static final String TICKET_CLASS_XPATH = "//div[3]/div/div/div/div/div";
    private static final String ADD_FLIGHT_BUTTON_JSNAME = "htvI8d";

    // Initiate and loading Google Travel Flights search page
    public SearchPage(int waitMS) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", TestRunner.CHROMEDRIVER);
        driver = new ChromeDriver();
        driver.get("https://www.google.com/travel/flights");
        Thread.sleep(waitMS);
    }

    // Validate 'Explore' button before action
    public boolean hasExploreButton() {
        By locator = By.cssSelector("[jsname='" + EXPLORE_BUTTON_JSNAME + "']");
        return !driver.findElements(locator).isEmpty() && driver.findElement(locator).isEnabled();
    }

    // Method to get the ticket type from the flight details, expect to return Round trip, One way, or Multi-city
    public String getTicketType() {
        By locator = By.cssSelector("[id='" + TICKET_TYPE_ID + "'][jsname='" + TICKET_JSNAME + "']");
        return driver.findElement(locator).getText();
    }

    // Method to get total number of passengers from the flight details, minimal value = 1 (Adult)
    public String getNumberOfPassengers() {
        By locator = By.cssSelector("[jsname='" + NUMBER_OF_PASSENGERS_JS_NAME + "']");
        return driver.findElement(locator).getText();
    }

    // Method to get selected ticket class  from the flight details, expect to return Economy, Premium economy, Business, or First
    public String getTicketClass() {
        By locator = By.cssSelector("[id='" + TICKET_CLASS_ID + "'][jsname='" + TICKET_JSNAME + "']");
        return driver.findElement(locator).getText();
    }

    // Method to input the city of origin for the flight
    public void setOrigin(String origin) throws InterruptedException {
        setAirportHelper(ORIGIN_INPUT_PLACEHOLDER_VALUE, origin);
    }

    // Method to input the city of destination for the flight
    public void setDestination(String destination) throws InterruptedException {
        setAirportHelper(DESTINATION_INPUT_PLACEHOLDER_VALUE, destination);
    }

    // Helper to input the city of origin for the flight
    // Pass the city name, click to activate the dropdown list, then select the first occurrence from the dropdown list to get the correct city name
    private void setAirportHelper(String airportLabel, String value) {
        WebElement input = driver.findElement(By.cssSelector("[type='text'][aria-label='" + airportLabel + "']"));
        String script = "arguments[0].value ='" + value + "';";
        ((JavascriptExecutor) driver).executeScript(script, input);
        input.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(AIRPORT_LIST_CLASS)));

        // Click on the first suggestion from the autocomplete dropdown
        WebElement firstSuggestion = dropdown.findElement(By.className(AIRPORT_SELECTION_CLASS));
        firstSuggestion.click();
    }

    // Method to input the departure date for the flight
    public void setDepartureDate(String date) throws InterruptedException {
        setDateHelper(DEPARTURE_DATE_INPUT_PLACEHOLDER_VALUE, date);
    }

    // Method to input the return date for the flight
    public void setReturnDate(String date) throws InterruptedException {
        setDateHelper(RETURN_DATE_INPUT_PLACEHOLDER_VALUE, date);
    }

    // Helper to input the date for the flight
    // Pass the date text value in "Month dd, yyyy" format, enter to validate the text date to date picker format
    // Add delay to ensure the value is captured correctly
    private void setDateHelper(String dateKind, String date) throws InterruptedException {
        WebElement input = driver.findElement(By.cssSelector("[type='text'][placeholder='" + dateKind + "']"));
        String script = "arguments[0].value ='" + date + "';";
        ((JavascriptExecutor) driver).executeScript(script, input);
        input.sendKeys(Keys.ENTER);
        Thread.sleep(5000); // Needs pause to let the date picker return prices and display fully!
    }

    // Action on the Explore button after all flight details are entered
    public ResultsPage clickExploreButton() throws InterruptedException {
        WebElement exploreButton = driver.findElement(By.cssSelector("[jsname='" + EXPLORE_BUTTON_JSNAME + "']"));
        exploreButton.click();
        return new ResultsPage(5000, driver); // Has to wait for results page to load.
    }

    // Method to input the ticket type for the flight
    public void setTicketType(String ticketType) {
        // Using Selenium IDE to get the xpath:
        driver.findElement(By.xpath(TICKET_TYPE_DROPDOWN_XPATH)).click();
        driver.findElement(By.xpath("//li[contains(.,'" + ticketType + "')]")).click();
    }

    // A very contrived way to specify type of a lambda function and use it as they weren't really needed so far
    // - perhaps if I had to map some function onto a collection?
    private interface Helper {
        void action(String s, int i);
    }

    // Method to add the number passenger for the passenger type (Adult, Children, Infants on lap or in seats)
    // Minimum 1 adult passenger is required
    public void updatePassengerTypeToNumber(String passengerType, int number) {
        driver.findElement(By.cssSelector("[jsname='" + PASSENGER_TYPE_JSNAME + "']")).click();

        Helper lambdaHelper = (String labelName, int start) -> {
            WebElement inputAdult = driver.findElement(By.cssSelector("[aria-label='" + labelName + "']"));
            for (int i = start; i < number; i++) {
                inputAdult.click();
            }
        };

        if (passengerType.equals("Adult")) {
            lambdaHelper.action("Add adult", 1);
        }

        if (passengerType.equals("Children")) {
            lambdaHelper.action("Add child", 0);
        }

        if (passengerType.equals("Infants on lap")) {
            lambdaHelper.action("Add infant on lap", 0);
        }

        if (passengerType.equals("Infants in seat")) {
            lambdaHelper.action("Add infant in seat", 0);
        }

        driver.findElement(By.xpath("//div[2]/button/span")).click();
    }

    // Method to input the ticket type for the flight
    public void setTicketClass(String ticketClass) {
        driver.findElement(By.xpath(TICKET_CLASS_XPATH)).click();
        driver.findElement(By.xpath("//li[contains(.,'" + ticketClass + "')]")).click();

    }

    // Validate the return date input field is hidden (for One way or Multi-city selection)
    public boolean hasNoReturnDate() {
        return !driver.findElement(By.cssSelector("[type='text'][placeholder='" +
                RETURN_DATE_INPUT_PLACEHOLDER_VALUE + "']")).isDisplayed();
    }

    // close the browser after finish the test
    public void close() {
        driver.close();
    }

}
