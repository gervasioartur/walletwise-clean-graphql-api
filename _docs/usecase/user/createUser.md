## Feature: Create user

### Scenario: User creation fails when an unexpected exception occurs
    When I attempt to create a new user with

        | firstname     | lastname     | username     | email     | password     |
        | any_fristname | any_lastname | any_username | any_email | any_password |

    Then an unexpected exception is thrown
    And the user is not created
    And the status code should be 500
    And the response body should contain "An unexpected error occurred. Please try again later."

### Scenario: User creation fails with an existing username
    Given a user with the username "existing_username" already exists
    When I attempt to create a new user with

        | firstname     | lastname     | username          | email     | password     |
        | any_fristname | any_lastname | existing_username | any_email | any_password |

    Then the system should prevent the creation
    And a Conflict exception is thrown
    And the status code should be 409
    And the response body should contain "Username already taken."

### Scenario: User creation fails with already in use email
    Given a user with the email "in_use_email" already in use
    When I attempt to create a new user with

        | firstname     | lastname     | username    | email        | password     |
        | any_fristname | any_lastname | any_username | in_use_email | any_password |

    Then the system should prevent the creation
    And a Conflict exception is thrown
    And the status code should be 409
    And the response body should contain "Email already in use."

### Scenario: User creation fails with firstname Blank
    Given a user with a blank firstname
    When I attempt to create a new user with

        | firstname     | lastname     | username     | email     | password     |
        |               | any_lastname | any_username | any_email | any_password |

    Then the system should prevent the creation
    And a Domain exception is thrown
    And the status code should be 400
    And the response body should contain "Fistname is required."

### Scenario: User creation fails with lastname Blank
    Given a user with a blank lastname
    When I attempt to create a new user with

        | firstname     | lastname     | username     | email     | password     |
        | any_firstname |              | any_username | any_email | any_password |

    Then the system should prevent the creation
    And a Domain exception is thrown
    And the status code should be 400
    And the response body should contain "Lastname is required."

### Scenario: User creation fails with username Blank
    Given a user with a blank username
    When I attempt to create a new user with

        | firstname     | lastname      | username     | email     | password     |
        | any_firstname | any_lastname  |              | any_email | any_password |

    Then the system should prevent the creation
    And a Domain exception is thrown
    And the status code should be 400
    And the response body should contain "Username is required."

### Scenario: User creation fails with email Blank
    Given a user with a blank email
    When I attempt to create a new user with

        | firstname     | lastname      | username     | email     | password     |
        | any_firstname | any_lastname  | any_username |           | any_password |

    Then the system should prevent the creation
    And a Domain exception is thrown
    And the status code should be 400
    And the response body should contain "E-mail is required."

### Scenario: User creation fails with password Blank
    Given a user with a blank password
    When I attempt to create a new user with

        | firstname     | lastname      | username     | email     | password     |
        | any_firstname | any_lastname  | any_username | any_email |              |

    Then the system should prevent the creation
    And a Domain exception is thrown
    And the status code should be 400
    And the response body should contain "Password is required."

### Scenario: User creation succeeds with valid user info
    Given a user with valid info
    When I attempt to create a new user with

        | firstname     | lastname      | username     | email     | password     |
        | any_firstname | any_lastname  | any_username | any_email | any_password |

    Then the system should create the user
    And the status code should be 200
    And the response body should contain "User created."