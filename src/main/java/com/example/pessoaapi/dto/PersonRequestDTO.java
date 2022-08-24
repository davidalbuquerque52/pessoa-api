package com.example.pessoaapi.dto;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class PersonRequestDTO {

    @NotNull
    @Schema(name = "name", description = "person's name", required = true)
    private String name;

    @NotNull
    @Size(min = 11, max = 18)
    @Schema(name = "identifier", description = "person's docment wich can be CPF or CNPJ", required = true)
    private String identifier;

}
