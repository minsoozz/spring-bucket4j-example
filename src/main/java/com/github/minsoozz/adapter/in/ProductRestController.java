package com.github.minsoozz.adapter.in;


import com.github.minsoozz.application.in.ProductUseCase;
import com.github.minsoozz.application.out.ProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductUseCase productUseCase;

    public ProductRestController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @GetMapping
    public List<ProductResponse> findProducts() {
        return productUseCase.findProducts();
    }
}
