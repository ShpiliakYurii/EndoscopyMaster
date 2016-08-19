package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by Yurii on 04.01.2016.
 */
@Entity
public class Manipulations {
    private int id;
    private String value;
    private int manipulationDictionaryId;
    private int revisionsId;
    private String place;
    private ManipulationDictionary manipulationDictionaryByManipulationDictionaryId;
    private Revisions revisionsByRevisionsId;

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
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "manipulation_dictionary_id")
    public int getManipulationDictionaryId() {
        return manipulationDictionaryId;
    }

    public void setManipulationDictionaryId(int manipulationDictionaryId) {
        this.manipulationDictionaryId = manipulationDictionaryId;
    }

    @Basic
    @Column(name = "revisions_id")
    public int getRevisionsId() {
        return revisionsId;
    }

    public void setRevisionsId(int revisionsId) {
        this.revisionsId = revisionsId;
    }

    @Basic
    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manipulations that = (Manipulations) o;

        if (id != that.id) return false;
        if (manipulationDictionaryId != that.manipulationDictionaryId) return false;
        if (revisionsId != that.revisionsId) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + manipulationDictionaryId;
        result = 31 * result + revisionsId;
        result = 31 * result + (place != null ? place.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "manipulation_dictionary_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false))
    public ManipulationDictionary getManipulationDictionaryByManipulationDictionaryId() {
        return manipulationDictionaryByManipulationDictionaryId;
    }

    public void setManipulationDictionaryByManipulationDictionaryId(ManipulationDictionary manipulationDictionaryByManipulationDictionaryId) {
        this.manipulationDictionaryByManipulationDictionaryId = manipulationDictionaryByManipulationDictionaryId;
    }

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "revisions_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false))
    public Revisions getRevisionsByRevisionsId() {
        return revisionsByRevisionsId;
    }

    public void setRevisionsByRevisionsId(Revisions revisionsByRevisionsId) {
        this.revisionsByRevisionsId = revisionsByRevisionsId;
    }
}
