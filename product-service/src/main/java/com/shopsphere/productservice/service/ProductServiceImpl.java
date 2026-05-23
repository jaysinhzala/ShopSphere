package com.shopsphere.productservice.service;

import com.shopsphere.productservice.dto.ProductRequest;
import com.shopsphere.productservice.dto.ProductResponse;
import com.shopsphere.productservice.model.Product;
import com.shopsphere.productservice.repository.ProductRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        // Step 1 - Convert ProductRequest to Product entity
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .sellerId(request.getSellerId())
                .build();

        // Step 2 - Save product to database
        Product savedProduct = productRepository.save(product);

        // Step 3 - Convert saved Product entity to ProductResponse and return
        return convertToResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        // Step 1 - Get all products from database
        List<Product> allProducts = productRepository.findAll();

        // Step 2 - Convert each Product to ProductResponse and return
        return allProducts.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        // Step 1 - Find product by id - throw exception if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Step 2 - Convert and return response
        return convertToResponse(product);
    }

    @Override
    public void deleteProduct(Long id) {
        // Step 1 - Find product by id - throw exception if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Step 2 - Delete product from database
        productRepository.delete(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        // Step 1 - Find product by id - throw exception if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Step 2 - Update each field with new values from request
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        // Step 3 - Save updated product to database
        Product updatedProduct = productRepository.save(product);

        // Step 4 - Convert and return response
        return convertToResponse(updatedProduct);
    }

    @Override
    public List<ProductResponse> getProductsBySeller(Long sellerId) {

        // Step 1 - Find all products belonging to this seller
        List<Product> products = productRepository.findBySellerId(sellerId);

        // Step 2 - Convert each Product to ProductResponse and return
        return products.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> searchProducts(String search) {

        // Step 1 - Search products by keyword in name
        List<Product> products = productRepository.findByNameContainingIgnoreCase(search);

        // Step 2 - Convert each Product to ProductResponse and return
        return products.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }

    // Helper method to convert Product entity to ProductResponse
    private ProductResponse convertToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl(),
                product.getSellerId()
        );
    }
}
