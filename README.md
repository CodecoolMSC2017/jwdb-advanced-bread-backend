# Bread
Restaurant Point-Of-Sale system backend

## REST endpoints

* __GET /owner/restaurant__ - Obtains the list of all restaurant associated with the given owner ID. If the list is successfully obtained, the list of existing restaurant is returned, along with an HTTP 200(OK) status.
* __POST /owner/restaurant__ - Creates a new restaurant. This request should carry a request body that includes the data that should be associated with the newly created restaurant. If the restaurant is created, an HTTP 201 (Created) status is returned along with the newly created restaurant in the response body.
* __GET /owner/restaurant/{id}__ - Obtains the restaurant associated with the given ID. If no restaurant exists, an HTTP 404 (Not Found) status is returned. If the restaurant can be found, an HTTP 200 status is returned and the response body contains the information associated with the restaurant.
* __PUT /owner/restaurant/{id}__ - Updates an existing restaurant. If no restaurant with the given ID can be found, an HTTP 404 status is returned. If a restaurant exists with the given ID and the request body contains valid updates to the restaurant, the restaurant is updated and the updated restaurant is returned in the response body, along with an HTTP 200 status.
* __DELETE /owner/restaurant/{id}__ - Deletes a restaurant with the given ID. If no restaurant exists, an HTTP 404 status is returned. If the restaurant exists, it is deleted, and an HTTP 204 (No Content) status is returned.

* GET /owner/{ownerId}

* __GET /restaurant/{restaurantId}/table__
* __GET /restaurant/{restaurantId}/table/{tableId}__
* __POST /restaurant/{restaurantId}/table/{tableId}__
* __PUT /restaurant/{restaurantId}/table/{tableId}__

* GET /restaurant/{restaurantId}/table/{tableId}/seat
* GET /restaurant/{restaurantId}/table/{tableId}/seat/{seatId}
* POST /restaurant/{restaurantId}/table/{tableId}/seat
* PUT /restaurant/{restaurantId}/table/{tableId}/seat/{seatId}

* __GET /owner/restaurant/{restaurantId}/employee__
* __GET /owner/restaurant/{restaurantId}/employee/{employeeId}__
* __POST /owner/{ownerId}/restaurant/{restaurantId}/employee__
* __DELETE /owner/restaurant/{restaurantId}/employee__ - Deactivates the user.
* __PUT /owner/restaurant/{restaurantId}/employee/{employeeId}__
* __PUT /owner/restaurant/{restaurantId}/employee/{employeeId}/addusername__ - Connects an existing employee with a username. It requieres a username, a password and a confirmation password and returns an employee

* GET /owner/{ownerId}/restaurant/{restaurantId}/ingredient
* GET /owner/{ownerId}/restaurant/{restaurantId}/ingredient/{ingredientId}
* POST /owner/{ownerId}/restaurant/{restaurantId}/ingredient

* GET /owner/{ownerId}/restaurant/{restaurantId}/item
* GET /owner/{ownerId}/restaurant/{restaurantId}/item/{itemId}
* POST /owner/{ownerId}/restaurant/{restaurantId}/item
* DELETE /owner/{ownerId}/restaurant/{restaurantId}/item/{itemId}
* PUT /owner/{ownerId}/restaurant/{restaurantId}/item{itemId}

* GET /restaurant/{restaurantId}/employee/{employeeId}/table/{tableId}/seat/{seatId}/customerorder
* GET /restaurant/{restaurantId}/employee/{employeeId}/table/{tableId}/seat/{seatId}/customerorder/{customerOrderId}
* GET /restaurant/{restaurantId}/employee/{employeeId}/table/{tableId}/seat/{seatId}/orderitem
* POST /restaurant/{restaurantId}/employee/{employeeId}/table/{tableId}/seat/{seatId}/order

* __GET /owner/user__
* __GET /owner/user/{userId}__
* __POST /owner/user__
* __POST /owner/user/change-password__