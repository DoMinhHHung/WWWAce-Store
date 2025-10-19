package iuh.fit.se.wwwacestore.controller;

import iuh.fit.se.wwwacestore.dto.request.ProductForm;
import iuh.fit.se.wwwacestore.dto.response.ApiResponse;
import iuh.fit.se.wwwacestore.dto.response.ProductResponse;
import iuh.fit.se.wwwacestore.service.CloudinaryService;
import iuh.fit.se.wwwacestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CloudinaryService cloudinaryService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<ProductResponse>> create(@ModelAttribute ProductForm form) {
        String mainImageUrl = null;
        if (form.getMainImage() != null && !form.getMainImage().isEmpty()) {
            Map upload = cloudinaryService.uploadFile(form.getMainImage());
            mainImageUrl = String.valueOf(upload.get("secure_url"));
        }

        List<String> imageUrls = null;
        if (form.getImages() != null && form.getImages().length > 0) {
            List<Map> results = cloudinaryService.uploadFiles(form.getImages());
            imageUrls = results.stream().map(m -> String.valueOf(m.get("secure_url"))).collect(Collectors.toList());
        }

        ProductResponse res = productService.create(form, mainImageUrl, imageUrls);
        return ResponseEntity.ok(ApiResponse.success("Product created", res));
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<ProductResponse>> update(@PathVariable Long id, @ModelAttribute ProductForm form) {
        String mainImageUrl = null;
        if (form.getMainImage() != null && !form.getMainImage().isEmpty()) {
            Map upload = cloudinaryService.uploadFile(form.getMainImage());
            mainImageUrl = String.valueOf(upload.get("secure_url"));
        }

        List<String> imageUrls = null;
        if (form.getImages() != null && form.getImages().length > 0) {
            List<Map> results = cloudinaryService.uploadFiles(form.getImages());
            imageUrls = results.stream().map(m -> String.valueOf(m.get("secure_url"))).collect(Collectors.toList());
        }

        ProductResponse res = productService.update(id, form, mainImageUrl, imageUrls);
        return ResponseEntity.ok(ApiResponse.success("Product updated", res));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Found", productService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success("List", productService.getAll()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Deleted", null));
    }
}
