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
import org.springframework.web.multipart.MultipartFile;

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
    public ProductResponseDTO createProduct(ProductDTO productDTO, MultipartFile mainImage,
                                            List<MultipartFile> images, List<MultipartFile> videos) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .brand(productDTO.getBrand())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .category(productDTO.getCategory())
                .description(productDTO.getDescription())
                .cpu(productDTO.getCpu())
                .ram(productDTO.getRam())
                .storage(productDTO.getStorage())
                .gpu(productDTO.getGpu())
                .display(productDTO.getDisplay())
                .mainboard(productDTO.getMainboard())
                .psu(productDTO.getPsu())
                .monitor(productDTO.getMonitor())
                .os(productDTO.getOs())
                .type(productDTO.getType())
                .build();
        if (mainImage != null) {
            product.setMainImage(cloudinaryService.uploadFile(mainImage));
        }
        if (images != null && !images.isEmpty()) {
            List<String> imageUrls = images.stream()
                    .map(cloudinaryService::uploadFile)
                    .toList();
            product.setImages(imageUrls);
        }
        if (videos != null && !videos.isEmpty()) {
            List<String> videoUrls = videos.stream()
                    .map(cloudinaryService::uploadFile)
                    .toList();
            product.setVideos(videoUrls);
        }
        Product saved = productRepository.save(product);

        return ProductResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .brand(saved.getBrand())
                .price(saved.getPrice())
                .stock(saved.getStock())
                .category(saved.getCategory())
                .description(saved.getDescription())
                .cpu(saved.getCpu())
                .ram(saved.getRam())
                .storage(saved.getStorage())
                .gpu(saved.getGpu())
                .display(saved.getDisplay())
                .mainboard(saved.getMainboard())
                .psu(saved.getPsu())
                .monitor(saved.getMonitor())
                .os(saved.getOs())
                .type(saved.getType())
                .mainImage(saved.getMainImage())
                .images(saved.getImages())
                .videos(saved.getVideos())
                .build();
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductDTO dto,
                                            MultipartFile mainImage,
                                            List<MultipartFile> images,
                                            List<MultipartFile> videos) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // update text fields
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

        // update mainImage nếu có file mới
        if (mainImage != null) {
            product.setMainImage(cloudinaryService.uploadFile(mainImage));
        }

        // update images nếu có file mới
        if (images != null && !images.isEmpty()) {
            List<String> imageUrls = images.stream()
                    .map(cloudinaryService::uploadFile)
                    .toList();
            product.setImages(imageUrls);
        }

        // update videos nếu có file mới
        if (videos != null && !videos.isEmpty()) {
            List<String> videoUrls = videos.stream()
                    .map(cloudinaryService::uploadFile)
                    .toList();
            product.setVideos(videoUrls);
        }

        Product saved = productRepository.save(product);

        // map to response DTO
        return ProductResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .brand(saved.getBrand())
                .price(saved.getPrice())
                .stock(saved.getStock())
                .category(saved.getCategory())
                .description(saved.getDescription())
                .cpu(saved.getCpu())
                .ram(saved.getRam())
                .storage(saved.getStorage())
                .gpu(saved.getGpu())
                .display(saved.getDisplay())
                .mainboard(saved.getMainboard())
                .psu(saved.getPsu())
                .monitor(saved.getMonitor())
                .os(saved.getOs())
                .type(saved.getType())
                .mainImage(saved.getMainImage())
                .images(saved.getImages())
                .videos(saved.getVideos())
                .build();
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
