Feature: Google Flight Search

  Scenario Outline: Verify flights returned by search result page when valid entries on all inputs with default values (round trip/1 adult/Economy)
    Given The Google Travel Flight Search page loaded successfully
    And Default ticket settings are used
    When User enters valid "<origin>" and "<destination>" and "<departureDate>" and "<returnDate>"
    And User selects Explore button
    Then Flight results will be returned on the search results page
    Examples:
      | origin    | destination   | departureDate        | returnDate       |
      | Perth     | Canberra      | May 1, 2024          | May 25, 2024     |
      | Adelaide  | Sydney        | September 15, 2024   | October 5, 2024  |


  Scenario Outline: Verify search page input fields are updated when one way tickets are selected regardless different class of ticket (Economy/Premium economy/Business/First) and number of tickets
    Given The Google Travel Flight Search page loaded successfully
    When User updates "<ticketTypes>"
    And User updates number of "<passengerType>" to <numberOfPassengers>
    And User updates "<ticketClassType>"
    Then User can only enter "<origin>" and "<destination>" and "<departureDate>" and selects Explore button
    And Flight results will be returned on the search results page
    Examples:
      | origin    | destination   | departureDate        | ticketTypes     | passengerType   | numberOfPassengers  | ticketClassType |
      | Melbourne | Canberra      | June 10, 2024        | One way         | Adult           | 2                   | Premium economy |
      | Brisbane  | Fiji          | April 30, 2024       | One way         | Children        | 1                   | Business        |
      | Sydney    | Bali          | July 25, 2024        | One way         | Infants on lap  | 1                   | First           |
      | Singapore | Dubai         | September 2, 2024    | One way         | Infants on seat | 2                   | Premium economy |


  Scenario Outline: Verify add flight is enabled when multi-city tickets are selected and user can add and remove multiple stop-overs
    Given The Google Travel Flight Search page loaded successfully
    When User updates "Multi-city"
    And Default ticket settings are used for  passengerType, numberOfPassengers, and ticketClassType
    Then User can add multiple "<origin>" and "<destination>" and "<departureDate>"
    And User can remove multiple "<origin>" and "<destination>" and "<departureDate>"
    And Flight results will be returned on the search results page
    Examples:
      | origin    | destination   | departureDate       |
      | Melbourne | Canberra      | April 10, 2024      |
      | Canberra  | Fiji          | June 30, 2024       |
      | Fiji      | Bali          | July 2, 2024        |
      | Bali      | Dubai         | September 12, 2024  |