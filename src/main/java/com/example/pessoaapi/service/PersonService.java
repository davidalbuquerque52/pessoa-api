package com.example.pessoaapi.service;

import com.example.pessoaapi.dto.PeopleResponseDTO;
import com.example.pessoaapi.dto.PersonRequestDTO;
import com.example.pessoaapi.dto.PersonResponseDTO;
import com.example.pessoaapi.entity.enums.IdentifierType;
import com.example.pessoaapi.entity.Person;
import com.example.pessoaapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public PeopleResponseDTO getPeople() {
        List<Person> people = personRepository.findAll();
        List<PersonResponseDTO> personResponseDTOList = new ArrayList<>();
        if(!people.isEmpty()){

            people.forEach(person -> {
                PersonResponseDTO personResponseDTO = initializePersonResponseByPerson(person);
                personResponseDTOList.add(personResponseDTO);
            });
        }
        PeopleResponseDTO peopleResponseDTO = new PeopleResponseDTO();
        peopleResponseDTO.setPeopleResponseDTO(personResponseDTOList);
        return peopleResponseDTO;
    }

    public PersonResponseDTO savePerson(PersonRequestDTO personRequest) {
        Person person = Person.builder()
                .name(personRequest.getName())
                .identifier(personRequest.getIdentifier())
                .identifierType(getIdentifierTypeByIdentifier(personRequest.getIdentifier()))
                .build();
        Person personSaved = personRepository.save(person);
        PersonResponseDTO personResponseDTO = initializePersonResponseByPerson(personSaved);
        return  personResponseDTO;
    }

    private PersonResponseDTO initializePersonResponseByPerson(Person person) {
        PersonResponseDTO personResponseDTO = PersonResponseDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .identifier(person.getIdentifier())
                .identifierType(person.getIdentifierType().toString())
                .build();
        return personResponseDTO;
    }

    private IdentifierType getIdentifierTypeByIdentifier(String identifier) {
        IdentifierType identifierType = identifier
                .replace(".", "")
                .replace("-", "")
                .replace("/", "")
                .replace(" ", "")
                .length() < 14 ? IdentifierType.CPF : IdentifierType.CNPJ;
        return identifierType;
    }
}
