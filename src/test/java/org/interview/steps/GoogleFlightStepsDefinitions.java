package org.interview.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.annotations.findby.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleFlightStepsDefinitions  {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Public\\Nelly\\chromedriver.exe"); // Replace with the path to your chromedriver executable!
        driver = new ChromeDriver();
    }

    @Given("^The Google Travel Flight Search page loaded successfully$")
    public void theGoogleTravelFlightSearchPageLoadedSuccessfully() throws InterruptedException {
        driver.get("https://www.google.com/travel/flights");
        Thread.sleep(1000);
        WebElement exploreButton = driver.findElement(By.cssSelector("[jsname='vLv7Lb']"));
        exploreButton.click();
        Thread.sleep(1000);
    }

    @When("User enters valid Origin and Destination and Departure and Return")
    public void userEntersValidOriginAndDestinationAndDepartureAndReturn() {
    }

    @And("User selects Explore button")
    public void userSelectsExploreButton() {
    }

    @Then("Flight results will be returned as expected")
    public void flightResultsWillBeReturnedAsExpected() {
    }

}
