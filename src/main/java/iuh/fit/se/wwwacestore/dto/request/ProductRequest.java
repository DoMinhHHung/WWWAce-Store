package iuh.fit.se.wwwacestore.dto.request;

import iuh.fit.se.wwwacestore.entity.enums.ProductType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductRequest {
    private String name;
    private String brand;
    private String description;
    private ProductType type;
    private BigDecimal price;
    private Integer stock;
    private String mainImage;
    private List<String> images;

    private String cpu;
    private String gpu;
    private String ram;
    private String storage;
    private String screen;
    private String keyboardType;
    private String connectivity;

    private Long promotionId;
}
