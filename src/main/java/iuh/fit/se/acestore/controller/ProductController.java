package iuh.fit.se.acestore.controller;


import iuh.fit.se.acestore.dto.request.ProductDTO;
import iuh.fit.se.acestore.dto.response.ApiResponse;
import iuh.fit.se.acestore.dto.response.ProductResponseDTO;
import iuh.fit.se.acestore.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("ace/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getAllProducts(){
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(ApiResponse.<List<ProductResponseDTO>>builder()
                .success(true)
                .message("All products retrieved successfully")
                .data(products).build());
    }

    @GetMapping("/id")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getProductById(@PathVariable Long id){
        ProductResponseDTO product = productService.getProductById(id);
        return ResponseEntity.ok(ApiResponse.<ProductResponseDTO>builder()
                .success(true)
                .message("Product retrieved successfully")
                .data(product).build()
        );
    }

//    @PostMapping
//    public ResponseEntity<ApiResponse<ProductResponseDTO>> createProduct(@Valid @ModelAttribute ProductDTO dto)
//    {
//        ProductResponseDTO create = productService.createProduct(dto);
//        return new ResponseEntity<>(ApiResponse.<ProductResponseDTO>builder()
//                .success(true)
//                .message("Product created successfully")
//                .data(create).build(), HttpStatus.CREATED
//        );
//    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDTO>> createProduct(
            @RequestPart("product") ProductDTO dto,
            @RequestPart(value = "mainImage", required = false) MultipartFile mainImage,
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestPart(value = "videos", required = false) List<MultipartFile> videos) {

        ProductResponseDTO created = productService.createProduct(dto, mainImage, images, videos);
        return new ResponseEntity<>(ApiResponse.<ProductResponseDTO>builder()
                .success(true)
                .message("Product created successfully")
                .data(created)
                .build(), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> updateProduct(
            @PathVariable Long id,
            @RequestPart("product") ProductDTO dto,
            @RequestPart(value = "mainImage", required = false) MultipartFile mainImage,
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestPart(value = "videos", required = false) List<MultipartFile> videos) {

        ProductResponseDTO updated = productService.updateProduct(id, dto, mainImage, images, videos);

        return ResponseEntity.ok(
                ApiResponse.<ProductResponseDTO>builder()
                        .success(true)
                        .message("Product updated successfully")
                        .data(updated)
                        .build()
        );
    }

    @DeleteMapping("/id")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .message("Product deleted successfully")
                .data(null).build()
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getProductsByName(@PathVariable String name) {
        List<ProductResponseDTO> products = productService.getProductsByName(name);
        return ResponseEntity.ok(ApiResponse.<List<ProductResponseDTO>>builder()
                .success(true)
                .message("Products retrieved by name")
                .data(products)
                .build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getProductsByType(@PathVariable String type) {
        List<ProductResponseDTO> products = productService.getProductsByType(type);
        return ResponseEntity.ok(ApiResponse.<List<ProductResponseDTO>>builder()
                .success(true)
                .message("Products retrieved by type")
                .data(products)
                .build());
    }
}
