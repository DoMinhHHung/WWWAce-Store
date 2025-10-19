package iuh.fit.se.wwwacestore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionRequest {
    private String name;
    private String description;
    private double discountPercent;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
}
