package iuh.fit.se.acestore.entity;

import iuh.fit.se.acestore.entity.enums.PromotionType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    private PromotionType type;

    private BigDecimal value;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
