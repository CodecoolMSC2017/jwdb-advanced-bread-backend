# Bread
Restaurant Point-Of-Sale system backend

## REST endpoints

* GET /owner/{id} - Obtains the owner associated with the given ID. If no owner exists, an HTTP 404 (Not Found) status is returned. If the restaurant can be found, an HTTP 200 status is returned and the response body contains the information associated with the owner.
* PUT /owner/{id} - Updates an existing owner. If no owner with the given ID can be found, an HTTP 404 status is returned. If a owner exists with the given ID and the request body contains valid updates to the owner, the owner is updated and the updated owner is returned in the response body, along with an HTTP 200 status.

* GET /restaurant - Obtains the list of all restaurants currently created in the system. If the list is successfully obtained, the list of existing orders is returned, along with an HTTP 200(OK) status.
* POST /restaurant - Creates a new restaurant. This request should carry a request body that includes the data that should be associated with the newly created restaurant. If the restaurant is created, an HTTP 201 (Created) status is returned along with the newly created restaurant in the response body.
* GET /restaurant/{id} - Obtains the restaurant associated with the given ID. If no restaurant exists, an HTTP 404 (Not Found) status is returned. If the restaurant can be found, an HTTP 200 status is returned and the response body contains the information associated with the restaurant.
* PUT /restaurant/{id} - Updates an existing restaurant. If no restaurant with the given ID can be found, an HTTP 404 status is returned. If a restaurant exists with the given ID and the request body contains valid updates to the restaurant, the restaurant is updated and the updated restaurant is returned in the response body, along with an HTTP 200 status.
* DELETE /restaurant/{id} - Deletes a restaurant with the given ID. If no restaurant exists, an HTTP 404 status is returned. If the restaurant exists, it is deleted, and an HTTP 204 (No Content) status is returned.

* GET /restaurant/table
* POST /restaurant/table
* GET /restaurant/table/{id}
* PUT /restaurant/table/{id}
* DELETE /restaurant/table/{id}


