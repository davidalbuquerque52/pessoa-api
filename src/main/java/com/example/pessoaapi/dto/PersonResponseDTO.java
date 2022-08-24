package com.example.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDTO {

    @Schema(name = "id")
    private Long id;

    @Schema(name = "name", description = "person's name")
    private String name;

    @Schema(name = "identifier", description = "person's docment wich can be CPF or CNPJ number")
    private String identifier;

    @Schema(name = "identifierType", description = "identifier type wich can be CPF or CNPJ type")
    private String identifierType;

}
