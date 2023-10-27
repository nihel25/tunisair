package tn.esprit.tunisair.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.tunisair.entity.Specialitee;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialiteeDTO {


    private Long id;
    @NotNull
    private String types;

    public static Specialitee toentity(SpecialiteeDTO dto) {
        return Specialitee.builder()


                .id(dto.getId())


                .types(dto.getTypes())

                .build();
    }








    public static SpecialiteeDTO fromEntity(Specialitee sp){

        return SpecialiteeDTO.builder()
                .id(sp.getId())

                .types(sp.getTypes())

                .build();
    }





















}
