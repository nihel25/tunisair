package tn.esprit.tunisair.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.tunisair.entity.Encadreur;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EncadreurDTO  {


    private Long id;
    @NotNull
    private String nom;
    @NotNull
    private String prenom;

    private String email;
@NotNull
    private String fonctionEncad;



    public static EncadreurDTO fromEntity(Encadreur request) {
        if (request == null) {
            return null; // Ou lancez une exception appropriée selon votre logique métier.
        }
        return EncadreurDTO.builder()
                .id(request.getId())
                .nom(request.getNom())
                .email(request.getEmail())
                .prenom(request.getPrenom())
                .fonctionEncad(request.getFonctionEncad())

                .build();


    }



	public static Encadreur toEntity(EncadreurDTO request){
        if (request == null) {
            return null; // Ou lancez une exception appropriée selon votre logique métier.
        }

		return Encadreur.builder()
                .id(request.getId())
                .nom(request.getNom())
                .email(request.getEmail())
                .prenom(request.getPrenom())
                .fonctionEncad(request.getFonctionEncad())

                .build();
	}

}
