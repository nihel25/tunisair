package tn.esprit.tunisair.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @DiscriminatorValue("stagaire")

    @SuperBuilder
    public class Stagiaire implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

    private String nomUniv;

private String nom;
    private String prenom;
    private String telephone;

    @Column(unique = true)
    private String email;

    private String cin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datenaissance;
    @ManyToOne
    @JsonIgnore
    private Stage stage;
    @OneToMany(mappedBy = "stagiaire")
    @JsonIgnore
    private List<Certificat> certificats;

        


    }

