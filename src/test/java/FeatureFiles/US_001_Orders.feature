@smoke
  @UI
  @regression

Feature: Buyer should be able to see the current information of product purchased in the Orders section.

  Scenario: Buyer should be able to see the product purchased and its current and accurate information in the Orders section.

      Given Navigate to "https://test.urbanicfarm.com/"
      When Login with valid credentials of the "seller"
      Then Go to Your products or services page
      And Add a new product to sell
      Then Verify that the product added message is available
      Then Open a new frame and select to navigate
      And Navigate to "https://phpmyadmin-test.urbanicfarm.com/"
      And Login SQL with valid credentials
      Then Select database to execute query
      And Execute SQL query to fix as APPROVED
      And Close the browser tab and back to the UrbanicFarm frame
      And Verify related item is available with APPROVED description
      And Logout from website
      Then Login with valid credentials of the "buyer"
      Then Navigate to Farmers Market Page and search for the product
      Then Add the relevant seller's product to the cart
      Then Go to Cart and verify the purchase information to make payment.
      Then Pay for the product with PayPal
      Then Verify that the payment is successful
      And Verify that the product is available in the Orders section
      And Verify that the order status is "In Progress" for the buyer
      And Logout from website
      When Login with valid credentials of the "seller"
      Then Go to Sold items page and verify the product is available
      And Verify that the order status is "In Progress" for the seller
      And Change order status to "On Delivery"
      And Logout from website
      Then Login with valid credentials of the "buyer"
      And Go to Orders page
      Then Verify that the order status is "On Delivery" for the buyer
      And Logout from website
      Then Login with valid credentials of the "seller"
      Then Go to Sold items page
      And Change order status to "Completed"
      And Logout from website
      Then Login with valid credentials of the "buyer"
      Then Go to Orders page
      Then Verify that the order status is "Completed" for the buyer
