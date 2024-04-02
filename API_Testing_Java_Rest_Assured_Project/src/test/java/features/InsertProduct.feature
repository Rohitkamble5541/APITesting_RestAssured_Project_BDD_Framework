Feature: Insert products using POST api

  Scenario Outline: Validate insert post product api status code  works correctly
    Given I hit the url of post  product api endpoint
    When I pass the url of products in  the request
    And I pass the request body of product title <ProductTitile>
    Then I received the response code as 200
    Examples:
      | ProductTitile |
      | Shoes         |


  Scenario Outline: Validate post product api response body works correctly
    Given I hit the url of post  product api endpoint
    When I pass the url of products in  the request
    And I pass the request body of product title <ProductTitile>
    Then I received the response body with id as <id>
    Examples:
      | ProductTitile | id |
      | Shoes         | 21  |