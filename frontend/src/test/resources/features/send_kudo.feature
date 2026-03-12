Feature: Send a kudo from the web application
  As a SofkianOS user
  I want to submit a kudo from the UI
  So that the request is accepted asynchronously by the backend

  Scenario: Submit a valid kudo and receive success feedback
    Given the user opens SofkianOS landing page
    When the user navigates to the Kudos form
    And the user fills the kudo form with:
      | from                | to                    | category   | message                                      |
      | Christopher Pallo   | Santiago              | Teamwork   | Gracias por tu apoyo en la entrega del sprint |
    And the user submits the kudo using the slider
    Then the user should see a successful kudo submission message
