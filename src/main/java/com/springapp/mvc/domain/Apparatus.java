package com.springapp.mvc.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Yurii on 04.01.2016.
 */
@Entity
public class Apparatus {
    private int id;
    private String name;
    private Collection<Revisions> revisionsesById;
    private int num;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNum(Integer num) {
        this.num = num;
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

        Apparatus apparatus = (Apparatus) o;

        if (id != apparatus.id) return false;
        if (name != null ? !name.equals(apparatus.name) : apparatus.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "apparatusByApparatusId")
    public Collection<Revisions> getRevisionsesById() {
        return revisionsesById;
    }

    public void setRevisionsesById(Collection<Revisions> revisionsesById) {
        this.revisionsesById = revisionsesById;
    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
