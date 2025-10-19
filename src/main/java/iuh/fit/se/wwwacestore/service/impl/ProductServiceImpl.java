package iuh.fit.se.wwwacestore.service.impl;

import iuh.fit.se.wwwacestore.dto.request.ProductForm;
import iuh.fit.se.wwwacestore.dto.response.ProductResponse;
import iuh.fit.se.wwwacestore.entity.Product;
import iuh.fit.se.wwwacestore.entity.Promotion;
import iuh.fit.se.wwwacestore.exception.ResourceNotFoundException;
import iuh.fit.se.wwwacestore.repository.ProductRepository;
import iuh.fit.se.wwwacestore.repository.PromotionRepository;
import iuh.fit.se.wwwacestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final PromotionRepository promotionRepo;

    @Override
    @Transactional
    public ProductResponse create(ProductForm form, String mainImageUrl, List<String> imageUrls) {
        Promotion promo = null;
        if (form.getPromotionId() != null) {
            promo = promotionRepo.findById(form.getPromotionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Promotion not found with id = " + form.getPromotionId()));
        }

        Product p = Product.builder()
                .name(form.getName())
                .brand(form.getBrand())
                .description(form.getDescription())
                .type(form.getType())
                .price(form.getPrice())
                .stock(form.getStock())
                .mainImage(mainImageUrl)
                .images(imageUrls)
                .cpu(form.getCpu())
                .gpu(form.getGpu())
                .ram(form.getRam())
                .storage(form.getStorage())
                .screen(form.getScreen())
                .keyboardType(form.getKeyboardType())
                .connectivity(form.getConnectivity())
                .promotion(promo)
                .build();

        Product saved = productRepo.save(p);
        return mapToResponse(saved);
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductForm form, String mainImageUrl, List<String> imageUrls) {
        Product p = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id = " + id));

        if (form.getPromotionId() != null) {
            Promotion promo = promotionRepo.findById(form.getPromotionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Promotion not found with id = " + form.getPromotionId()));
            p.setPromotion(promo);
        }
        if (form.getName() != null && !form.getName().isBlank()) p.setName(form.getName());
        if (form.getBrand() != null && !form.getBrand().isBlank()) p.setBrand(form.getBrand());
        if (form.getDescription() != null && !form.getDescription().isBlank()) p.setDescription(form.getDescription());
        if (form.getType() != null) p.setType(form.getType());
        if (form.getPrice() != null) p.setPrice(form.getPrice());
        if (form.getStock() != null) p.setStock(form.getStock());

        if (form.getCpu() != null && !form.getCpu().isBlank()) p.setCpu(form.getCpu());
        if (form.getGpu() != null && !form.getGpu().isBlank()) p.setGpu(form.getGpu());
        if (form.getRam() != null && !form.getRam().isBlank()) p.setRam(form.getRam());
        if (form.getStorage() != null && !form.getStorage().isBlank()) p.setStorage(form.getStorage());
        if (form.getScreen() != null && !form.getScreen().isBlank()) p.setScreen(form.getScreen());
        if (form.getKeyboardType() != null && !form.getKeyboardType().isBlank()) p.setKeyboardType(form.getKeyboardType());
        if (form.getConnectivity() != null && !form.getConnectivity().isBlank()) p.setConnectivity(form.getConnectivity());

        if (mainImageUrl != null && !mainImageUrl.isBlank()) {
            p.setMainImage(mainImageUrl);
        }
        if (imageUrls != null && !imageUrls.isEmpty()) {
            p.setImages(imageUrls);
        }

        Product updated = productRepo.save(p);
        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!productRepo.existsById(id)) throw new ResourceNotFoundException("Product not found with id = " + id);
        productRepo.deleteById(id);
    }

    @Override
    public ProductResponse getById(Long id) {
        Product p = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id = " + id));
        return mapToResponse(p);
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepo.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToResponse(Product p) {
        ProductResponse.ProductPromotionSummary promo = null;
        if (p.getPromotion() != null) {
            Promotion pr = p.getPromotion();
            promo = ProductResponse.ProductPromotionSummary.builder()
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
