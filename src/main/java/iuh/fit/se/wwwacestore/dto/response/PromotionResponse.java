package iuh.fit.se.wwwacestore.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PromotionResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal discountPercent;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
}
