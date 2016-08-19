package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by Yurii on 04.01.2016.
 */
@Entity
@Table(name = "region_dictionary", schema = "", catalog = "endo")
public class RegionDictionary {
    private int idRegion;
    private String name;
    private Integer idOrgan;

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    @Id
    @GeneratedValue
    @Column(name = "id_region")
    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
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
    @Column(name = "id_organ")
    public Integer getIdOrgan() {
        return idOrgan;
    }

    public void setIdOrgan(Integer idOrgan) {
        this.idOrgan = idOrgan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionDictionary that = (RegionDictionary) o;

        if (idRegion != that.idRegion) return false;
        if (idOrgan != null ? !idOrgan.equals(that.idOrgan) : that.idOrgan != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRegion;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idOrgan != null ? idOrgan.hashCode() : 0);
        return result;
    }
}
