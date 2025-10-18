package iuh.fit.se.acestore.service;

import iuh.fit.se.acestore.dto.request.ProductDTO;
import iuh.fit.se.acestore.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO createProduct(ProductDTO productDTO);
    ProductResponseDTO updateProduct(Long id,ProductDTO productDTO);
    void deleteProduct(Long id);
    public List<ProductResponseDTO> getProductsByType(String type);
    public List<ProductResponseDTO> getProductsByName(String name);

}

