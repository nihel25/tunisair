package tn.esprit.tunisair.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Formation  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String ref;
    private double remise;
    private String image;
    private String  formationtype;
    @Column(length = 2000)
private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateformation = new Date(System.currentTimeMillis());

    private double  prix;
    @ManyToOne
    private User user;

    public Formation(Long id, String formationType, float prix) {
        this.id = id;
        this.formationtype = formationType;
        this.prix = prix;
    }



    @ManyToOne

    UserProfile userProfile;
    @JsonIgnore
    @OneToMany(mappedBy ="formation")
    private Set<Session> session;

    @JsonIgnore
    @OneToMany(mappedBy ="formation")
    private Set<Attestation> attestations;
@ManyToOne

     private Formateur formateur;




    @JsonIgnore
    @OneToMany(mappedBy = "formation")
    private Set<Demandeclient> demandeclients;



    @JsonIgnore
    @OneToMany(mappedBy = "formation")
    private Set<Reclamation> reclamation;




    @JsonIgnore
    @OneToMany(mappedBy = "formation")
    private Set<Demandeformation> demandeformations;




}
