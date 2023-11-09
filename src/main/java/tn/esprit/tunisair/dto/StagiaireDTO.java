package tn.esprit.tunisair.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.tunisair.entity.Stagiaire;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StagiaireDTO {
	private Long id;
	@NotNull(message="it should be not null")
	private String prenom;
	@NotNull(message="it should be not null")
	private String nom;

	private String telephone;

	private String email;
	@NotNull(message="it should be not null")
	private String nomUniv;
	@NotNull(message="it should be not null")
	private StageDTO stagedto;

	@NotNull(message="it should be not null")
	private String cin;

	@NotNull(message="it should be not null")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date datenaissance;

	public static Stagiaire toEntity(StagiaireDTO dto) {

		if (dto == null) {
			return null; // Ou lancez une exception appropriée selon votre logique métier.
		}
		return Stagiaire.builder()


.id(dto.getId())


				.telephone(dto.getTelephone())
				.email(dto.getEmail())
				.nomUniv(dto.getNomUniv())
				.nom(dto.getNom())
				.prenom(dto.getPrenom())
				.stage(StageDTO.toEntity(dto.getStagedto()))
				.cin(dto.getCin())
				.datenaissance(dto.getDatenaissance())
				.build();
	}








	public static StagiaireDTO fromEntity(Stagiaire stage){

		if (stage == null) {
			return null; // Ou lancez une exception appropriée selon votre logique métier.
		}

		return StagiaireDTO.builder()
				.id(stage.getId())
				.cin(stage.getCin())
				.datenaissance(stage.getDatenaissance())
				.telephone(stage.getTelephone())
				.email(stage.getEmail())
				.nomUniv(stage.getNomUniv())
				.nom(stage.getNom())
				.prenom(stage.getPrenom())
				.stagedto(StageDTO.fromEntity(stage.getStage()))
				.build();
	}

}
