package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by Yurii on 04.01.2016.
 */
@Entity
public class Conclusions {
    private int id;
    private int conclusionDictionaryId;
    private int revisionsId;
    private ConclusionDictionary conclusionDictionaryByConclusionDictionaryId;
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
    @Column(name = "conclusion_dictionary_id")
    public int getConclusionDictionaryId() {
        return conclusionDictionaryId;
    }

    public void setConclusionDictionaryId(int conclusionDictionaryId) {
        this.conclusionDictionaryId = conclusionDictionaryId;
    }

    @Basic
    @Column(name = "revisions_id")
    public int getRevisionsId() {
        return revisionsId;
    }

    public void setRevisionsId(int revisionsId) {
        this.revisionsId = revisionsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conclusions that = (Conclusions) o;

        if (conclusionDictionaryId != that.conclusionDictionaryId) return false;
        if (id != that.id) return false;
        if (revisionsId != that.revisionsId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + conclusionDictionaryId;
        result = 31 * result + revisionsId;
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "conclusion_dictionary_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)})
    public ConclusionDictionary getConclusionDictionaryByConclusionDictionaryId() {
        return conclusionDictionaryByConclusionDictionaryId;
    }

    public void setConclusionDictionaryByConclusionDictionaryId(ConclusionDictionary conclusionDictionaryByConclusionDictionaryId) {
        this.conclusionDictionaryByConclusionDictionaryId = conclusionDictionaryByConclusionDictionaryId;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "revisions_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)})
    public Revisions getRevisionsByRevisionsId() {
        return revisionsByRevisionsId;
    }

    public void setRevisionsByRevisionsId(Revisions revisionsByRevisionsId) {
        this.revisionsByRevisionsId = revisionsByRevisionsId;
    }
}
