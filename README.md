To verify if your Spring Boot microservices project is working end-to-end (from auth-service to product-service via the api-gateway with Eureka registry), follow these step-by-step checks:
1. Start the Services in Order

    Start Eureka Server

        Run: EurekaServerApplication.java

        Visit: http://localhost:8761

        You should see the Eureka dashboard with no services initially.

    Start Auth Service

        It should register itself with Eureka.

        Refresh http://localhost:8761 and see AUTH-SERVICE listed.

    Start Product Service

        It should also register with Eureka.

        Confirm PRODUCT-SERVICE appears in the Eureka dashboard.

    Start API Gateway

        After starting, API-GATEWAY should also register with Eureka.

2. Test Auth Flow (User Registration & Login)

Use Postman or curl:
Register User

POST http://localhost:9090/auth/register
Content-Type: application/json

{
  "username": "testuser",
  "password": "testpass",
  "email": "test@example.com",
  "role": "USER"
}

Login to Get JWT Token

POST http://localhost:9090/auth/login
Content-Type: application/json

{
  "username": "testuser",
  "password": "testpass"
}

Response: You should receive a JWT token like eyJhbGciOiJIUzI1NiJ9...

3. Test Product API Access via Gateway (With JWT)

Use the token from login.
Add Product

POST http://localhost:9090/products
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json

{
  "name": "Apple",
  "description": "Fresh apple",
  "price": 10.5,
  "quantity": 100
}

Get Products

GET http://localhost:9090/products
Authorization: Bearer <JWT_TOKEN>

 You should see a list of products if everything is working correctly.

Troubleshooting Tips

    401 Unauthorized: Likely JWT is missing or expired.

    404 Not Found: Check if route mapping in API Gateway is correct.

    Service Not Available: Ensure services are up and registered with Eureka.

    CORS issues: If testing with frontend, ensure CORS config is added in gateway.
