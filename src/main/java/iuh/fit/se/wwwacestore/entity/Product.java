package iuh.fit.se.wwwacestore.entity;

import iuh.fit.se.wwwacestore.entity.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private BigDecimal price;
    private Integer stock;

    private String mainImage;

    @ElementCollection
    private List<String> images;

    private String cpu;
    private String gpu;
    private String ram;
    private String storage;
    private String screen;
    private String keyboardType;
    private String connectivity;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;
}
