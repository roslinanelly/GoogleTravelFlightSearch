package org.au.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.au.travelpage.SearchPage;
import org.au.travelpage.ResultsPage;
import org.junit.Assert;

/**
 * Step Definition class for searchflights feature test
 */
public class SearchFlightsStepDefinitions {

    SearchPage searchPage;
    ResultsPage resultsPage; // NB! This is loaded by clicking the explore button on the search page.

    @Before
    public void setUp() throws InterruptedException {
        searchPage = new SearchPage(5000);
    }


    @Given("^The Google Travel Flight Search page is loaded successfully$")
    public void theGoogleTravelFlightSearchPageIsLoadedSuccessfully() {
        Assert.assertTrue(searchPage.hasExploreButton());
    }

    @And("Default ticket settings are used")
    public void defaultTicketSettingsAreUsed() {

        Assert.assertEquals("Round trip", searchPage.getTicketType());
        Assert.assertEquals("1", searchPage.getNumberOfPassengers());
        Assert.assertEquals("Economy", searchPage.getTicketClass());
    }

    @When("User enters valid {string} and {string} and {string} and {string}")
    public void userEntersValidOriginDestinationDepartureReturnDates(String origin, String destination, String departureDate, String returnDate) throws InterruptedException {
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
    public void flightResultsWillBeReturnedAsExpected() {
        Assert.assertEquals("Search results", resultsPage.getSearchResults());
        Assert.assertTrue(resultsPage.getResultsSummary().matches(".*[0-9]+ results returned."));
    }

    @Then("No flight results will be returned on the search results page")
    public void noFlightResultsWillBeReturnedAsExpected() {
        Assert.assertEquals("Search results", resultsPage.getSearchResults());
        Assert.assertTrue(resultsPage.getResultsSummary().contains("No results returned"));

    }

    @When("User updates ticket type {string}")
    public void userUpdatesTicketType(String ticketType) {
        searchPage.setTicketType(ticketType);
    }

    @And("User updates passenger type {string} to {int}")
    public void userUpdatesPassengerTypeToNumberOfPassengers(String passengerType, int number) {
        searchPage.updatePassengerTypeToNumber(passengerType, number);
    }

    @And("User updates ticket class {string}")
    public void userUpdatesTicketClass(String ticketClass) {
        searchPage.setTicketClass(ticketClass);
    }

    @Then("User can only enter {string} and {string} and {string}")
    public void userCanOnlyEnterOriginDestinationDepartureDate(String origin, String destination, String departureDate) throws InterruptedException {
        searchPage.setOrigin(origin);
        searchPage.setDestination(destination);

        Thread.sleep(5000); // This has to wait for the browser to settle airport picking choices.

        searchPage.setDepartureDate(departureDate);

        // This should not be found:
        Assert.assertTrue(searchPage.hasNoReturnDate());
    }

    @After
    public void cleanUp() {
        searchPage.close();
    }

}
