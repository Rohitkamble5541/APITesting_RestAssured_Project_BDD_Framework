  Feature: Update products using PUT api

  Scenario Outline: Validate update put product api status code  works correctly
    Given I hit the url of put  product api endpoint
    When I pass the url of products in  the request with <Productnumber>
    Then I received the response code as 200
    Examples:
      | Productnumber |
      | 6             |

