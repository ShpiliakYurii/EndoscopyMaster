package com.springapp.mvc.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Yurii on 04.01.2016.
 */
@Entity
public class Revisiontype {
    private int id;
    private String revisionName;
    private Collection<Revisions> revisionsesById;
    private String abr;

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
    @Column(name = "revisionName")
    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Revisiontype that = (Revisiontype) o;

        if (id != that.id) return false;
        if (revisionName != null ? !revisionName.equals(that.revisionName) : that.revisionName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (revisionName != null ? revisionName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "revisiontypeByRevisiontypeId")
    public Collection<Revisions> getRevisionsesById() {
        return revisionsesById;
    }

    public void setRevisionsesById(Collection<Revisions> revisionsesById) {
        this.revisionsesById = revisionsesById;
    }

    @Basic
    @Column(name = "abr")
    public String getAbr() {
        return abr;
    }

    public void setAbr(String abr) {
        this.abr = abr;
    }
}
