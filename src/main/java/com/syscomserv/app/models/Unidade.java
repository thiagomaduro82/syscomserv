package com.syscomserv.app.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "unidades")
public class Unidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O campo DESCRIÇÃO é obrigatório!")
    @Length(min = 3, max = 255, message = "O campo DESCRIÇÃO deve ter entre 3 a 255 caracteres!")
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "criado_em", columnDefinition = "DATETIME default CURRENT_TIMESTAMP")
    private Timestamp criadoEm;

    @Column(name = "alterado_em", columnDefinition = "DATETIME default CURRENT_TIMESTAMP")
    private Timestamp alteradoEm;

    public Unidade() {
        super();
    }

    public Unidade(Integer id, String descricao, Timestamp criadoEm, Timestamp alteradoEm) {
        this.id = id;
        this.descricao = descricao;
        this.criadoEm = criadoEm;
        this.alteradoEm = alteradoEm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Timestamp criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Timestamp getAlteradoEm() {
        return alteradoEm;
    }

    public void setAlteradoEm(Timestamp alteradoEm) {
        this.alteradoEm = alteradoEm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unidade)) return false;
        Unidade unidade = (Unidade) o;
        return getId().equals(unidade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
