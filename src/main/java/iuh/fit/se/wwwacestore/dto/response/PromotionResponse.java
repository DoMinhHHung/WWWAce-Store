package iuh.fit.se.wwwacestore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionResponse {
    private Long id;
    private String name;
    private String description;
    private double discountPercent;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
}
