# CRUD Product Management Application

## Product Management Application using Spring Boot

This project is an application that uses **Spring Boot** to implement **CRUD operations** (Create, Read, Update, Delete) on a product entity. The project does not include a user interface but relies on a **RESTful API** to perform operations on products via **HTTP**.

## Technologies Used
- **Java 23**
- **Spring Boot 3.3.5**
- **H2 Database** (embedded database)
- **Spring Data JPA**
- **Spring Web** (for building REST APIs)
- **Spring Boot Starter Validation** (for input validation)

## Project Structure

### Entities
- **productEntity**: Represents a product with the following fields:
    - `productId`: Product identifier (automatically generated).
    - `productName`: Name of the product (must not be empty).
    - `productPrice`: Price of the product (must be greater than zero).
    - `productBarcode`: Barcode of the product (must only contain digits).

### Services
- **productService**: Contains business logic for performing operations on products such as:
    - Fetching all products.
    - Fetching a product by its ID or barcode.
    - Adding, updating, and deleting a product.
    - Adding and deleting a list of products.

### Controller
- **productController**: Defines the REST API and handles HTTP requests such as:
    - `GET /products`: Fetch all products.
    - `GET /products/id/{id}`: Fetch a product by its ID.
    - `GET /products/barcode/{barcode}`: Fetch a product by its barcode.
    - `POST /products`: Add a new product.
    - `PUT /products/id/{id}`: Update a product by its ID.
    - `POST /products/add-list`: Add a list of products.
    - `DELETE /products/id/{id}`: Delete a product by its ID.
    - `DELETE /products/delete-list`: Delete a list of products.
    - `DELETE /products/delete-all`: Delete all products.

## Configuration

### Database Connection Settings
The project uses **H2** as the embedded database for storing data. The following settings are specified in the `application.yaml` file:
- **Port**: 6060
- **Database URL**: `jdbc:h2:file:./dataBase/mydatabase;DB_CLOSE_ON_EXIT=FALSE`
- **H2 Console**: Accessible via `/h2-console`.

### JPA Settings
- `hibernate.ddl-auto` is set to `update` to automatically update the schema when the application starts.
- `hibernate.show-sql` is set to `true` to show SQL queries in the console.

## Running the Project

### Prerequisites
- **Java Development Kit (JDK) 23 or higher**
- **Apache Maven**
- **An Integrated Development Environment (IDE)** (e.g., IntelliJ IDEA, Eclipse)
- **HTTP client tool to interact with the API You can use Postman.**

### Running the Application
1. Clone the repository.
2. Navigate to the project directory.
3. Run the application using IntelliJ or Maven:
   ```bash
    mvn spring-boot:run

4. The application will be running on port 6060.
5. You can access the H2 console at: http://localhost:6060/h2-console

## Testing the API

You can use **Postman**, **cURL**, or any HTTP client tool to interact with the API.


### 1. Get All Products
To fetch a list of all products:
- **Request Type:** GET
- **URL:** `http://localhost:6060/products`
- **Description:** This will return a paginated list of products.

---

### 2. Get Product by ID
To fetch a product by its ID:
- **Request Type:** GET
- **URL:** `http://localhost:6060/products/id/{id}`
  - Replace `{id}` with the actual product ID.
- You should receive a response with the details of the product corresponding to the given ID.

---

### 3. Get Product by Barcode
To fetch a product by its barcode:
- **Request Type:** GET
- **URL:** `http://localhost:6060/products/barcode/{barcode}`
  - Replace `{barcode}` with the actual barcode.
- You should receive a response with the details of the product corresponding to the given barcode.

---

### 4. Add a New Product
To create a new product:
- **Request Type:** POST
- **URL:** `http://localhost:6060/products`
- **Body:** JSON (as `application/json`)
- **Request Body Example:**
   ```json
     {
        "productName": "Tablet",
        "productPrice": 3500.0,
        "productBarcode": "123456789"
      }
- You should receive a response with the newly created product and status 201.

---

### 5. Update Product by ID
To update an existing product by its ID:

- **Request Type:** PUT
- **URL:** `http://localhost:6060/products/id/{id}`
  - **Replace** `{id}` with the actual product ID.
- **Body:** JSON (as application/json)
- **Request Body Example:**

  ```json
  {
    "productName": "Updated Product Name",
    "productPrice": 120.0,
    "productBarcode": "987654321"
  }

- You should receive a response with the updated product details.

---
### 6. Add a List of Products
To add multiple products in one request:

- **Request Type:** POST
- **URL:** `http://localhost:6060/products/add-list`
- **Body:** JSON (as application/json)
- **Request Body Example:**

   ```json
   [
     {
       "productName": "Smartphone",
       "productPrice": 5000.0,
       "productBarcode": "112233"
     },
     {
       "productName": "Laptop",
       "productPrice": 7500.0,
       "productBarcode": "445566"
     }
   ]
- You should receive a response with a list of newly created products.
---

### 7. Delete a Product by ID
To delete a product by its ID:

- **Request Type:** DELETE
- **URL:** `http://localhost:6060/products/id/{id}`
  - Replace `{id}` with the actual product ID.

- You should receive a success message if the product was deleted, or a "not found" message if the product does not exist.

---

### 8. Delete a List of Products
To delete multiple products by their IDs:

- **Request Type:** DELETE
- **URL:** `http://localhost:6060/products/delete-list`
- **Body:** JSON (as application/json)
- **Request Body Example:**

   ```json
    [
     112233,
     445566
    ]

- You should receive a success message if all the products were deleted.

---

### 9. Delete All Products
To delete all products:

- **Request Type:** DELETE
- **URL:** `http://localhost:6060/products/delete-all`
- You should receive a success message if all products were deleted.

---

## Conclusion
   By following the steps above, you can easily test the different CRUD operations for your API using Postman. Make sure your Spring Boot application is running before sending requests to ensure you get the expected responses.
