package iuh.fit.se.wwwacestore.controller;

import iuh.fit.se.wwwacestore.dto.request.PromotionRequest;
import iuh.fit.se.wwwacestore.dto.response.ApiResponse;
import iuh.fit.se.wwwacestore.dto.response.PromotionResponse;
import iuh.fit.se.wwwacestore.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping
    public ResponseEntity<ApiResponse<PromotionResponse>> create(@RequestBody PromotionRequest req) {
        return ResponseEntity.ok(ApiResponse.success("Promotion created", promotionService.create(req)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PromotionResponse>> update(@PathVariable Long id, @RequestBody PromotionRequest req) {
        return ResponseEntity.ok(ApiResponse.success("Promotion updated", promotionService.update(id, req)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PromotionResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Found", promotionService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PromotionResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success("List", promotionService.getAll()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
        promotionService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Deleted", null));
    }
}
