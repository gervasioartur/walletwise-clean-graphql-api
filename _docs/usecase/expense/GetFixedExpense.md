## Feature: Get Fixed expense

### Scenario: Get fixed expense fails when an unexpected exception occurs [✅]

    Given logged user 
    When I attempt to get fixed expense with

        | expenseCode     |
        | 12              |

    Then an unexpected exception is thrown
    And the fixed expense is not returned
    And the status code should be 500
    And the response body should contain "An unexpected error occurred. Please try again later."

### Scenario: Get fixed expense fails when fixed expense does not exist [✅]

    Given logged user
    When I attempt to get fixed expense with

        | expenseCode     |
        | 12              |

    Then an domain exception is thrown
    And the fixed expense is not returned
    And the status code should be 404
    And the response body should contain "Resource not found."

### Scenario: Get fixed expense succeeds [✅]

    Given logged user
    When I attempt to get fixed expense with

        | expenseCode     |
        | 12              |

    Then an domain exception is thrown
    And the status code should be 200
    And the response body should contain fixed expense model object.