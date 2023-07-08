package com.github.minsoozz.application.service;

import com.github.minsoozz.application.in.ProductUseCase;
import com.github.minsoozz.application.out.ProductPersistencePort;
import com.github.minsoozz.application.out.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductUseCase {

    private final ProductPersistencePort productPersistencePort;

    public ProductService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public List<ProductResponse> findProducts() {
        return productPersistencePort.findProducts()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getQuantity()))
                .toList();
    }
}
