package com.matcarv.app.dtos;

import java.util.UUID;

import lombok.Getter;

/**
 * Data Transfer Object for searching Cliente.
 * 
 * @author Weslley Matos
 */
@Getter
public class ClienteSearchDTO {

    /* Identificador único do cliente */
    private final UUID id;

    /* Nome do cliente */
    private final String nome;

    /* CPF do cliente */
    private final String cpf;

    /* Email do cliente */
    private final String email;

    /**
     * Construtor.
     *
     * @param id identificador único do cliente
     * @param nome nome do cliente
     * @param cpf CPF do cliente
     * @param email email do cliente
     */
    public ClienteSearchDTO(final UUID id, final String nome, final String cpf, final String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    
}
