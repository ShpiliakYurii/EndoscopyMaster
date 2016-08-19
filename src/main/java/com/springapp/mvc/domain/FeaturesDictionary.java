package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by Yurii on 04.01.2016.
 */
@Entity
@Table(name = "features_dictionary", schema = "", catalog = "endo")
public class FeaturesDictionary {
    private int idFeatures;
    private String name;
    private Integer idTerm;
    private Integer last;
    private String placeholder;
    private Integer conclusionDictionaryId;

    public void setIdFeatures(Integer idFeatures) {
        this.idFeatures = idFeatures;
    }

    @Id
    @GeneratedValue
    @Column(name = "id_features")
    public int getIdFeatures() {
        return idFeatures;
    }

    public void setIdFeatures(int idFeatures) {
        this.idFeatures = idFeatures;
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
    @Column(name = "id_term")
    public Integer getIdTerm() {
        return idTerm;
    }

    public void setIdTerm(Integer idTerm) {
        this.idTerm = idTerm;
    }

    @Basic
    @Column(name = "last")
    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeaturesDictionary that = (FeaturesDictionary) o;

        if (idFeatures != that.idFeatures) return false;
        if (idTerm != null ? !idTerm.equals(that.idTerm) : that.idTerm != null) return false;
        if (last != null ? !last.equals(that.last) : that.last != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFeatures;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idTerm != null ? idTerm.hashCode() : 0);
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "placeholder")
    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Basic
    @Column(name = "conclusion_dictionary_id")
    public Integer getConclusionDictionaryId() {
        return conclusionDictionaryId;
    }

    public void setConclusionDictionaryId(Integer conclusionDictionaryId) {
        this.conclusionDictionaryId = conclusionDictionaryId;
    }
}
