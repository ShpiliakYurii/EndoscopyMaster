package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by Yurii on 23.02.2016.
 */
@Entity
public class Recomendations {
    private int id;
    private int recomendationDictionaryId;
    private int conclusionDictionaryId;
    private ConclusionDictionary conclusionDictionaryByConclusionDictionaryId;
    private RecomendationDictionary recomendationDictionaryByRecomendationDictionaryId;

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
    @Column(name = "recomendation_dictionary_id")
    public int getRecomendationDictionaryId() {
        return recomendationDictionaryId;
    }

    public void setRecomendationDictionaryId(int recomendationDictionaryId) {
        this.recomendationDictionaryId = recomendationDictionaryId;
    }

    @Basic
    @Column(name = "conclusion_dictionary_id")
    public int getConclusionDictionaryId() {
        return conclusionDictionaryId;
    }

    public void setConclusionDictionaryId(int conclusionDictionaryId) {
        this.conclusionDictionaryId = conclusionDictionaryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recomendations that = (Recomendations) o;

        if (conclusionDictionaryId != that.conclusionDictionaryId) return false;
        if (id != that.id) return false;
        if (recomendationDictionaryId != that.recomendationDictionaryId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + recomendationDictionaryId;
        result = 31 * result + conclusionDictionaryId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "conclusion_dictionary_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ConclusionDictionary getConclusionDictionaryByConclusionDictionaryId() {
        return conclusionDictionaryByConclusionDictionaryId;
    }

    public void setConclusionDictionaryByConclusionDictionaryId(ConclusionDictionary conclusionDictionaryByConclusionDictionaryId) {
        this.conclusionDictionaryByConclusionDictionaryId = conclusionDictionaryByConclusionDictionaryId;
    }

    @ManyToOne
    @JoinColumn(name = "recomendation_dictionary_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public RecomendationDictionary getRecomendationDictionaryByRecomendationDictionaryId() {
        return recomendationDictionaryByRecomendationDictionaryId;
    }

    public void setRecomendationDictionaryByRecomendationDictionaryId(RecomendationDictionary recomendationDictionaryByRecomendationDictionaryId) {
        this.recomendationDictionaryByRecomendationDictionaryId = recomendationDictionaryByRecomendationDictionaryId;
    }
}
