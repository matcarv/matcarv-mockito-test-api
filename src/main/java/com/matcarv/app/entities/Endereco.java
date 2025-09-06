package com.matcarv.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Endereço de uma pessoa (entidade embutida).
 *
 * @author Weslley Matos
 */
@Data
@Embeddable
public class Endereco {

    /** Logradouro */
    @Column(name = "ENDERECO", nullable = false)
    private String logradouro;

    /** Número do endereço */
    @Column(name = "NUMERO", nullable = false)
    private String numero;

    /** Complemento do endereço */
    @Column(name = "COMPLEMENTO", nullable = false)
    private String complemento;

    /** Bairro */
    @Column(name = "BAIRRO", nullable = false)
    private String bairro;

    /** Cidade */
    @Column(name = "CIDADE", nullable = false)
    private String cidade;

    /** Estado */
    @Column(name = "ESTADO", nullable = false)
    private String estado;

    /** CEP */
    @Column(name = "CEP", nullable = false)
    private String cep;
}
