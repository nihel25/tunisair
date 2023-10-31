package tn.esprit.tunisair.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;
import tn.esprit.tunisair.AuthRequestResponse.RegistrationRequest;
import tn.esprit.tunisair.entity.Client;



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


