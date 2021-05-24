# tomtom-ecommerce
#### mini ecommerce project for Interview assessment project


## Project Synopsis 
This is mini ecommerce assingment wherein customer and seller can performed following activities: 
* Customer and Seller can list the products
* Customer can add product to the cart 
* Customer can removed products from the cart
* Customer can list all products added into the cart
* Seller can update the stock - can add products to the database 

## High Level Implementation Details
- Springboot APIs are created for all of the above mentioned functionalities.
- Adhered to the design patterns to provide better experience and standard practices.
- Role based access control has been implemented to manage control based access for the APIs
- For authentication basic auth has been implemented using Spring-Security 
- For authorization on checkout and payment (critical apis) jwt data signing has been implemented.
- Enabled global exception handeling to provide consistent and minimal required information to the client


## Low Level details 
##### POST \api\seller\add-products
* This api can be invoked by Seller only to add the product to database.

##### GET \api\products
* This api can be invoked by both Seller and Customer

##### PUT \api\cart\add-product
* This api can be invoked by Customer only to add the product to his/her cart.

##### PUT \api\cart\remove-product
* This api can be invoked by Customer only to remove the product from his/her cart.

##### GET \api\cart\products
* This api can be invoked by Customer only to list products from his/her cart.

##### POST \api\checkout
* This api can be invoked by Customer only to checkout hist/her cart.

##### POST \api\payment
* This api can be invoked by Customer only to make payment to the order.

