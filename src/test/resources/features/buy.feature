Feature: Buy products
    As a customer
    I want to buy products

Background:
    Given the store is ready to service customers
    And a product "Bread" with price 20.50 and stock of 5 exists
    And a product "Jam" with price 80.00 and stock of 10 exists
    And a product "Milk" with price 20.00 and stock of 15 exists

Scenario: Buy one product
    When I buy "Bread" with quantity 2
    Then total should be 41.00

Scenario: Buy multiple products
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 1
    And I buy "Milk" with quantity 3
    Then total should be 181.00

    Scenario Outline: Buy one product
        When I buy <product> with quantity <quantity>
        Then total should be <total>
        Examples:
            | product  | quantity |  total  |
            | "Bread"  |     1    |   20.50 |
            | "Jam"    |     2    |  160.00 |
            | "Milk"   |     2    |   40.00 |

Scenario: Check stock after purchasing
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 1
    And I buy "Milk" with quantity 3
    Then total should be 181.00
    And a product "Bread" with a stock of 3 exists
    And a product "Jam" with a stock of 9 exists
    And a product "Milk" with a stock of 12 exists

Scenario: Out of stock
    When I buy "Bread" with quantity 6
    Then an exception "InsufficientResourcesException" should be thrown
