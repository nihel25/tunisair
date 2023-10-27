package tn.esprit.tunisair.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tn.esprit.tunisair.entity.User;
import tn.esprit.tunisair.entity.UserRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public   class UserDTO {



    private Long id;
    @NotNull
    private String fullname;
    @NotNull
    private String adresse;
    @NotNull
    @Pattern(regexp="^[0-9]{8}$",message="it should be a number ")
    private String telephone;
    @NotNull

    private String cin;
    @NotNull

    private String prenom;
    @Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",message="it should be an email ")
    private String email;
    @NotNull
    private  String password;
    @NotNull

    @Enumerated(EnumType.STRING)
    private UserRole role;



    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .adresse(user.getAdresse())
                .telephone(user.getTelephone())
                .email(user.getEmail())
                .prenom(user.getPrenom())
                .password(user.getPassword())
                .role(user.getRole())
                .cin(user.getCin())

                .build();

    }
    public static User toEntity(UserDTO user) {
        return User.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .adresse(user.getAdresse())
                .telephone(user.getTelephone())
                .email(user.getEmail())
                .prenom(user.getPrenom())
                .password(user.getPassword())
                .role(user.getRole())
                .cin(user.getCin())
                .build();

    }


}
