## Feature: Authenticate user

### Scenario: User authentication fails when an unexpected exception occurs
    When I attempt to authenticate a user

        | usenameOremail               | password        |
        | any_username_or_email        | any_password    | 

    Then an unexpected exception is thrown
    And the user is not athenticated
    And the status code should be 500
    And the response body should contain "An unexpected error occurred. Please try again later."

### Scenario: User authentication fails when username or email is blank
    Given a user with a blank username or email
    When I attempt to authenticate a user

        | usenameOremail               | password       |
        |                              | any_password   | 

    Then the system should prevent the authentication
    And the user is not athenticated
    And a Business exception is thrown
    And the status code should be 400
    And the response body should contain "Username or email is required."

### Scenario: User authentication fails when password is blank
    Given a user with a blank password
    When I attempt to authenticate a user

        | usenameOremail               | password       |
        | any_username_or_email        |                | 

    Then the system should prevent the authentication
    And the user is not athenticated
    And a Business exception is thrown
    And the status code should be 400
    And the response body should contain "Password is required."

### Scenario: User authentication succeeds when with valid authentication info
    Given a valid authentication info
    When I attempt to authenticate a user

        | usenameOremail               | password       |
        | any_username_or_email        | any_passord    | 

    Then the system should autehnticate the user
    And the status code should be 200
    And the response body should contain the access token