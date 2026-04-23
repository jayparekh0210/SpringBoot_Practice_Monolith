package com.jay.springbootstarter.controllers;

import com.jay.springbootstarter.dto.requests.ProductRequest;
import com.jay.springbootstarter.dto.responses.ProductResponse;
import com.jay.springbootstarter.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequest));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponse> fetchProductById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProduct(id, productRequest);
        if (productResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id)?ResponseEntity.status(HttpStatus.NO_CONTENT).body(true):ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);

    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword){
        return ResponseEntity.status(HttpStatus.OK).body(productService.searchProducts(keyword));
    }


}
