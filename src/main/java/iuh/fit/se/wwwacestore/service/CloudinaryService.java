package iuh.fit.se.wwwacestore.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CloudinaryService {
    Map uploadFile(MultipartFile file);
    List<Map> uploadFiles(MultipartFile[] files);
    Map deleteFile(String publicId);
}
