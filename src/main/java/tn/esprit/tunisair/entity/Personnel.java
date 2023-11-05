package tn.esprit.tunisair.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personnel")
public class Personnel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

private String cin;
    @Column(name = "prenom")
    private String prenom;


    private String fonction;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy ="personnel")
    private Set<Attestation> attestations;

}
