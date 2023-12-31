package tn.esprit.tunisair.dto;//package tn.esprit.formation.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tn.esprit.tunisair.entity.CoordinateurEntreprise;
import tn.esprit.tunisair.authrequestresponse.RegistrationRequest;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CoordinateurEntrepriseDTO extends RegistrationRequest {


@NotNull
    private String fonctions;


    public static CoordinateurEntreprise toEntity(CoordinateurEntrepriseDTO request) {
        return CoordinateurEntreprise.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .password(request.getPassword())
                .adresse(request.getAdresse())
                .telephone(request.getTelephone())
                .cin(request.getCin())
                .prenom(request.getPrenom())
                .fonctions(request.getFonctions())
                .build();
    }
}
