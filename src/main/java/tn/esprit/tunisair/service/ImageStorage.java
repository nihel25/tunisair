package tn.esprit.tunisair.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
public interface ImageStorage {

    String store(MultipartFile file);
    Resource loadResource(String filename);

  

   ResponseEntity<Resource> downloadUserImage(String imageName, HttpServletRequest request);
}
