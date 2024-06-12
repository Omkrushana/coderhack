# coderhack



# Coder Hack
**Postman collection**

*Create User*
Post request 

curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data '{
    "username": "omkrushana"
}'

*Get All User*
Get request   (GET /users)

curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data ''

*Get User By ID*
Get request (GET /users/{userId})

curl --location 'http://localhost:8080/users/{userId}' \
--header 'Content-Type: application/json' \
--data ''


*Update User score by Id*
Put request (PUT /users/{userId})

curl --location --request PUT 'http://localhost:8080/users/{userid}' \
--header 'Content-Type: application/json' \
--data '{
    "score":"20"
}'


*Delete user by id*
Delete request (DELETE /users/{userId})

curl --location --request DELETE 'http://localhost:8080/users/{userId}' \
--header 'Content-Type: application/json' \
--data ''

