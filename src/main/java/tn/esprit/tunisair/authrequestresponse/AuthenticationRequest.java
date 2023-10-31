package tn.esprit.tunisair.authrequestresponse;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {

    private String email;

    private String password;

}