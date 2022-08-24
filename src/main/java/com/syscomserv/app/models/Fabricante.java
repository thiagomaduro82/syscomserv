package com.syscomserv.app.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "fabricantes")
public class Fabricante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "criado_em")
    private Timestamp criadoEm;

    @Column(name = "alterado_em")
    private Timestamp alteradoEm;

    public Fabricante() {
        super();
    }

    public Fabricante(Integer id, String descricao, Timestamp criadoEm, Timestamp alteradoEm) {
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
        if (!(o instanceof Fabricante)) return false;
        Fabricante that = (Fabricante) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
