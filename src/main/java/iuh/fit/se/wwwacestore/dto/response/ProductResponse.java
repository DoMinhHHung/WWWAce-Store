package iuh.fit.se.wwwacestore.dto.response;

import iuh.fit.se.wwwacestore.entity.enums.ProductType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String brand;
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
    private String promotionName;
    private BigDecimal discountPercent;
}
