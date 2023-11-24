package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tunisair.entity.UserProfile;
import tn.esprit.tunisair.repository.UserProfileRepository;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class UserProfileService {



  private final  UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Transactional
    public void chargerDonneesCSV(MultipartFile fichierCSV) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fichierCSV.getInputStream()))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] colonnes = ligne.split(";");



                if (colonnes.length >= 5) {
                    String age = colonnes[0];
                    String niveauEtudes = colonnes[1];
                    String heuresDisponiblesSemaine = colonnes[2];
                    String montantBudget = colonnes[3];
                    String nombrePersonnesGroupe = colonnes[4];
                    String formatFormation = colonnes[5];
                    String engagementsProfessionnels = colonnes[6];

                    UserProfile personnel = new UserProfile();




                    personnel.setAge(age);
                    personnel.setNiveauEtudes(niveauEtudes);
                    personnel.setHeuresDisponiblesSemaine(heuresDisponiblesSemaine);
                    personnel.setMontantBudget(montantBudget);
                    personnel.setNombrePersonnesGroupe(nombrePersonnesGroupe);
                    personnel.setFormatFormation(formatFormation);
                    personnel.setEngagementsProfessionnels(engagementsProfessionnels);

                    userProfileRepository.save(personnel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

}
