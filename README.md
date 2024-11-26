# Shopping Cart API Documentation

## Introduction

A Spring Boot application for managing a shopping cart. It provides endpoints to manage products and shopping carts.

---

## API Endpoints

### **Product Endpoints**

1. **List All Products**
    - **GET** `/products`  
      Returns a list of all products.
    - **Example**
      ```bash
      curl -X GET http://localhost:8081/products
      ```

2. **Search Products**
    - **GET** `/products/search?keyword=<keyword>`  
      Searches products by a keyword.
    - **Example**
      ```bash
      curl -X GET "http://localhost:8081/products/search?keyword=Organic apple"
      ```

3. **Get Product Details**
    - **GET** `/products/{id}`  
      Returns details of a product by ID.
    - **Example**
      ```bash
      curl -X GET http://localhost:8081/products/1
      ```

---

### **Shopping Cart Endpoints**

1. **View Cart**
    - **GET** `/cart/{cartId}`  
      Retrieves details of the specified shopping cart.
    - **Example**
      ```bash
      curl -X GET http://localhost:8081/cart/1
      ```

2. **Add Product to Cart**
    - **POST** `/cart/{cartId}/add?productId=<id>&quantity=<quantity>`  
      Adds a product to the shopping cart.
    - **Example**
      ```bash
      curl -X POST "http://localhost:8081/cart/1/add?productId=1&quantity=2"
      ```

3. **Remove Product from Cart**
    - **DELETE** `/cart/{cartId}/remove/{cartItemId}`  
      Removes a product from the cart.
    - **Example**
      ```bash
      curl -X DELETE http://localhost:8081/cart/1/remove/1
      ```

4. **Empty Cart**
    - **DELETE** `/cart/{cartId}/empty`  
      Empties all items from the cart.
    - **Example**
      ```bash
      curl -X DELETE http://localhost:8081/cart/1/empty
      ```

5. **Checkout and Pay**
    - **POST** `/cart/{cartId}/pay`  
      Calculates and returns the total price, including taxes.
    - **Example**
      ```bash
      curl -X POST http://localhost:8081/cart/1/pay
      ```

---

## Note

The application runs on `server.port=8081`. Ensure the server is up and running before testing the endpoints.
#   T r e n d y o l - C a p s t o n e - P r o j e c t  
 