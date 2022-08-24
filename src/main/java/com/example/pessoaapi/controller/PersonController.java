package com.example.pessoaapi.controller;

import com.example.pessoaapi.dto.PeopleResponseDTO;
import com.example.pessoaapi.dto.PersonRequestDTO;
import com.example.pessoaapi.dto.PersonResponseDTO;
import com.example.pessoaapi.service.PersonService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Person API",
        description = "API for Use Case of Person",
        version = "2.0",
        contact = @Contact(
                name = "Allan Albuquerque",
                email = "allandavidcamocim@gmail.com",
                url = "https://github.com/davidalbuquerque52"
        ),
        license = @License(
                name = "MIT Licence",
                url = "https://opensource.org/licenses/mit-license.php"
        )
))
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Operation(summary = "List People", description = "return a list of people saved for Post resource before")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of people", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PeopleResponseDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "An error occured.", content = @Content) })
    @GetMapping
    public PeopleResponseDTO getAllPeople() {
        return personService.getPeople();
    }


    @Operation(summary = "Save Person", description = "Save a new person and return the person saved")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person Created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponseDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "An error occured.", content = @Content) })
    @PostMapping
    public ResponseEntity<PersonResponseDTO> savePerson(@Valid @RequestBody PersonRequestDTO personRequestDTO) {
        PersonResponseDTO personResponseDTO = personService.savePerson(personRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(personResponseDTO);
    }

}

