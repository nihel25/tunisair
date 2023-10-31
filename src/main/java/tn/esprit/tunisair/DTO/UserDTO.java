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
