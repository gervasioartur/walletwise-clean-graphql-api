## Feature: Update Fixed expense

### Scenario: Update fixed expense fails when an unexpected exception occurs [✅]

    Given logged user 
    When I attempt to update fixed expense with

        | description     | category     | amount | dueday | startDate     | endDate    |
        | any_description | any_category | 100    |  20    | 2023-10-01    | 2024-10-01 |

    Then an unexpected exception is thrown
    And the user is not created
    And the status code should be 500
    And the response body should contain "An unexpected error occurred. Please try again later."

### Scenario: Update fixed expense fails when description is blank [✅]

    Given logged user
    When I attempt to update fixed expense with

        | description     | category     | amount | dueday | startDate     | endDate    |
        |                 | any_category | 100    |  20    | 2023-10-01    | 2024-10-01 |

    Then an domain exception is thrown
    And the expense is not updateed
    And the status code should be 400
    And the response body should contain "Description is required."

### Scenario: Update fixed expense fails when category is blank [✅]

    Given logged user
    When I attempt to update fixed expense with

        | description     | category     | amount | dueday | startDate     | endDate    |
        | any_description |              | 100    |  20    | 2023-10-01    | 2024-10-01 |

    Then an domain exception is thrown
    And the expense is not updateed
    And the status code should be 400
    And the response body should contain "Category is required."

### Scenario: Update fixed expense fails when amount is less than zero or equal zero  [✅]

    Given logged user
    When I attempt to update fixed expense with

        | description     | category     | amount | dueday | startDate   | endDate    |
        | any_description | any_category | 0      |  20    | 2023-10-01  | 2024-10-01 |

    Then an domain exception is thrown
    And the expense is not updateed
    And the status code should be 400
    And the response body should contain "Amount is invalid, make it is grater than zero(0)."

### Scenario: Update fixed expense fails when start date is null  [✅]

    Given logged user
    When I attempt to update fixed expense with

        | description     | category     | amount | dueday | startDate     | endDate      |
        | any_description | any_category | 100    |  20    |               | 2024-10-01   |

    Then an domain exception is thrown
    And the expense is not updateed
    And the status code should be 400
    And the response body should contain "Start date is required."

### Scenario: Update fixed expense fails when end date is null  [✅]

    Given logged user
    When I attempt to update fixed expense with

        | description     | category     | amount | dueday | startDate     | endDate      |
        | any_description | any_category | 100    |  20  | 2024-10-01    |              |

    Then an domain exception is thrown
    And the expense is not updateed
    And the status code should be 400
    And the response body should contain "End date is required."

### Scenario: Update fixed expense fails when end date is before the star date  [✅]

    Given logged user
    When I attempt to update fixed expense with

        | description     | category     | amount | dueday | startDate     | endDate      |
        | any_description | any_category | 100    |  20    | 2024-10-01    | 2024-01-01   |

    Then an domain exception is thrown
    And the expense is not updateed
    And the status code should be 400
    And the response body should contain "Invalid end date! End date should be after or equal the start date."

### Scenario: Update fixed expense succeeds with correct values   [✅]

    Given logged user
    When I attempt to update fixed expense with

        | description     | category     | amount | dueday | startDate     | endDate      |
        | any_description | any_category | 100    |  20    | 2024-10-01    | 2025-01-01   |

    Then an domain exception is thrown
    And the status code should be 201
    And the response body should contain "OK"