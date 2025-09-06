package com.matcarv.app.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade Cliente.
 * Representa um cliente do sistema.
 *
 * @author Weslley Matos
 */
@Data
@Entity
@Table(name = "CLIENTE")
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Pessoa {

    /** Observações adicionais sobre a pessoa */
    @Column(name = "OBSERVACAO", length = 4000)
    private String observacao;
}
