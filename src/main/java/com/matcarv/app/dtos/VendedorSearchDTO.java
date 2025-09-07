package com.matcarv.app.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;

/**
 * Data Transfer Object for searching Vendedor.
 * 
 * @author Weslley Matos
 */
@Getter
public class VendedorSearchDTO {

    /* ID do Vendedor */
    private final UUID id;

    /* Nome do Vendedor */
    private final String nome;

    /* Email do Vendedor */
    private final String email;

    /* CPF do Vendedor */
    private final String cpf;

    /* Comissão do Vendedor */
    private final BigDecimal comissao;

    /**
     * Construtor.
     *
     * @param id identificador único do vendedor
     * @param nome nome do vendedor
     * @param cpf CPF do vendedor
     * @param email email do vendedor
     * @param comissao comissão do vendedor
     */
    public VendedorSearchDTO(final UUID id, final String nome, final String cpf, final String email,
            final BigDecimal comissao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.comissao = comissao;
    }
}
