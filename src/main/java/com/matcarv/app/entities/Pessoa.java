package com.matcarv.app.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Entidade base para Cliente e Vendedor
 */
@Data
@Entity
@Table(name = "PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(of = {"id"})
public class Pessoa {

    /** Identificador único da pessoa */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    /** Nome completo da pessoa */
    @Column(name = "NOME", nullable = false)
    private String nome;

    /** E-mail da pessoa */
    @Column(name = "EMAIL", nullable = false)
    private String email;

    /** Telefone de contato da pessoa */
    @Column(name = "TELEFONE", nullable = false)
    private String telefone;

    /** Endereço completo da pessoa */
    @Embedded
    private Endereco endereco;

    /** CPF da pessoa */
    @Column(name = "CPF", nullable = false)
    private String cpf;

    /** Data de inclusão da pessoa */
    @Column(name = "DATA_INCLUSAO", nullable = false)
    private LocalDate dataInclusao;
}
