package iuh.fit.se.wwwacestore.service;

import iuh.fit.se.wwwacestore.dto.request.PromotionRequest;
import iuh.fit.se.wwwacestore.dto.response.PromotionResponse;

import java.util.List;

public interface PromotionService {
    PromotionResponse create(PromotionRequest request);
    PromotionResponse update(Long id, PromotionRequest request);
    void delete(Long id);
    PromotionResponse getById(Long id);
    List<PromotionResponse> getAll();
}
