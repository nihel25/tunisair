package tn.esprit.tunisair.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identifiant unique du profil

    private String age;
    private String niveauEtudes;
    private String heuresDisponiblesSemaine;
    private String montantBudget;
    private String nombrePersonnesGroupe;
    private String formatFormation;
    private String engagementsProfessionnels;

    @OneToMany(mappedBy = "userProfile")
    @JsonIgnore
    private List<Formation> formation;
}

