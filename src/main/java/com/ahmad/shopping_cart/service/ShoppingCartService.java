package com.ahmad.shopping_cart.service;

import com.ahmad.shopping_cart.entity.CartItem;
import com.ahmad.shopping_cart.entity.Product;
import com.ahmad.shopping_cart.entity.ShoppingCart;
import com.ahmad.shopping_cart.exception.ResourceNotFoundException;
import com.ahmad.shopping_cart.repository.CartItemRepository;
import com.ahmad.shopping_cart.repository.ProductRepository;
import com.ahmad.shopping_cart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public ShoppingCart getCart(Long cartId) {
        return shoppingCartRepository.findById(cartId)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart();
                    return shoppingCartRepository.save(cart);
                });
    }

    public ShoppingCart addProductToCart(Long cartId, Long productId, Integer quantity) {
        ShoppingCart cart = getCart(cartId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));

        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for product id " + productId);
        }

        // Update product stock
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        // Check if the product is already in the cart
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (cartItem != null) {
            // Update quantity
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            // Add new cart item
            cartItem = CartItem.builder()
                    .product(product)
                    .shoppingCart(cart)
                    .quantity(quantity)
                    .build();
            cart.getItems().add(cartItem);
        }

        shoppingCartRepository.save(cart);
        return cart;
    }

    public ShoppingCart removeProductFromCart(Long cartId, Long cartItemId) {
        ShoppingCart cart = getCart(cartId);
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id " + cartItemId));

        // Update product stock
        Product product = cartItem.getProduct();
        product.setQuantity(product.getQuantity() + cartItem.getQuantity());
        productRepository.save(product);

        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);

        shoppingCartRepository.save(cart);
        return cart;
    }

    public void emptyCart(Long cartId) {
        ShoppingCart cart = getCart(cartId);

        // Update stock for all products in the cart
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() + item.getQuantity());
            productRepository.save(product);
        }

        cart.getItems().clear();
        shoppingCartRepository.save(cart);
    }

    public BigDecimal payForCart(Long cartId) {
        ShoppingCart cart = getCart(cartId);

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem item : cart.getItems()) {
            BigDecimal itemTotal = item.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));

            BigDecimal taxAmount = itemTotal.multiply(item.getProduct().getTaxRate());
            totalAmount = totalAmount.add(itemTotal).add(taxAmount);
        }

        // Empty the cart after payment
        cart.getItems().clear();
        shoppingCartRepository.save(cart);

        return totalAmount;
    }
}
