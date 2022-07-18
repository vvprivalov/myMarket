package ru.geekbrains.myMarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.myMarket.auth.AuthRequest;
import ru.geekbrains.myMarket.auth.AuthResponse;
import ru.geekbrains.myMarket.model.Cart;
import ru.geekbrains.myMarket.model.Product;
import ru.geekbrains.myMarket.service.ProductService;
import ru.geekbrains.myMarket.service.UserService;
import ru.geekbrains.myMarket.utils.JwtTokenUtil;
import ru.geekbrains.myMarket.utils.MarketError;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final Cart cartProduct = new Cart();
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @GetMapping("/products")
    public Page<Product> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@RequestBody Product product) {
        productService.save(product);
        return product;
    }

    @DeleteMapping("/products/{id}")
    public void delProduct(@PathVariable("id") Long id) {

        productService.delProduct(id);
    }

    @GetMapping("/products/{id}")
    public Product prepareProduct(@PathVariable Long id) {

        return productService.findById(id);
    }

    @GetMapping("/products/cart-content")
    public List<Product> getCart() {
        return cartProduct.getLstCart();
    }

    @PostMapping("/products/add-cart")
    public List<Product> addProductToCard(@RequestBody Product product) {

        return cartProduct.addProduct(product);
    }

    @PutMapping("/products")
    public void updateProduct(@RequestBody Product product) {

        productService.updateProduct(product);
    }

    @PostMapping("/products/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError("Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

