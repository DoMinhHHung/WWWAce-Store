package iuh.fit.se.acestore.entity;

import iuh.fit.se.acestore.entity.enums.ProductCategory;
import iuh.fit.se.acestore.entity.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private BigDecimal price;
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Lob
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
    private String mainImage;

    @ElementCollection
    private List<String> images;

    @ElementCollection
    private List<String> videos;

    @Enumerated(EnumType.STRING)
    private ProductType type;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Promotion> promotions;
}
