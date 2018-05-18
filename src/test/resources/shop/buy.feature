Feature: Buy products
    As a customer
    I want to buy products

Background:
    Given the following products exist:
        | name   |  price  | quantity |
        | Bread  |  10     |    10    |
        | Jam    |  20     |    15    |
        | Coffee |  30     |    1     |

Scenario Outline: Buy one product
    When I buy "<product>" with quantity <quantity>
    Then total should be <total>
    And "<product>" should have <stock> left
    Examples:
        | product  | quantity |  total  | stock |
        | Bread    |   1      |   10    |   9   |
        | Jam      |   2      |   40    |  13   |
        | Coffee   |   1      |   30    |   0   |

Scenario: Buy multiple products
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 1
    And I buy "Coffee" with quantity 1
    Then total should be 70
    And "Bread" should have 8 left
    And "Jam" should have 14 left
    And "Coffee" should have 0 left