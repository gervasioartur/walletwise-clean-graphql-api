## Feature: Delete Fixed expense

### Scenario: Delete fixed expense fails when an unexpected exception occurs [✅]

    Given logged user 
    When I attempt to delete fixed expense with

        | expenseCode     |
        | 12              |

    Then an unexpected exception is thrown
    And the user is not created
    And the status code should be 500
    And the response body should contain "An unexpected error occurred. Please try again later."

### Scenario: Delete fixed expense fails when fixed expense does not exist [✅]

    Given logged user
    When I attempt to delete fixed expense with

        | expenseCode     |
        | 12              |

    Then an domain exception is thrown
    And the expense is not added
    And the status code should be 404
    And the response body should contain "Resource not found."


### Scenario: Delete fixed expense succeeds [✅]

    Given logged user
    When I attempt to delete fixed expense with

        | expenseCode     |
        | 12              |

    Then an domain exception is thrown
    And the expense is not added
    And the status code should be 200
    And the response body should contain "OK."