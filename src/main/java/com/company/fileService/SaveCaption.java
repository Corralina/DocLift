package com.company.fileService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class SaveCaption {
    @Value("${upload.path.caption}")
    private String uploadPathImage;

    public String uploadImg(MultipartFile file) throws IOException {
        File uploadDir = new File(this.uploadPathImage);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "." + file.getOriginalFilename();
        file.transferTo(new File(this.uploadPathImage + "/" + resultFileName));
        return resultFileName;
    }
}
