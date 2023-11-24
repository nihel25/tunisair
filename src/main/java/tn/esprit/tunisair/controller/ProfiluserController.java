package tn.esprit.tunisair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tunisair.entity.UserProfile;
import tn.esprit.tunisair.service.UserProfileService;

import java.util.List;


@RestController
@RequestMapping("/profil")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfiluserController {




private final UserProfileService userProfileService;
    @Autowired
    public ProfiluserController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Secured("COORDINATEURENTREPRISE")

    @PostMapping(value = "/profiluser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String chargerCSV(@RequestPart("fichier") MultipartFile fichierCSV) {
        userProfileService.chargerDonneesCSV(fichierCSV);
        return null;
    }


    @Secured("COORDINATEURFORMATION")

    @GetMapping("/user-profiles")
    public List<UserProfile> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }
}
