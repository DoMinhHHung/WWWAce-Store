package iuh.fit.se.acestore.dto.response;

import java.math.BigDecimal;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionInfo {
    private Long id;
    private String name;
    private String description;
    private String type;
    private BigDecimal value;
}
