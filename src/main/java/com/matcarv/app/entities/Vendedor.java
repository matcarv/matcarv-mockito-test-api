package com.matcarv.app.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "VENDEDOR")
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode(callSuper = true)
public class Vendedor extends Pessoa {

    /** Comissão do vendedor */
    @Column(name = "COMISSAO", scale = 2, nullable = false)
    private BigDecimal comissao;
}
