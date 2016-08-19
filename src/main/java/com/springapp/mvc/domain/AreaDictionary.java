package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by Yurii on 04.01.2016.
 */
@Entity
@Table(name = "area_dictionary", schema = "", catalog = "endo")
public class AreaDictionary {
    private int idArea;
    private String name;
    private Integer idCharacteristic;

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    @Id
    @Column(name = "id_area")
    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
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
    @Column(name = "id_characteristic")
    public Integer getIdCharacteristic() {
        return idCharacteristic;
    }

    public void setIdCharacteristic(Integer idCharacteristic) {
        this.idCharacteristic = idCharacteristic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AreaDictionary that = (AreaDictionary) o;

        if (idArea != that.idArea) return false;
        if (idCharacteristic != null ? !idCharacteristic.equals(that.idCharacteristic) : that.idCharacteristic != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idArea;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idCharacteristic != null ? idCharacteristic.hashCode() : 0);
        return result;
    }
}
