package iuh.fit.se.wwwacestore.dto.request;

import iuh.fit.se.wwwacestore.entity.enums.ProductType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductForm {
    private String name;
    private String brand;
    private String description;
    private ProductType type;
    private BigDecimal price;
    private Integer stock;
    private Long promotionId;

    private String cpu;
    private String gpu;
    private String ram;
    private String storage;
    private String screen;
    private String keyboardType;
    private String connectivity;

    // files
    private MultipartFile mainImage;
    private MultipartFile[] images;
}
