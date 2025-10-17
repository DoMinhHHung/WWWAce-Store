package iuh.fit.se.acestore.dto.request;

import iuh.fit.se.acestore.entity.enums.ProductCategory;
import iuh.fit.se.acestore.entity.enums.ProductType;
import lombok.*;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;

    @NotNull(message = "Category is required")
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

    @NotNull(message = "Product type is required")
    private ProductType type;

    private MultipartFile mainImage;
    private List<MultipartFile> images;
    private List<MultipartFile> videos;
}
