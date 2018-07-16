# Bread
Restaurant Point-Of-Sale system backend

## REST endpoints

* GET /owner/{id} - Obtains the owner associated with the given ID. If no owner exists, an HTTP 404 (Not Found) status is returned. If the restaurant can be found, an HTTP 200 status is returned and the response body contains the information associated with the owner.
* PUT /owner/{id} - Updates an existing owner. If no owner with the given ID can be found, an HTTP 404 status is returned. If a owner exists with the given ID and the request body contains valid updates to the owner, the owner is updated and the updated owner is returned in the response body, along with an HTTP 200 status.

* GET /owner/{id}/restaurant - Obtains the list of all restaurant associated with the given owner ID. If the list is successfully obtained, the list of existing restaurant is returned, along with an HTTP 200(OK) status.
* POST /owner/{id}/restaurant - Creates a new restaurant. This request should carry a request body that includes the data that should be associated with the newly created restaurant. If the restaurant is created, an HTTP 201 (Created) status is returned along with the newly created restaurant in the response body.
* GET /owner/{id}/restaurant/{id} - Obtains the restaurant associated with the given ID. If no restaurant exists, an HTTP 404 (Not Found) status is returned. If the restaurant can be found, an HTTP 200 status is returned and the response body contains the information associated with the restaurant.
* PUT /owner/{id}/restaurant/{id} - Updates an existing restaurant. If no restaurant with the given ID can be found, an HTTP 404 status is returned. If a restaurant exists with the given ID and the request body contains valid updates to the restaurant, the restaurant is updated and the updated restaurant is returned in the response body, along with an HTTP 200 status.
* DELETE /owner/{id}/restaurant/{id} - Deletes a restaurant with the given ID. If no restaurant exists, an HTTP 404 status is returned. If the restaurant exists, it is deleted, and an HTTP 204 (No Content) status is returned.

Endpoint:

    RestEmployeeController

        Iterable<Employee> getEmployeesByRestaurantId
            GET owner/{ownerId}/restaurant/{restaurantId}/employee
        Employee getEmployeeById
            GET owner/{ownerId}/restaurant/{restaurantId}/employee/{employeeId}
        Employee addNewEmployee
            POST owner/{ownerId}/restaurant/{restaurantId}/employee
        Employee changeDetails
            PUT owner/{ownerId}/restaurant/{restaurantId}/employee/{employeeId}

    RestOwnerController

        Iterable<Restaurant> getRestaurantsByOwnerId
            GET /owner
        Owner getOwnerById
            GET /owner/{ownerId}
        Restaurant getRestaurantById
            GET /owner/{ownerId/restaurant/{restaurantId}
        Iterable<Table> getAllTablesByRestaurantId
            GET /{ownerId}/restaurant/{restaurantId}/table
        Table getTableById
            GET owner//{ownerId/restaurant/{restaurantId}/table/{tableId}
        Iterable<Seat>
            GET seat/{ownerId/restaurant/{restaurantId}/table/{tableId}/seat
        Seat getSeatById
            GET seat/{ownerId/restaurant/{restaurantId}/table/{tableId}/seat/{seatId}