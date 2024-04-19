# Java Selenium Serenity BDD with Cucumber Framework UI test automation for Google Travel Flight Search

Page under-test:  https://www.google.com/travel/flights


## Dependencies
If not already installed, download and install the dependencies below, select each link for more information for download and installation:
1. [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) 8 or higher
2. [Maven](https://maven.apache.org/download.cgi)
3. [Selenium WebDriver](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
4. [Serenity BDD](https://mvnrepository.com/artifact/net.serenity-bdd/serenity-core)
5. [Chromedriver](https://googlechromelabs.github.io/chrome-for-testing/) - replace the path with your local file path in the \GoogleTravelFlightSearch\src\test\java\org\interview\regression\TestRunner.java

For dependency #3 & #4, include the dependency in your `pom.xml`

for example:

   ```xml
   <dependency>
       <groupId>net.serenity-bdd</groupId>
       <artifactId>serenity-core</artifactId>
       <version>{version}</version>
	   <scope>test</scope>
   </dependency>
   ```

## Usage

1. Clone this repository:

   ```bash
   git clone https://github.com/roslinanelly/GoogleTravelFlightSearch.git
   
2. Run testRunner for all tests scenarios :
	```bash
	mvn clean verify
    ```
 
    Run specific test scenario, see the tag name in the feature file
    ```bash 
    mvn clean verify -Dtags=@Test-01 
    ```
    
   Test Report will be generated in \GoogleTravelFlightSearch\target\site\serenity\index.html

3. To run test manually in IDE: run \GoogleTravelFlightSearch\src\test\java\org\interview\regression\TestRunner.java
4. Feature file: \GoogleTravelFlightSearch\src\test\resources\features\searchflights.feature
5. Step Definitions: \GoogleTravelFlightSearch\src\test\java\org\interview\steps\searchflights\SearchFlightsStepDefinitions.java