package tn.esprit.tunisair.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Setter
@Builder
public class Encadreur   {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;

    private String fonctionEncad;


    @OneToMany(mappedBy = "encadreur")
    @JsonIgnore
    private List<Certificat> certificats;


}