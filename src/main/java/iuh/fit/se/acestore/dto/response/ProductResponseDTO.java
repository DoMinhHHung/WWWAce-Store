package iuh.fit.se.acestore.dto.response;

import iuh.fit.se.acestore.entity.enums.ProductCategory;
import iuh.fit.se.acestore.entity.enums.ProductType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private BigDecimal salePrice;    // price sau khi áp dụng promotion
    private Integer stock;
    private ProductCategory category;
    private String description;
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    private String display;
    private String mainboard;
    private String psu;
    private String monitor;
    private String os;
    private ProductType type;
    private String mainImage;
    private List<String> images;
    private List<String> videos;
    private List<PromotionInfo> promotions;
}
