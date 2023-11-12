package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tunisair.entity.Personnel;
import tn.esprit.tunisair.dto.PersonnelDTO;
import tn.esprit.tunisair.repository.PersonnelRepository;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;


@Service
public class PersonnelleServiceImpl implements PersonnelleService{
    @Autowired
    PersonnelRepository personnelRepository;


    @Override
    @Transactional
    public void chargerDonneesCSV(MultipartFile fichierCSV) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fichierCSV.getInputStream()))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] colonnes = ligne.split(";");


                if (colonnes.length >= 5) {
                    String nom = colonnes[0];
                    String prenom = colonnes[1];
                    String cin = colonnes[2];
                    String email = colonnes[3];
                    String fonction = colonnes[4];

                    Personnel personnel = new Personnel();
                    personnel.setNom(nom);
                    personnel.setPrenom(prenom);
                    personnel.setCin(cin);
                    personnel.setEmail(email);
                    personnel.setFonction(fonction);

                    personnelRepository.save(personnel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }









    public List<Personnel> getAllPersonnel() {
        return personnelRepository.findAll();
    }




    @Override
    public PersonnelDTO recherch(Long id) {
        Optional<Personnel> optionalpersonnelle = personnelRepository.findById(id);
        if (optionalpersonnelle.isPresent()) {
            Personnel stagiaire = optionalpersonnelle.get();
            return PersonnelDTO.fromentity(stagiaire);
        } else {
            return null;
        }


    }

    @Override
    public void delete(Long id) {

        personnelRepository.deleteById(id);
    }

    @Override
    public PersonnelDTO save(PersonnelDTO personnelDTO) {
        Personnel personnel = PersonnelDTO.toentity(personnelDTO);
        personnelRepository.save(personnel);
        return  PersonnelDTO.fromentity(personnel);


    }
}
