package tn.esprit.tunisair.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("coordinateurenp")
@SuperBuilder
public class CoordinateurEntreprise extends User{

   private String fonctions;
}
