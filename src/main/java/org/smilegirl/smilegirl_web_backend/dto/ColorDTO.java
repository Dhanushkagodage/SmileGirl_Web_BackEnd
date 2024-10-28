package org.smilegirl.smilegirl_web_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ColorDTO {
    private String colorName;
    private List<StockDTO> stocks;
}

