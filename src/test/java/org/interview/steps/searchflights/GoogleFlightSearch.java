package org.interview.steps.searchflights;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.interview.travelpage.SearchPage;
import org.interview.travelpage.ResultsPage;
import org.junit.Assert;

import java.util.List;
import java.util.Map;


public class GoogleFlightSearch {

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
    public void userCanOnlyEnterAndAnd(String origin, String destination, String departureDate) throws InterruptedException {
        searchPage.setOrigin(origin);
        searchPage.setDestination(destination);

        Thread.sleep(5000); // This has to wait for the browser to settle airport picking choices.

        searchPage.setDepartureDate(departureDate);

        // This should not be found:
        Assert.assertTrue(searchPage.hasNoReturnDate());
    }


    @Then("User can add multiple flights")
    public void userCanAddMultipleFlights(DataTable dataTable) {

    }

    @And("User can remove selected flight")
    public void userCanRemoveSelectedFlight() {

    }


    @After
    public void cleanUp() {
        searchPage.close();
    }

}
