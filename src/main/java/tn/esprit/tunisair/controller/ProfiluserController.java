package tn.esprit.tunisair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tunisair.service.UserProfileService;
import tn.esprit.tunisair.entity.UserProfile;

import java.util.List;


@RestController
@RequestMapping("/profil")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfiluserController {



@Autowired
    UserProfileService userProfileService;


    @Secured("coordinateurentreprise")

    @PostMapping(value = "/profiluser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String chargerCSV(@RequestPart("fichier") MultipartFile fichierCSV) {
        userProfileService.chargerDonneesCSV(fichierCSV);
        return null;
    }


    @Secured("coordinateurformation")

    @GetMapping("/user-profiles")
    public List<UserProfile> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }
}
