package org.au.travelpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// class for search result page objects
// contain methods available from the search result page
public class ResultsPage {

    WebElement searchResults;

    // object identifiers and values from source page
    private static final String RESULTS_XPATH = "//div[@class='FXkZv']//h2";
    private static final String RESULTS_SUMMARY_XPATH = "//div[@class='HeMQ4'][@role='alert']";

    // validate the search result page by finding the search result frame
    public ResultsPage(int waitMS, WebDriver driver) throws InterruptedException {
        Thread.sleep(waitMS);
        searchResults = driver.findElement(By.xpath(RESULTS_XPATH));
    }

    public String getSearchResults() {
        return searchResults.getText();
    }

    // Method to get the result summary text
    public String getResultsSummary() {
        return searchResults.findElement(By.xpath(RESULTS_SUMMARY_XPATH)).getText();
    }
}
