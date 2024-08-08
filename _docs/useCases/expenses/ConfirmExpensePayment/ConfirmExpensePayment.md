# Feature: Confirm expense payment

As a logged user,
I want to be able to confirm expense payment

## NOTE:

      * If pay date is not set, it takes the current date.
      * The payment receipt is optional.
      * If reference mouth is not set, it takes current mouth.

## Scenario: Unexpected error during confirm expense payment []

    Given fixed expense payload with  expenseCode 12345, amountPaid 22, payDate "01-10-2022", 
    referenceMonth "10/2024", paymentMethod "CARD", paymentReceipt "image.png"
    When I send a POST request to "api/confirm-expense-payment"
    And an unexpected error occurs
    Then the response status should be 500
    And the response should contain "An unexpected error occurred. Please try again later."

## Scenario: Confirm expense payment with already confirmed expense code  []

    Given fixed expense payload with  expenseCode -23, amountPaid 22, payDate "01-10-2022", 
    referenceMonth "10/2024", paymentMethod "CARD", paymentReceipt "image.png"
    When I send a POST request to "api/confirm-expense-payment"
    Then the response status should be 409
    And the response should contain "The expense payment has already been confirmed."

## Scenario: Confirm expense payment with non-existing expense code []

    Given fixed expense payload with  expenseCode -23, amountPaid 22, payDate "01-10-2022", 
    referenceMonth "10/2024", paymentMethod "CARD", paymentReceipt "image.png"
    When I send a POST request to "api/confirm-expense-payment"
    Then the response status should be 404
    And the response should contain "Expense not found."

## Scenario: Confirm expense payment with no active session []

    Given fixed expense payload with  expenseCode "any_expense_code", amountPaid 22, payDate "01-10-2022", 
    referenceMonth "10/2024", paymentMethod "CARD", paymentReceipt "image.png"
    When I send a POST request to "api/confirm-expense-payment"
    Then the response status should be 403
    And the response should contain "Forbidden."

## Scenario: Confirm expense payment with no expense code []

    Given fixed expense payload with  expenseCode 0 , amountPaid 22, payDate "01-10-2022", 
    referenceMonth "10/2024", paymentMethod "CARD", paymentReceipt "image.png"
    When I send a POST request to "api/confirm-expense-payment"
    Then the response status should be 400
    And the response should contain "Expense code is required."

## Scenario: Confirm expense payment with no amount paid []

    Given fixed expense payload with  expenseCode 23, amountPaid 0, payDate "01-10-2022", 
    referenceMonth "10/2024", paymentMethod "CARD", paymentReceipt "image.png"
    When I send a POST request to "api/confirm-expense-payment"
    Then the response status should be 400
    And the response should contain "Amount payed is required."

## Scenario: Confirm expense payment with invalid amount paid []

    Given fixed expense payload with  expenseCode 23, amountPaid -200, payDate "01-10-2022", 
    referenceMonth "10/2024", paymentMethod "CARD", paymentReceipt "image.png"
    When I send a POST request to "api/confirm-expense-payment"
    Then the response status should be 400
    And the response should contain "Amount payed is invalid."

## Scenario: Confirm expense payment with invalid amount paid []

    Given fixed expense payload with  expenseCode 23, amountPaid 200, payDate "01-10-2022", 
    referenceMonth "10/2024", paymentMethod "CARD", paymentReceipt "image.png"
    When I send a POST request to "api/confirm-expense-payment"
    Then the response status should be 400
    And the response should contain "Amount payed is i invalid."

## Scenario: Confirm expense payment with payDate after current date []

    Given fixed expense payload with  expenseCode 0 , amountPaid 22, payDate "any_day_after_current_date", 
    referenceMonth "10/2024", paymentMethod "CARD", paymentReceipt "image.png"
    When I send a POST request to "api/confirm-expense-payment"
    Then the response status should be 400
    And the response should contain "We sorry! The pay day is invalid."

## Scenario: Confirm expense payment with no payment method []

    Given fixed expense payload with  expenseCode 23, amountPaid 200, payDate "01-10-2022", 
    referenceMonth "10/2024", paymentMethod "", paymentReceipt "image.png"
    When I send a POST request to "api/confirm-expense-payment"
    Then the response status should be 400
    And the response should contain "Payment method is required."

## Scenario: Confirm expense payment with invalid payment method []

    Given fixed expense payload with  expenseCode 23, amountPaid 200, payDate "01-10-2022", 
    referenceMonth "10/2024", paymentMethod "any_invalid", paymentReceipt "image.png"
    When I send a POST request to "api/confirm-expense-payment"
    Then the response status should be 400
    And the response should contain "Payment is invalid."

## Scenario: Successful Confirm expense payment []

    Given fixed expense payload with  expenseCode 23, amountPaid 200, payDate "01-10-2022", 
    referenceMonth "10/2024", paymentMethod "CARD", paymentReceipt "image.png"
    When I send a POST request to "api/confirm-expense-payment"
    Then the response status should be 200
    And the response should contain "We've receved payment confirmation."