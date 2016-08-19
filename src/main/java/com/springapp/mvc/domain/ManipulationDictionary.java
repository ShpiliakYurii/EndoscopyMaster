package com.springapp.mvc.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Yurii on 04.01.2016.
 */
@Entity
@Table(name = "manipulation_dictionary", schema = "", catalog = "endo")
public class ManipulationDictionary {
    private int id;
    private String name;
    private Collection<Manipulations> manipulationsesById;

    public void setId(Integer id) {
        this.id = id;
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

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManipulationDictionary that = (ManipulationDictionary) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "manipulationDictionaryByManipulationDictionaryId")
    public Collection<Manipulations> getManipulationsesById() {
        return manipulationsesById;
    }

    public void setManipulationsesById(Collection<Manipulations> manipulationsesById) {
        this.manipulationsesById = manipulationsesById;
    }
}
