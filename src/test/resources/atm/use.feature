Feature: Use an ATM
  As a bank customer
  I want to make a transaction using an ATM

  Background:
    Given the following accounts exist:
      | id  | pin  | balance |
      | 1   | 123  | 100.00  |
      | 2   | 000  | 200.00  |
      | 3   | 999  | 0.00    |

  Scenario Outline: One person uses ATM
    When customer id <id> login with pin <pin>
    Then the balance should be <balance1>
    When deposit <deposit>
    Then the balance should be <balance2>
    When withdraw <withdraw>
    Then the balance should be <balance3>
    Examples:
      | id  | pin  | balance1 | deposit | balance2 | withdraw | balance3 |
      | 1   | 123  | 100.00   | 20.00   | 120.00   | 50.00    | 70.00    |
      | 2   | 000  | 200.00   | 100.00  | 300.00   | 1000.00  | 300.00   |
      | 3   | 999  | 0.00     | 10.00   | 10.00    | 10.00    | 0.00     |

  Scenario: Two person use ATM
    When customer id 1 login with pin 123
    Then the balance should be 100.00
    When transfer 100.00 to customer 3
    Then the balance should be 0.00
    When customer leaves the ATM
    And customer id 3 login with pin 999
    Then the balance should be 100.00