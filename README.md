This Java program simulates a shopping cart with product selection, discount application, and final price calculation.

Key Features:
---------------------------------------------------------------------------------------------------------------------------------------
Product Catalog: Manages three products with fixed prices using the Product enum.
Discount Rules: Implements various discount types using the DiscountType enum and DiscountRule class.
Applies the most beneficial discount to the cart based on subtotal, quantity, and specific product quantities.
Gift Wrap and Shipping Fees: Calculates gift wrap fees based on individual product wrapping preferences.
Computes shipping fees based on the total number of packages needed (10 units per package).
User Input: Prompts the user to enter quantities for each product and their gift wrapping preferences.
Output: Displays a detailed summary of the cart, including:
Product names, quantities, and total amounts
Subtotal
Applicable discount name and value
Subtotal after discount
Shipping and gift wrap fees
Final total

Code Structure:
-----------------------------------------------------------------------------------------------------------------------------------------
Enums: Product and DiscountType for defining product types and discount types.
Classes:
DiscountRule: Represents a discount rule with its type, threshold, and discount percentage or amount.
ProductField: Encapsulates product details (name, price, quantity) and gift wrapping status.
Cart: Main class with methods for discount calculation, display, and user interaction.
















