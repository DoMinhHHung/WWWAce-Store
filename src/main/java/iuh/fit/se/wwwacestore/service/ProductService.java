package iuh.fit.se.wwwacestore.service;

import iuh.fit.se.wwwacestore.dto.request.ProductForm;
import iuh.fit.se.wwwacestore.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductForm form, String mainImageUrl, java.util.List<String> imageUrls);
    ProductResponse update(Long id, ProductForm form, String mainImageUrl, java.util.List<String> imageUrls);
    void delete(Long id);
    ProductResponse getById(Long id);
    List<ProductResponse> getAll();
}
