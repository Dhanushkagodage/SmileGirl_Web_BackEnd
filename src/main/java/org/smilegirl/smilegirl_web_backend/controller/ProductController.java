package org.smilegirl.smilegirl_web_backend.controller;


import org.smilegirl.smilegirl_web_backend.dto.ProductDTO;
import org.smilegirl.smilegirl_web_backend.dto.ProductSummaryDTO;
import org.smilegirl.smilegirl_web_backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin

public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.addProduct(productDTO);
            return ResponseEntity.ok("Product added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add product");
        }
    }

    @GetMapping("/getallproducts")
    public ResponseEntity<List<ProductSummaryDTO>> getAllProducts() {
        try {
            List<ProductSummaryDTO> products = productService.getAllProductsSummary();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/getproductByID/{id}")
    public ResponseEntity<ProductDTO> getProductByID(@PathVariable Long id) {
        try {
            ProductDTO product = productService.getProductByID(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

}




