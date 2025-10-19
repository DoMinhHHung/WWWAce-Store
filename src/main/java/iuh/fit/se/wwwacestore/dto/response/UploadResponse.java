package iuh.fit.se.wwwacestore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResponse {
    private String url;
    private String publicId;
}