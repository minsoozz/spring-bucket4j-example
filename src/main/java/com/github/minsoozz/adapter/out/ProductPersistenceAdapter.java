package com.github.minsoozz.adapter.out;

import com.github.minsoozz.application.out.ProductPersistencePort;
import com.github.minsoozz.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductPersistenceAdapter implements ProductPersistencePort {
    @Override
    public List<Product> findProducts() {
        return List.of(
                new Product(2L, "Product 2", 2, 2),
                new Product(3L, "Product 3", 3, 3),
                new Product(4L, "Product 4", 4, 4),
                new Product(5L, "Product 5", 5, 5),
                new Product(6L, "Product 6", 6, 6),
                new Product(7L, "Product 7", 7, 7),
                new Product(8L, "Product 8", 8, 8),
                new Product(9L, "Product 9", 9, 9),
                new Product(10L, "Product 10", 10, 10));
    }
}
