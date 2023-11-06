package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service("UserImageStorageImpl")
public class UserImageStorageImpl implements ImageStorage {


    private final Path imageLocation;

    @Autowired
    public UserImageStorageImpl(tn.esprit.tunisair.configimage.FileStorageProperties fileStorageProperties) {
        this.imageLocation = Paths.get(fileStorageProperties.getUploadImgUsersDir()).toAbsolutePath().normalize();
        try {

        } catch (Exception e) {
            throw new tn.esprit.tunisair.exceptions.FileStorageException("could not create the directory where the uploaded images will be stored", e);
        }
    }


    @Override
    public String store(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new tn.esprit.tunisair.exceptions.FileStorageException("File name contains invalid path sequence " + fileName);
            }
            Files.copy(file.getInputStream(), this.imageLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Fail", e);
        }
        return file.getOriginalFilename();
    }

    @Override
    public Resource loadResource(String filename) {
        try {
            Path path = imageLocation.resolve(filename);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Fail");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Fail");
        }
    }







    @Override
    public ResponseEntity<Resource> downloadUserImage(String imageName, HttpServletRequest request) {
        Resource resource = this.loadResource(imageName);
        String contentType = null;

        if (resource != null) {
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (contentType != null) {
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }
        }

        // Gérer le cas où la ressource est null ou le type de contenu est null.
        throw new RuntimeException("La ressource demandée est introuvable.");
    }

}




