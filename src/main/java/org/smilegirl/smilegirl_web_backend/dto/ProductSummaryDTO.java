package org.smilegirl.smilegirl_web_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductSummaryDTO {
    private Long id;
    private String productName;
    private Double rentalPrice;
    private String imageUrl;
}

