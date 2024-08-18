## Feature: Get Fixed expenses

### Scenario: Get fixed expenses fails when an unexpected exception occurs [✅]

    Given logged user 
    When I attempt to get fixed expenses with

        | expenseCode     |
        | 12              |

    Then an unexpected exception is thrown
    And the expenses is not returned
    And the status code should be 500
    And the response body should contain "An unexpected error occurred. Please try again later."

### Scenario: Get fixed expenses succeeds [✅]

    Given logged user
    When I attempt to get fixed expenses with

        | expenseCode     |
        | 12              |

    Then an domain exception is thrown
    And the status code should be 200
    And the response body should contain list of fixed expenses