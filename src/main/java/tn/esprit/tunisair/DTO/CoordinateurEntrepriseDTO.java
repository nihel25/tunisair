package tn.esprit.tunisair.DTO;//package tn.esprit.formation.DTO;
//

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tn.esprit.tunisair.AuthRequestResponse.RegistrationRequest;
import tn.esprit.tunisair.entity.CoordinateurEntreprise;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CoordinateurEntrepriseDTO extends RegistrationRequest {

//
//
//
@NotNull
    private String Fonctions;


    public static CoordinateurEntreprise toentity(CoordinateurEntrepriseDTO request) {
        return CoordinateurEntreprise.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .password(request.getPassword())
                .adresse(request.getAdresse())
                .telephone(request.getTelephone())
                .cin(request.getCin())
                .prenom(request.getPrenom())
                .Fonctions(request.getFonctions())
                .build();
    }
}
