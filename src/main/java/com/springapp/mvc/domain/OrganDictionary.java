package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by Yurii on 04.01.2016.
 */
@Entity
@Table(name = "organ_dictionary", schema = "", catalog = "endo")
public class OrganDictionary {
    private int idOrgan;
    private String name;
    private Integer idRevisionType;
    private int id;

    public void setIdOrgan(Integer idOrgan) {
        this.idOrgan = idOrgan;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_organ")
    public int getIdOrgan() {
        return idOrgan;
    }

    public void setIdOrgan(int idOrgan) {
        this.idOrgan = idOrgan;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "id_revision_type")
    public Integer getIdRevisionType() {
        return idRevisionType;
    }

    public void setIdRevisionType(Integer idRevisionType) {
        this.idRevisionType = idRevisionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganDictionary that = (OrganDictionary) o;

        if (idOrgan != that.idOrgan) return false;
        if (idRevisionType != null ? !idRevisionType.equals(that.idRevisionType) : that.idRevisionType != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOrgan;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idRevisionType != null ? idRevisionType.hashCode() : 0);
        return result;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
