package org.interview.travelpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage {

    private WebDriver driver;

    WebElement searchResults;

    private static final String RESULTS_XPATH = "//div[@class='FXkZv']//h2";
    private static final String RESULTS_SUMMARY_XPATH = "//div[@class='HeMQ4'][@role='alert']";

    public ResultsPage(int waitMS, WebDriver driver) throws InterruptedException {
        this.driver = driver; // NB! Search Page will close the driver for us.
        Thread.sleep(waitMS);
        searchResults = driver.findElement(By.xpath(RESULTS_XPATH));
    }

    public String getSearchResults() {
        return searchResults.getText();
    }

    public String getResultsSummary() {
        return searchResults.findElement(By.xpath(RESULTS_SUMMARY_XPATH)).getText();
    }
}
