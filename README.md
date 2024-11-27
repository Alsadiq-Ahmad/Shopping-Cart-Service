# Trendyol Capstone Project

## Overview

This project is a **Shopping Cart Service** developed as part of the Trendyol Capstone Project. It demonstrates a basic shopping application built using **Spring Boot** and **PostgreSQL**. The application allows users to manage products, create shopping carts, and perform various cart operations such as adding, removing, and checking out products.

---

## Features

- **Product Management**:
  - List all available products.
  - Search products by ID or keyword.
  - View product details (name, description, price, and tax rate).

- **Shopping Cart Operations**:
  - Create a shopping cart and persist its contents.
  - Add products to a shopping cart.
  - Remove products from the shopping cart.
  - Empty the entire shopping cart.
  - Checkout and pay for the shopping cart (includes tax calculation).

- **Persistence**:
  - Shopping cart and product data are stored in a PostgreSQL database.

---

## Tech Stack

- **Backend**: Java 21, Spring Boot
- **Database**: PostgreSQL
- **Tools**: Maven, Postman (for API testing)

---

## API Endpoints

### Product Endpoints

1. **List All Products**
   - **GET** `/products`
   - Returns a list of all products.
   - **Example**:
     ```bash
     curl -X GET http://localhost:8081/products
     ```

2. **Search Products**
   - **GET** `/products/search?keyword=<keyword>`
   - Searches for products using a keyword.
   - **Example**:
     ```bash
     curl -X GET "http://localhost:8081/products/search?keyword=Red"
     ```

3. **Get Product Details**
   - **GET** `/products/{id}`
   - Returns details of a product by ID.
   - **Example**:
     ```bash
     curl -X GET http://localhost:8081/products/1
     ```

---

### Shopping Cart Endpoints

1. **View Cart**
   - **GET** `/cart/{cartId}`
   - Retrieves details of the specified shopping cart.
   - **Example**:
     ```bash
     curl -X GET http://localhost:8081/cart/1
     ```

2. **Add Product to Cart**
   - **POST** `/cart/{cartId}/add?productId=<id>&quantity=<quantity>`
   - Adds a product to the shopping cart.
   - **Example**:
     ```bash
     curl -X POST "http://localhost:8081/cart/1/add?productId=1&quantity=2"
     ```

3. **Remove Product from Cart**
   - **DELETE** `/cart/{cartId}/remove/{cartItemId}`
   - Removes a product from the cart.
   - **Example**:
     ```bash
     curl -X DELETE http://localhost:8081/cart/1/remove/1
     ```

4. **Empty Cart**
   - **DELETE** `/cart/{cartId}/empty`
   - Empties all items from the cart.
   - **Example**:
     ```bash
     curl -X DELETE http://localhost:8081/cart/1/empty
     ```

5. **Checkout and Pay**
   - **POST** `/cart/{cartId}/pay`
   - Calculates the total price, including taxes.
   - **Example**:
     ```bash
     curl -X POST http://localhost:8081/cart/1/pay
     ```

---

## Set Up PostgreSQL

1. **Create a database**:
   Create a PostgreSQL database named `ShoppingCart`.

2. **Update the `application.properties` file** with your PostgreSQL credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/ShoppingCart
   spring.datasource.username=your_username
   spring.datasource.password=your_password


## **Notes**

### Port Configuration
The application runs on port `8081`. To change this, update the `application.properties` file:

```properties
server.port=8081

