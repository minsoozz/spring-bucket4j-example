package com.github.minsoozz.application.out;

import com.github.minsoozz.domain.Product;

import java.util.List;

public interface ProductPersistencePort {
    List<Product> findProducts();
}
