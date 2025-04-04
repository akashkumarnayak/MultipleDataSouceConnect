package org.example.springpractice.services;

import org.example.springpractice.models.product.Product;
import org.example.springpractice.repositories.product.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product createProduct(Product product) {
        productRepo.save(product);
        return product;
    }
}
