package org.interview.steps.searchflights;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.interview.travelpage.SearchPage;
import org.interview.travelpage.ResultsPage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleFlightSearch {

    SearchPage searchPage;
    ResultsPage resultsPage; // NB! This is loaded by clicking the explore button on the search page.

    @Before
    public void setUp() throws InterruptedException {
        searchPage = new SearchPage(5000);
    }

    @Given("^The Google Travel Flight Search page loaded successfully$")
    public void theGoogleTravelFlightSearchPageLoadedSuccessfully() {
        Assert.assertTrue(searchPage.hasExploreButton());
    }

    @And("Default ticket settings are used")
    public void defaultTicketSettingsAreUsed() {
        // found element value = Round trip, 1, Economy

        // System.out.println("Ticket Type: " + searchPage.ticketType());
        Assert.assertEquals("Round trip", searchPage.getTicketType());
        // System.out.println("Number of Passengers: " + searchPage.numberOfPassengers());
        Assert.assertEquals("1", searchPage.getNumberOfPassengers());
        // System.out.println("Ticket Class: " + searchPage.ticketClass());
        Assert.assertEquals("Economy", searchPage.getTicketClass());
    }

    @When("User enters valid {string} and {string} and {string} and {string}")
    public void userEntersValidOriginAndDestinationAndDepartureAndReturn(String origin, String destination, String departureDate, String returnDate) throws InterruptedException {
        searchPage.setOrigin(origin);
        searchPage.setDestination(destination);

        Thread.sleep(5000); // This has to wait for the browser to settle airport picking choices.

        searchPage.setDepartureDate(departureDate);
        searchPage.setReturnDate(returnDate);
    }

    @And("User selects Explore button")
    public void userSelectsExploreButton() throws InterruptedException {
        resultsPage = searchPage.clickExploreButton();
    }

    @Then("Flight results will be returned on the search results page")
    public void flightResultsWillBeReturnedAsExpected() throws InterruptedException {
        Assert.assertEquals("Search results", resultsPage.getSearchResults());
        Assert.assertTrue(resultsPage.getResultsSummary().contains("results returned"));
    }

    @When("User updates {string}")
    public void userUpdates(String arg0) {
    }

    @And("User updates number of {string} to {int}")
    public void userUpdatesNumberOfToNumberOfPassengers(String arg0, int arg1) {
    }

    @Then("User can only enter {string} and {string} and {string} and selects Explore button")
    public void userCanOnlyEnterAndAndAndSelectsExploreButton(String arg0, String arg1, String arg2) {
    }

    @And("Default ticket settings are used for  passengerType, numberOfPassengers, and ticketClassType")
    public void defaultTicketSettingsAreUsedForPassengerTypeNumberOfPassengersAndTicketClassType() {
    }

    @Then("User can add multiple {string} and {string} and {string}")
    public void userCanAddMultipleAndAnd(String arg0, String arg1, String arg2) {
    }

    @And("User can remove multiple {string} and {string} and {string}")
    public void userCanRemoveMultipleAndAnd(String arg0, String arg1, String arg2) {
    }

    @After
    public void cleanUp() {
        searchPage.close();
    }

}
