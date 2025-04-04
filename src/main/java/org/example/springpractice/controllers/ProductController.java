package org.example.springpractice.controllers;

import org.example.springpractice.dtos.ProductRequestDto;
import org.example.springpractice.dtos.ProductResponseDto;
import org.example.springpractice.models.product.Product;
import org.example.springpractice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("create")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto)
    {
        Product product = productService.createProduct(from(productRequestDto));
        return from(product);
    }

    Product from(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setId(productRequestDto.getId());
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        return product;
    }

    ProductResponseDto from(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        return productResponseDto;
    }


}
