package tn.esprit.tunisair.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Specialitee  {





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String types;



    @OneToMany(mappedBy = "specialitee")
    @JsonIgnore
    private List<Formateur> formateurs;







}
