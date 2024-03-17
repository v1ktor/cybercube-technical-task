# `Login Functionality`

- Validate that `standard_user", "problem_user", "performance_glitch_user", "error_user", "visual_user"` can log in with
  valid credentials
- Validate that `locked_out_user` cannot log in and receives an error message
- Validate that error message is thrown when trying to log in with invalid credentials

# `Cart Functionality, tested only with "standard_user"`

- Validate that products can be added and removed to/from the cart from the inventory page. The cart icon should update
  to reflect the number of items in the cart. The "Add to cart" and "Remove" buttons should also be visible and
  functional
- Validate that products can be added to cart from the inventory page and removed from the cart from the cart page. The
  cart page should be empty after removing all items from the cart.
    - NB! Possible bug. When cart is empty user still can proceed to the checkout which logically should not be possible

# `Checkout Functionality, tested only with "standard_user"`

- Validate that user can do successfully checkout with selected products
- Validate that selected items price is displayed correctly in the checkout page
    - NB! Calculation/Displaying bug. The price of some selected items is calculated with floating-point precision
      error - e.g. 9.99 + 49.99 is displayed as `$59.980000000000004` instead of `$59.98`
