Feature: Google Flight Search

  Scenario: Searching flights with valid entries
    Given The Google Travel Flight Search page loaded successfully
    When User enters valid Origin and Destination and Departure and Return
    And User selects Explore button
    Then Flight results will be returned as expected