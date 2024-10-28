package org.smilegirl.smilegirl_web_backend.service;
import org.modelmapper.ModelMapper;
import org.smilegirl.smilegirl_web_backend.dto.ColorDTO;
import org.smilegirl.smilegirl_web_backend.dto.ProductDTO;
import org.smilegirl.smilegirl_web_backend.dto.ProductSummaryDTO;
import org.smilegirl.smilegirl_web_backend.dto.StockDTO;
import org.smilegirl.smilegirl_web_backend.entity.Color;
import org.smilegirl.smilegirl_web_backend.entity.Product;
import org.smilegirl.smilegirl_web_backend.entity.ProductImage;
import org.smilegirl.smilegirl_web_backend.entity.Stock;
import org.smilegirl.smilegirl_web_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public void addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setSku(productDTO.getSku());
        product.setRentalPrice(productDTO.getRentalPrice());
        product.setMaterial(productDTO.getMaterial());
        product.setTypeOfWear(productDTO.getTypeOfWear());
        product.setDescription(productDTO.getDescription());

        List<ProductImage> images = productDTO.getImageUrls().stream()
                .map(url -> new ProductImage(null, url, product))
                .collect(Collectors.toList());
        product.setImageUrls(images);

        List<Color> colors = productDTO.getColors().stream().map(colorDTO -> {
            Color color = new Color();
            color.setColorName(colorDTO.getColorName());
            color.setProduct(product);

            List<Stock> stocks = colorDTO.getStocks().stream().map(stockDTO -> {
                Stock stock = new Stock();
                stock.setSize(stockDTO.getSize());
                stock.setQuantity(stockDTO.getQuantity());
                stock.setColor(color);
                return stock;
            }).collect(Collectors.toList());

            color.setStocks(stocks);
            return color;
        }).collect(Collectors.toList());

        product.setColors(colors);

        productRepository.save(product);
    }

    public List<ProductSummaryDTO> getAllProductsSummary() {
        return productRepository.findAllProductsSummary();
    }

    public ProductDTO getProductByID(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        // Convert the Product entity to ProductDTO using the information
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(product.getProductName());
        productDTO.setSku(product.getSku());
        productDTO.setRentalPrice(product.getRentalPrice());
        productDTO.setMaterial(product.getMaterial());
        productDTO.setTypeOfWear(product.getTypeOfWear());
        productDTO.setDescription(product.getDescription());

        List<String> imageUrls = product.getImageUrls().stream().map(ProductImage::getImageUrl).collect(Collectors.toList());
        productDTO.setImageUrls(imageUrls);

        List<ColorDTO> colors = product.getColors().stream().map(color -> {
            ColorDTO colorDTO = new ColorDTO();
            colorDTO.setColorName(color.getColorName());

            List<StockDTO> stocks = color.getStocks().stream().map(stock -> {
                StockDTO stockDTO = new StockDTO();
                stockDTO.setSize(stock.getSize());
                stockDTO.setQuantity(stock.getQuantity());
                return stockDTO;
            }).collect(Collectors.toList());

            colorDTO.setStocks(stocks);
            return colorDTO;
        }).collect(Collectors.toList());

        productDTO.setColors(colors);

        return productDTO;
    }

}

