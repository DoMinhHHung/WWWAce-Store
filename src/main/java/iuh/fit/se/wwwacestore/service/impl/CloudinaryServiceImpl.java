package iuh.fit.se.wwwacestore.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import iuh.fit.se.wwwacestore.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Override
    public Map uploadFile(MultipartFile file) {
        try {
            return cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("folder", "ace_store"));
        } catch (IOException e) {
            throw new RuntimeException("Lỗi upload file lên Cloudinary: " + e.getMessage());
        }
    }

    @Override
    public Map deleteFile(String publicId) {
        try {
            return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("Lỗi xóa file trên Cloudinary: " + e.getMessage());
        }
    }
}
