package tn.esprit.tunisair.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class PDFFileService {

    private static final String PDF_DIRECTORY = "/path/to/your/pdf/directory/";

    public void uploadPDFs(MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            // Vérifiez que le fichier est un fichier PDF
            if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
                throw new IllegalArgumentException("Veuillez télécharger uniquement des fichiers PDF.");
            }

            // Obtenez le nom du fichier
            String filename = file.getOriginalFilename();

            // Stockez le fichier localement
            byte[] bytes = file.getBytes();
            Path path = Paths.get(PDF_DIRECTORY + filename);
            Files.write(path, bytes);
        }
    }

    // Méthode pour récupérer la liste des fichiers PDF
    public List<String> listPDFs() {
        File directory = new File(PDF_DIRECTORY);
        String[] files = directory.list((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        return Arrays.asList(files != null ? files : new String[0]);
    }
}
