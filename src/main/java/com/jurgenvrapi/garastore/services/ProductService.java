package com.jurgenvrapi.garastore.services;

import com.jurgenvrapi.garastore.entities.Product;
import com.jurgenvrapi.garastore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ImageUploadService imageUploadService;

    @Autowired
    public ProductService(ProductRepository productRepository, ImageUploadService imageUploadService) {
        this.productRepository = productRepository;
        this.imageUploadService = imageUploadService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product, MultipartFile image) {
        String imageUrl = imageUploadService.uploadImage(image);
        product.setImageUrl(imageUrl);
        return productRepository.save(product);
    }

public Product updateProduct(Long id, Product updatedProduct) {
    return productRepository.findById(id)
            .map(product -> {
                if (updatedProduct.getProductName() != null) {
                    product.setProductName(updatedProduct.getProductName());
                }
                if (updatedProduct.getDescription() != null) {
                    product.setDescription(updatedProduct.getDescription());
                }
                if (updatedProduct.getPrice() != null) {
                    product.setPrice(updatedProduct.getPrice());
                }
                if (updatedProduct.getQuantity() != null) {
                    product.setQuantity(updatedProduct.getQuantity());
                }
                return productRepository.save(product);
            })
            .orElseThrow(() -> new RuntimeException("Product not found"));
}

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}