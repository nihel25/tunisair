package tn.esprit.tunisair.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tn.esprit.tunisair.authrequestresponse.RegistrationRequest;
import tn.esprit.tunisair.entity.Admin;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminDTO extends RegistrationRequest {

    private long ages;


    public static Admin toEntity(AdminDTO request) {
        return Admin.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .password(request.getPassword())
                .adresse(request.getAdresse())
                .telephone(request.getTelephone())
                .prenom(request.getPrenom())
                .cin(request.getCin())


                .build();
    }
}
