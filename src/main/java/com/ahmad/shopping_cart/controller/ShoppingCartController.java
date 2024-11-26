package com.ahmad.shopping_cart.controller;

import com.ahmad.shopping_cart.entity.ShoppingCart;
import com.ahmad.shopping_cart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    // Show shopping cart by ID
    @GetMapping("/{cartId}")
    public ShoppingCart showCart(@PathVariable Long cartId) {
        return shoppingCartService.getCart(cartId);
    }

    // Add product to cart
    @PostMapping("/{cartId}/add")
    public ShoppingCart addToCart(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam Integer quantity
    ) {
        return shoppingCartService.addProductToCart(cartId, productId, quantity);
    }

    // Remove product from cart
    @DeleteMapping("/{cartId}/remove/{cartItemId}")
    public ShoppingCart removeFromCart(
            @PathVariable Long cartId,
            @PathVariable Long cartItemId
    ) {
        return shoppingCartService.removeProductFromCart(cartId, cartItemId);
    }

    // Empty shopping cart
    @DeleteMapping("/{cartId}/empty")
    public void emptyCart(@PathVariable Long cartId) {
        shoppingCartService.emptyCart(cartId);
    }

    // Pay for shopping cart
    @PostMapping("/{cartId}/pay")
    public BigDecimal payForCart(@PathVariable Long cartId) {
        return shoppingCartService.payForCart(cartId);
    }
}
