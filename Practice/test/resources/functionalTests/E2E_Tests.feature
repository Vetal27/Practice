Feature: Tests for Swapi.dev
  Swapi Dev URL : https://swapi.dev/api

  Scenario Outline: User  check film count
    Given A base URL and click films
    When User get films count
    Then User check films count equals to <Count>
    Examples:
      | Count |
      | 6     |

  Scenario Outline: User check director of first film
    Given A base URL and click films
    When User get list of films
    And User get first film
    And User get director of first film
    Then User check that first director equals '<Director>'
    Examples:
      | Director     |
      | George Lucas |

  Scenario Outline: User check count planets with a diameter greater than <Value>
    Given A base URL and click planets
    When User get list of planets
    And User choose planets with diameter greater than <Value>
    Then User check that count of result planets with diameter greater than <Value> equals <Count>
    Examples:
      | Value | Count |
      | 10000 | 7     |

  Scenario Outline: User check name of first planets with unknown population
    Given A base URL and click planets
    When User get list of planets
    And User choose planets with unknown population
    Then User check '<Name>' of first planet with unknown population
    Examples:
      | Name |
      | Hoth |