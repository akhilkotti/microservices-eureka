To verify if your Spring Boot microservices project is working end-to-end (from auth-service to product-service via the api-gateway with Eureka registry), follow these step-by-step checks:
1. Start the Services in Order

    Start Eureka Server

        Run: DiscoveryServerApplication.java

        Visit: http://localhost:8761

        You should see the Eureka dashboard with no services initially.

    Start Auth Service

        Run: AuthServiceApplication.java
   
        It should register itself with Eureka.

        Refresh http://localhost:8761 and see AUTH-SERVICE listed.

    Start Product Service

        Run: ProductServiceApplication.java
   
        It should also register with Eureka.

        Refresh http://localhost:8761 and see PRODUCT-SERVICE listed.

    Start API Gateway

        Run: ApiGatewayApplication.java
   
        It should also register with Eureka.

        Refresh http://localhost:8761 and see API-GATEWAY listed.

3. Test Auth Flow (User Registration & Login)

Use Postman or RESTED(FIREFOX):

a) Register User

    POST http://localhost:9090/auth/register
    Content-Type: application/json
    
    {
      "username": "akhil",
      "password": "akhilpwd",
      "email": "akhil@example.com",
      "role": "USER"
    }

b) Login to Get JWT Token

    POST http://localhost:9090/auth/login
    Content-Type: application/json
    
    {
      "username": "akhil",
      "password": "akhilpwd"
    }
    
    Response: You should receive a JWT token like eyJhbGciOiJIUzI1NiJ9...
c) Add Product

    Use the token from login.
    
    POST http://localhost:9090/products
    
    Authorization: Bearer <JWT_TOKEN>
    Content-Type: application/json
    
    {
      "name": "Elfbar",
      "description": "Ellfbar RayD3",
      "price": 2700.00,
      "quantity": 1
    }

d) Get Products

    GET http://localhost:9090/products
    Authorization: Bearer <JWT_TOKEN>
    
    You should see a list of products.

Note:

    Service Not Available: Ensure services are up and registered with Eureka.

    401 Unauthorized: Likely JWT is missing or expired.

    

