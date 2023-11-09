package tn.esprit.tunisair.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import tn.esprit.tunisair.entity.Client;
import tn.esprit.tunisair.authrequestresponse.RegistrationRequest;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ClientDTO extends RegistrationRequest {

private int age;

    public static Client toEntity(ClientDTO request) {
        return Client.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .password(request.getPassword())
                .adresse(request.getAdresse())
                .telephone(request.getTelephone())
                .cin(request.getCin())
                .prenom(request.getPrenom())
                .age(request.getAge())

                .build();
    }


}


