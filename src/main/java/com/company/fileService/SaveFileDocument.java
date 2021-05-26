package com.company.fileService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class SaveFileDocument {

    @Value("${upload.path.document}")
    private String uploadPathDocument;



    public String uploadDocument(MultipartFile file) throws IOException {
        File uploadDir = new File(this.uploadPathDocument);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "." + file.getOriginalFilename();
        file.transferTo(new File(this.uploadPathDocument + "/" + resultFileName));
        return resultFileName;
    }



}
