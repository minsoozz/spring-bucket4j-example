package com.github.minsoozz.application.in;

import com.github.minsoozz.application.out.ProductResponse;

import java.util.List;

public interface ProductUseCase {
    List<ProductResponse> findProducts();
}
