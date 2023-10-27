package tn.esprit.tunisair.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RequestMapping("filepdf")
@RestController
public class PDFFileController {

    private static final String PDF_DIRECTORY = "C:/Users/hp/Desktop/pdf";


    @Secured("coordinateurentreprise")
    @PostMapping("/uploadPDFs")
    public ResponseEntity<String> uploadPDFs(@RequestParam("files") MultipartFile[] files) {
        try {
            for (MultipartFile file : files) {
                // Vérifiez que le fichier est un fichier PDF
                if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Veuillez télécharger uniquement des fichiers PDF.");
                }

                // Obtenez le nom du fichier
                String filename = file.getOriginalFilename();

                // Stockez le fichier localement
                byte[] bytes = file.getBytes();
                Path path = Paths.get(PDF_DIRECTORY + filename);
                Files.write(path, bytes);
            }

            return ResponseEntity.status(HttpStatus.OK).body("Fichiers PDF ajoutés avec succès.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'ajout des fichiers PDF.");
        }
    }

    // ...
}
