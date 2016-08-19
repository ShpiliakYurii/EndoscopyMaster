package com.springapp.mvc.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Yurii on 11.01.2016.
 */
@Entity
@Table(name = "refferal_dictionary", schema = "", catalog = "endo")
public class RefferalDictionary {
    private int id;
    private String name;
    private Collection<Revisions> revisionsesById;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
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

        RefferalDictionary that = (RefferalDictionary) o;

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

    @OneToMany(mappedBy = "refferalDictionaryByRefferalDictionaryId")
    public Collection<Revisions> getRevisionsesById() {
        return revisionsesById;
    }

    public void setRevisionsesById(Collection<Revisions> revisionsesById) {
        this.revisionsesById = revisionsesById;
    }
}
