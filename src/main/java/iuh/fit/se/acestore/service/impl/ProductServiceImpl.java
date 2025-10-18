package iuh.fit.se.acestore.service.impl;

import iuh.fit.se.acestore.dto.request.ProductDTO;
import iuh.fit.se.acestore.dto.response.ProductResponseDTO;
import iuh.fit.se.acestore.entity.Product;
import iuh.fit.se.acestore.entity.enums.ProductType;
import iuh.fit.se.acestore.repository.ProductRepository;
import iuh.fit.se.acestore.service.CloudinaryService;
import iuh.fit.se.acestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToResponseDTO(product);
    }

    @Override
    public ProductResponseDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        mapDTOToProduct(productDTO, product);
        Product saved = productRepository.save(product);
        return mapToResponseDTO(saved);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        mapDTOToProduct(productDTO, product);
        Product updated = productRepository.save(product);
        return mapToResponseDTO(updated);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);

    }

    @Override
    public List<ProductResponseDTO> getProductsByType(String type) {
        ProductType productType;
        try{
            productType = ProductType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new RuntimeException("Product type not found" + type);
        }
        return productRepository.findByType(productType).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDTO> getProductsByName(String name) {
        return productRepository.findByName(name)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private void mapDTOToProduct(ProductDTO dto, Product product) {
        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(dto.getCategory());
        product.setDescription(dto.getDescription());
        product.setCpu(dto.getCpu());
        product.setRam(dto.getRam());
        product.setStorage(dto.getStorage());
        product.setGpu(dto.getGpu());
        product.setDisplay(dto.getDisplay());
        product.setMainboard(dto.getMainboard());
        product.setPsu(dto.getPsu());
        product.setMonitor(dto.getMonitor());
        product.setOs(dto.getOs());
        product.setType(dto.getType());

        // upload media
        if (dto.getMainImage() != null) {
            product.setMainImage(cloudinaryService.uploadFile(dto.getMainImage()));
        }
        if (dto.getImages() != null && !dto.getImages().isEmpty()) {
            product.setImages(dto.getImages().stream()
                    .map(cloudinaryService::uploadFile)
                    .collect(Collectors.toList()));
        }
        if (dto.getVideos() != null && !dto.getVideos().isEmpty()) {
            product.setVideos(dto.getVideos().stream()
                    .map(cloudinaryService::uploadFile)
                    .collect(Collectors.toList()));
        }
    }

    private ProductResponseDTO mapToResponseDTO(Product product) {
        BigDecimal salePrice = product.getPrice();

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .price(product.getPrice())
                .salePrice(salePrice)
                .stock(product.getStock())
                .category(product.getCategory())
                .description(product.getDescription())
                .cpu(product.getCpu())
                .ram(product.getRam())
                .storage(product.getStorage())
                .gpu(product.getGpu())
                .display(product.getDisplay())
                .mainboard(product.getMainboard())
                .psu(product.getPsu())
                .monitor(product.getMonitor())
                .os(product.getOs())
                .type(product.getType())
                .mainImage(product.getMainImage())
                .images(product.getImages())
                .videos(product.getVideos())
                .promotions(null)
                .build();
    }
}
