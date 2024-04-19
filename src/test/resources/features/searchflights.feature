Feature: Google Travel Flight Search

  @Test-01
  Scenario Outline: Verify flights returned by search result page when valid entries on all inputs with default values (round trip/1 adult/Economy)
    Given The Google Travel Flight Search page is loaded successfully
    And Default ticket settings are used
    When User enters valid "<origin>" and "<destination>" and "<departureDate>" and "<returnDate>"
    And User selects Explore button
    Then Flight results will be returned on the search results page
    Examples:
      | origin    | destination   | departureDate        | returnDate       |
      | Perth     | Canberra      | May 1, 2024          | May 25, 2024     |
      | Adelaide  | Sydney        | September 15, 2024   | October 5, 2024  |

  @Test-02
  Scenario Outline: Verify flights returned by search result page where return date is not entered for One way tickets for multiple number of tickets and different class of ticket (Economy/Premium economy/Business/First)
    Given The Google Travel Flight Search page is loaded successfully
    When User updates ticket type "<ticketTypes>"
    And User updates passenger type "<passengerType>" to <numberOfPassengers>
    And User updates ticket class "<ticketClassType>"
    Then User can only enter "<origin>" and "<destination>" and "<departureDate>"
    And User selects Explore button
    And Flight results will be returned on the search results page
    Examples:
      | origin    | destination   | departureDate        | ticketTypes     | passengerType   | numberOfPassengers  | ticketClassType |
      | Brussels  | Berlin        | June 10, 2024        | One way         | Adult           | 2                   | Premium economy |
      | Brisbane  | Suva, Fiji    | April 30, 2024       | One way         | Children        | 1                   | Business        |
      | Sydney    | New York      | July 25, 2024        | One way         | Infants on lap  | 1                   | First           |
      | Singapore | Dubai         | September 2, 2024    | One way         | Infants in seat | 2                   | Premium economy |

  @Test-03
  Scenario Outline: Verify no flight result is returned for specified destination and ticket class
    Given The Google Travel Flight Search page is loaded successfully
    When User updates ticket class "First"
    And User enters valid "<origin>" and "<destination>" and "<departureDate>" and "<returnDate>"
    And User selects Explore button
    Then No flight results will be returned on the search results page
    Examples:
      | origin       | destination   | departureDate        | returnDate       |
      | Canberra     | Bali          | May 1, 2024          | May 25, 2024     |