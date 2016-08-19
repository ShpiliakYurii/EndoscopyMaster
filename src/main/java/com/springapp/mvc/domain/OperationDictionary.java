package com.springapp.mvc.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Yurii on 23.02.2016.
 */
@Entity
@Table(name = "operation_dictionary", schema = "", catalog = "endo")
public class OperationDictionary {
    private int id;
    private String name;
    private Collection<Operations> operationsesById;

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

        OperationDictionary that = (OperationDictionary) o;

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

    @OneToMany(mappedBy = "operationDictionaryByOperationDictionaryId")
    public Collection<Operations> getOperationsesById() {
        return operationsesById;
    }

    public void setOperationsesById(Collection<Operations> operationsesById) {
        this.operationsesById = operationsesById;
    }
}
