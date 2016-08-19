package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by Yurii on 04.01.2016.
 */
@Entity
@Table(name = "term_dictionary", schema = "", catalog = "endo")
public class TermDictionary {
    private int idTerm;
    private String name;
    private Integer idRegion;
    private Integer last;
    private Integer conclusionId;
    private String placeholder;


    public void setIdTerm(Integer idTerm) {
        this.idTerm = idTerm;
    }

    @Id
    @GeneratedValue
    @Column(name = "id_term")
    public int getIdTerm() {
        return idTerm;
    }

    public void setIdTerm(int idTerm) {
        this.idTerm = idTerm;
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
    @Column(name = "id_region")
    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    @Basic
    @Column(name = "last")
    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    @Basic
    @Column(name = "conclusion_id")
    public Integer getConclusionId() {
        return conclusionId;
    }

    public void setConclusionId(Integer conclusionId) {
        this.conclusionId = conclusionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TermDictionary that = (TermDictionary) o;

        if (idTerm != that.idTerm) return false;
        if (conclusionId != null ? !conclusionId.equals(that.conclusionId) : that.conclusionId != null) return false;
        if (idRegion != null ? !idRegion.equals(that.idRegion) : that.idRegion != null) return false;
        if (last != null ? !last.equals(that.last) : that.last != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTerm;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idRegion != null ? idRegion.hashCode() : 0);
        result = 31 * result + (last != null ? last.hashCode() : 0);
        result = 31 * result + (conclusionId != null ? conclusionId.hashCode() : 0);
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
}
