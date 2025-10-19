package iuh.fit.se.wwwacestore.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

public interface CloudinaryService {
    Map uploadFile(MultipartFile file);
    Map deleteFile(String publicId);
}
