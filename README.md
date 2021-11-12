I was asked to create the next application:
Design and implement a RESTFUL API that…
- Has APIs for Create, Read, Update, and Delete (CRUD) for a customer object.
- Customer object has one required field: [string]Name.
 
- Has an API for a customer to make a purchase.
- Tracks the number of purchases made by a customer.
- Returns the cost of the purchase.
 * Cost should reflect loyalty discount by number of purchases as follows:
	purchases          | discount
	- 0                | 0%
	- 1-2              | 1%
	- 2-5              | 2%
	- 5-10             | 5%
	- >10              | 10%

The Design on a nutshell:
- The API is implemented in Java 11 on a framework called Spring
- The Spring frameworks allows ud to easily deploy any REST API inside a Tomcat we server 
- Every object comming in and out of the Spring framework is a JSON by default
- We have a few layers, each layer was thought on a way that this can be scaled easily
- We are applying a Clean Architecture, meaning that there is no attached dependencies between any of the classes.
- I designed a classed under the 'storage' package that would be were we could make the different implementation of any database.
- We have a 'Controllers' layer which are dedicated excelusively to receive and respond any REST calls. The 'handlers' package is the one that will handle all the logic for each of the controllers we have.
- Everything we have on the 'beans' package is meant to be used on the whole API, and this would allow us to create future object implementation based on a Factory Pattern, e.g. 'A New Customer', 'Old Customer', 'Special Needs customer'
    * The 'Item' class is an abstraction of something that we can receive from another service.