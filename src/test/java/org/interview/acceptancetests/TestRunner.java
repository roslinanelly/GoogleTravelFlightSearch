package org.interview.acceptancetests;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import net.thucydides.core.annotations.Screenshots;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@Screenshots(afterEachStep=true)
@CucumberOptions(features="src/test/resources/features/", glue="org.interview")

public class TestRunner {

    // Replace with the path to your chromedriver executable:
    public static String CHROMEDRIVER = "C:\\Users\\Public\\Nelly\\chromedriver.exe";

}