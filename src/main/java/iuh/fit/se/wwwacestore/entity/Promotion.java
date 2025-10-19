package iuh.fit.se.wwwacestore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promotions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String description;

    private double discountPercent;

    private LocalDate startDate;
    private LocalDate endDate;

    private boolean active;

    @OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY)
    private List<Product> products;
}
