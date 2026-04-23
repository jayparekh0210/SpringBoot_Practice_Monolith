package com.jay.springbootstarter.services;

import com.jay.springbootstarter.adapter.ProductConverter;
import com.jay.springbootstarter.dto.requests.ProductRequest;
import com.jay.springbootstarter.dto.responses.ProductResponse;
import com.jay.springbootstarter.models.Product;
import com.jay.springbootstarter.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Data
public class ProductService {

    private ProductRepository productRepository;
    private ProductConverter productConverter;

    public ProductResponse getProductById(Long id){
        Product product = productRepository.findById(id).orElse(null);
        if(product != null){
            return productConverter.productModelToProductResponse(product);
        } else {
            return null;
        }
    }

    public ProductResponse createProduct(ProductRequest productRequest){
        return productConverter.productModelToProductResponse(productRepository.save(productConverter.productRequestToProductModel(productRequest)));
    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productConverter::productModelToProductResponse)
                .toList();
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElse(null);
        if(product != null){
            Product updatedProduct = productConverter.productRequestToProductModel(productRequest, product);
            return productConverter.productModelToProductResponse(productRepository.save(updatedProduct));
        } else {
            return null;
        }

    }
}
