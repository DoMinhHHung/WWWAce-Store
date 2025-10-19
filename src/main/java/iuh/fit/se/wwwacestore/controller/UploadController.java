package iuh.fit.se.wwwacestore.controller;

import iuh.fit.se.wwwacestore.dto.response.UploadResponse;
import iuh.fit.se.wwwacestore.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<UploadResponse> upload(@RequestParam("file") MultipartFile file) {
        Map uploadResult = cloudinaryService.uploadFile(file);
        return ResponseEntity.ok(
                new UploadResponse(
                        uploadResult.get("secure_url").toString(),
                        uploadResult.get("public_id").toString()
                )
        );
    }
    @PostMapping("/multi")
    public ResponseEntity<List<UploadResponse>> uploadMultiple(@RequestParam("files") List<MultipartFile> files) {
        List<UploadResponse> responses = files.stream()
                .map(file -> {
                    Map result = cloudinaryService.uploadFile(file);
                    return new UploadResponse(
                            result.get("secure_url").toString(),
                            result.get("public_id").toString()
                    );
                })
                .toList();

        return ResponseEntity.ok(responses);
    }

    @DeleteMapping
    public ResponseEntity<Map> delete(@RequestParam("public_id") String publicId) {
        return ResponseEntity.ok(cloudinaryService.deleteFile(publicId));
    }
}