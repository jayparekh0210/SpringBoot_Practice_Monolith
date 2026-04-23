package com.jay.springbootstarter.adapter;

import com.jay.springbootstarter.dto.requests.ProductRequest;
import com.jay.springbootstarter.dto.responses.ProductResponse;
import com.jay.springbootstarter.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductResponse productModelToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .isActive(product.getIsActive())
                .stockQuantity(product.getStockQuantity())
                .build();

    }

    public Product productRequestToProductModel(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .category(productRequest.getCategory())
                .imageUrl(productRequest.getImageUrl())
                .stockQuantity(productRequest.getStockQuantity())
                .isActive(productRequest.getIsActive()!= null ?productRequest.getIsActive() : true)
                .build();
    }

    public Product productRequestToProductModel(ProductRequest productRequest,Product product) {
        return Product.builder()
                .id(product.getId())
                .name(productRequest.getName()!=null ? productRequest.getName() : product.getName())
                .description(productRequest.getDescription()!=null ? productRequest.getDescription() : product.getDescription())
                .price(productRequest.getPrice()!=null ? productRequest.getPrice() : product.getPrice())
                .category(productRequest.getCategory()!=null ? productRequest.getCategory() : product.getCategory())
                .imageUrl(productRequest.getImageUrl()!=null ? productRequest.getImageUrl() : product.getImageUrl())
                .stockQuantity(productRequest.getStockQuantity()!=null ? productRequest.getStockQuantity() : product.getStockQuantity())
                .isActive(productRequest.getIsActive()!= null ?productRequest.getIsActive() : product.getIsActive())
                .build();
    }
}
