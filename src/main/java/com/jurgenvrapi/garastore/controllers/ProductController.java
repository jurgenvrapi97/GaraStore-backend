package com.jurgenvrapi.garastore.controllers;

import com.jurgenvrapi.garastore.entities.Product;
import com.jurgenvrapi.garastore.services.ImageUploadService;
import com.jurgenvrapi.garastore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ImageUploadService imageUploadService;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService,  ImageUploadService imageUploadService) {
        this.productService = productService;
        this.imageUploadService = imageUploadService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

@PostMapping
public Product createProduct(@RequestPart("product") Product product, @RequestPart("image") MultipartFile image) {
    return productService.createProduct(product, image);
}

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}