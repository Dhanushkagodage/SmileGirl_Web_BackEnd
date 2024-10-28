package org.smilegirl.smilegirl_web_backend.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {
    private String productName;
    private String sku;
    private double rentalPrice;
    private String material;
    private String typeOfWear;
    private String description;
    private List<String> imageUrls;
    private List<ColorDTO> colors;

}
