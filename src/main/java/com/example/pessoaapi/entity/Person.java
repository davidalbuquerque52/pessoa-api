package com.example.pessoaapi.entity;

import com.example.pessoaapi.entity.enums.IdentifierType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "identificador")
    private String identifier;

    @Column(name = "tipo_identificador")
    @Enumerated(EnumType.STRING)
    private IdentifierType identifierType;

}
