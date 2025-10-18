package iuh.fit.se.acestore.service;

import iuh.fit.se.acestore.dto.request.ProductDTO;
import iuh.fit.se.acestore.dto.response.ProductResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO createProduct(ProductDTO productDTO, MultipartFile mainImage, List<MultipartFile> images, List<MultipartFile> videos);
    ProductResponseDTO updateProduct(Long id, ProductDTO dto, MultipartFile mainImage, List<MultipartFile> images, List<MultipartFile> videos);
    void deleteProduct(Long id);
    public List<ProductResponseDTO> getProductsByType(String type);
    public List<ProductResponseDTO> getProductsByName(String name);

}

