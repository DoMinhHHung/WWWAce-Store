package iuh.fit.se.wwwacestore.service.impl;

import iuh.fit.se.wwwacestore.dto.request.PromotionRequest;
import iuh.fit.se.wwwacestore.dto.response.PromotionResponse;
import iuh.fit.se.wwwacestore.entity.Promotion;
import iuh.fit.se.wwwacestore.exception.ResourceNotFoundException;
import iuh.fit.se.wwwacestore.repository.PromotionRepository;
import iuh.fit.se.wwwacestore.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    @Override
    @Transactional
    public PromotionResponse create(PromotionRequest request) {
        Promotion p = Promotion.builder()
                .name(request.getName())
                .description(request.getDescription())
                .discountPercent(request.getDiscountPercent())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .active(request.isActive())
                .build();
        Promotion saved = promotionRepository.save(p);
        return mapToResponse(saved);
    }

    @Override
    @Transactional
    public PromotionResponse update(Long id, PromotionRequest request) {
        Promotion p = promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found with id = " + id));
        p.setName(request.getName());
        p.setDescription(request.getDescription());
        p.setDiscountPercent(request.getDiscountPercent());
        p.setStartDate(request.getStartDate());
        p.setEndDate(request.getEndDate());
        p.setActive(request.isActive());
        Promotion updated = promotionRepository.save(p);
        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!promotionRepository.existsById(id)) throw new ResourceNotFoundException("Promotion not found with id = " + id);
        promotionRepository.deleteById(id);
    }

    @Override
    public PromotionResponse getById(Long id) {
        Promotion p = promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found with id = " + id));
        return mapToResponse(p);
    }

    @Override
    public List<PromotionResponse> getAll() {
        return promotionRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    private PromotionResponse mapToResponse(Promotion p) {
        return PromotionResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .discountPercent(p.getDiscountPercent())
                .startDate(p.getStartDate())
                .endDate(p.getEndDate())
                .active(p.isActive())
                .build();
    }
}
