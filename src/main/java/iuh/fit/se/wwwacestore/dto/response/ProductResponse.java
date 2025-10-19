package iuh.fit.se.wwwacestore.dto.response;

import iuh.fit.se.wwwacestore.entity.enums.ProductType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String brand;
    private ProductType type;
    private BigDecimal price;
    private Integer stock;
    private String mainImage;
    private List<String> images;
    private String cpu;
    private String gpu;
    private String ram;
    private String storage;
    private String screen;
    private String keyboardType;
    private String connectivity;
    private ProductPromotionSummary promotion;

    @Data
    @Builder
    public static class ProductPromotionSummary {
        private Long id;
        private String name;
        private double discountPercent;
    }

    public static ProductResponse fromEntity(iuh.fit.se.wwwacestore.entity.Product p) {
        ProductPromotionSummary promo = null;
        if (p.getPromotion() != null) {
            PromotionResponse pr = PromotionResponse.builder()
                    .id(p.getPromotion().getId())
                    .name(p.getPromotion().getName())
                    .description(p.getPromotion().getDescription())
                    .discountPercent(p.getPromotion().getDiscountPercent())
                    .startDate(p.getPromotion().getStartDate())
                    .endDate(p.getPromotion().getEndDate())
                    .active(p.getPromotion().isActive())
                    .build();

            promo = ProductPromotionSummary.builder()
                    .id(pr.getId())
                    .name(pr.getName())
                    .discountPercent(pr.getDiscountPercent())
                    .build();
        }

        return ProductResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .brand(p.getBrand())
                .type(p.getType())
                .price(p.getPrice())
                .stock(p.getStock())
                .mainImage(p.getMainImage())
                .images(p.getImages())
                .cpu(p.getCpu())
                .gpu(p.getGpu())
                .ram(p.getRam())
                .storage(p.getStorage())
                .screen(p.getScreen())
                .keyboardType(p.getKeyboardType())
                .connectivity(p.getConnectivity())
                .promotion(promo)
                .build();
    }
}
