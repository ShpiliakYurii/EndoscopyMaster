package com.springapp.mvc.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Yurii on 27.02.2016.
 */
@Entity
@Table(name = "medicinecard1", schema = "", catalog = "endo")
public class Medicinecard {
    private Integer id;
    private String pib;
    private String adress;
    private Date burnDate;
    private String identifyCode;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pib")
    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    @Basic
    @Column(name = "adress")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Basic
    @Column(name = "burnDate")
    public Date getBurnDate() {
        return burnDate;
    }

    public void setBurnDate(Date burnDate) {
        this.burnDate = burnDate;
    }

    @Basic
    @Column(name = "identifyCode")
    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medicinecard that = (Medicinecard) o;

        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;
        if (burnDate != null ? !burnDate.equals(that.burnDate) : that.burnDate != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (identifyCode != null ? !identifyCode.equals(that.identifyCode) : that.identifyCode != null) return false;
        if (pib != null ? !pib.equals(that.pib) : that.pib != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pib != null ? pib.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (burnDate != null ? burnDate.hashCode() : 0);
        result = 31 * result + (identifyCode != null ? identifyCode.hashCode() : 0);
        return result;
    }
}
