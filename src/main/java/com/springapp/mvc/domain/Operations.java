package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by Yurii on 23.02.2016.
 */
@Entity
public class Operations {
    private int id;
    private String description;
    private int operationDictionaryId;
    private int revisionsId;
    private OperationDictionary operationDictionaryByOperationDictionaryId;
    private Revisions revisionsByRevisionsId;

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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operations that = (Operations) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "operation_dictionary_id")
    public int getOperationDictionaryId() {
        return operationDictionaryId;
    }

    public void setOperationDictionaryId(int operationDictionaryId) {
        this.operationDictionaryId = operationDictionaryId;
    }

    @Basic
    @Column(name = "revisions_id")
    public int getRevisionsId() {
        return revisionsId;
    }

    public void setRevisionsId(int revisionsId) {
        this.revisionsId = revisionsId;
    }

    @ManyToOne
    @JoinColumn(name = "operation_dictionary_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public OperationDictionary getOperationDictionaryByOperationDictionaryId() {
        return operationDictionaryByOperationDictionaryId;
    }

    public void setOperationDictionaryByOperationDictionaryId(OperationDictionary operationDictionaryByOperationDictionaryId) {
        this.operationDictionaryByOperationDictionaryId = operationDictionaryByOperationDictionaryId;
    }

    @ManyToOne
    @JoinColumn(name = "revisions_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Revisions getRevisionsByRevisionsId() {
        return revisionsByRevisionsId;
    }

    public void setRevisionsByRevisionsId(Revisions revisionsByRevisionsId) {
        this.revisionsByRevisionsId = revisionsByRevisionsId;
    }
}
