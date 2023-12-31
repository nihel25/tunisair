package tn.esprit.tunisair.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Attestation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datenew = new Date(System.currentTimeMillis());
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;


    @ManyToOne
 
    private Personnel personnel;


    @ManyToOne
    private Formation formation;

}
