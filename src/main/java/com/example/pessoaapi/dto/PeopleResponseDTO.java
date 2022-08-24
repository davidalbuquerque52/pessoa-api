package com.example.pessoaapi.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleResponseDTO {
    private List<PersonResponseDTO> peopleResponseDTO;
}
