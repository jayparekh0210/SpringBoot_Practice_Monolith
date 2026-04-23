package com.jay.springbootstarter.services;

import com.jay.springbootstarter.adapter.ProductConverter;
import com.jay.springbootstarter.dto.requests.ProductRequest;
import com.jay.springbootstarter.dto.responses.ProductResponse;
import com.jay.springbootstarter.models.Product;
import com.jay.springbootstarter.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
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
        return productRepository.findAllByIsActiveTrue()
                .stream()
                .map(productConverter::productModelToProductResponse)
                .toList();
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    Product updatedProduct = productConverter.productRequestToProductModel(productRequest, existingProduct);
                    return productConverter.productModelToProductResponse(productRepository.save(updatedProduct));
                })
                .orElse(null);
    }

    public boolean deleteProduct(Long id){
        return productRepository.findById(id).map(product -> {
            product.setIsActive(false);
            productRepository.save(product);
            return true;
        }).orElse(false);
    }

    public List<ProductResponse> searchProducts(String keyword){
        return productRepository.searchProduct(keyword)
                .stream()
                .map(productConverter::productModelToProductResponse).toList();
    }
}
