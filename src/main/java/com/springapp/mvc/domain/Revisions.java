package com.springapp.mvc.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Yurii on 27.02.2016.
 */
@Entity
public class Revisions {
    private Integer id;
    private Timestamp date;
    private String description;
    private Integer doctorId;
    private Integer revisiontypeId;
    private Integer apparatusId;
    private Integer disinfectantSolutionId;
    private Integer refferalDictionaryId;
    private Integer medicinecardId;
    private Collection<Manipulations> manipulationsesById;
    private Apparatus apparatusByApparatusId;
    private DisinfectantSolution disinfectantSolutionByDisinfectantSolutionId;
    private Medicinecard medicinecardByMedicinecardId;
    private Revisiontype revisiontypeByRevisiontypeId;
    private User userByDoctorId;
    private Collection<Conclusions> conclusionsesById;
    private RefferalDictionary refferalDictionaryByRefferalDictionaryId;
    private Integer firstPatalogy;

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
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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

        Revisions revisions = (Revisions) o;

        if (date != null ? !date.equals(revisions.date) : revisions.date != null) return false;
        if (description != null ? !description.equals(revisions.description) : revisions.description != null)
            return false;
        if (id != null ? !id.equals(revisions.id) : revisions.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "doctor_id")
    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "revisiontype_id")
    public Integer getRevisiontypeId() {
        return revisiontypeId;
    }

    public void setRevisiontypeId(Integer revisiontypeId) {
        this.revisiontypeId = revisiontypeId;
    }

    @Basic
    @Column(name = "apparatus_id")
    public Integer getApparatusId() {
        return apparatusId;
    }

    public void setApparatusId(Integer apparatusId) {
        this.apparatusId = apparatusId;
    }

    @Basic
    @Column(name = "disinfectant_solution_id")
    public Integer getDisinfectantSolutionId() {
        return disinfectantSolutionId;
    }

    public void setDisinfectantSolutionId(Integer disinfectantSolutionId) {
        this.disinfectantSolutionId = disinfectantSolutionId;
    }

    @Basic
    @Column(name = "refferal_dictionary_id")
    public Integer getRefferalDictionaryId() {
        return refferalDictionaryId;
    }

    public void setRefferalDictionaryId(Integer refferalDictionaryId) {
        this.refferalDictionaryId = refferalDictionaryId;
    }

    @Basic
    @Column(name = "medicinecard_id")
    public Integer getMedicinecardId() {
        return medicinecardId;
    }

    public void setMedicinecardId(Integer medicinecardId) {
        this.medicinecardId = medicinecardId;
    }

    @OneToMany(mappedBy = "revisionsByRevisionsId")
    public Collection<Manipulations> getManipulationsesById() {
        return manipulationsesById;
    }

    public void setManipulationsesById(Collection<Manipulations> manipulationsesById) {
        this.manipulationsesById = manipulationsesById;
    }

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "apparatus_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false))
    public Apparatus getApparatusByApparatusId() {
        return apparatusByApparatusId;
    }

    public void setApparatusByApparatusId(Apparatus apparatusByApparatusId) {
        this.apparatusByApparatusId = apparatusByApparatusId;
    }

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "disinfectant_solution_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false))
    public DisinfectantSolution getDisinfectantSolutionByDisinfectantSolutionId() {
        return disinfectantSolutionByDisinfectantSolutionId;
    }

    public void setDisinfectantSolutionByDisinfectantSolutionId(DisinfectantSolution disinfectantSolutionByDisinfectantSolutionId) {
        this.disinfectantSolutionByDisinfectantSolutionId = disinfectantSolutionByDisinfectantSolutionId;
    }

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "medicinecard_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false))
    public Medicinecard getMedicinecardByMedicinecardId() {
        return medicinecardByMedicinecardId;
    }

    public void setMedicinecardByMedicinecardId(Medicinecard medicinecardByMedicinecardId) {
        this.medicinecardByMedicinecardId = medicinecardByMedicinecardId;
    }

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "revisiontype_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false))
    public Revisiontype getRevisiontypeByRevisiontypeId() {
        return revisiontypeByRevisiontypeId;
    }

    public void setRevisiontypeByRevisiontypeId(Revisiontype revisiontypeByRevisiontypeId) {
        this.revisiontypeByRevisiontypeId = revisiontypeByRevisiontypeId;
    }

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false))
    public User getUserByDoctorId() {
        return userByDoctorId;
    }

    public void setUserByDoctorId(User userByDoctorId) {
        this.userByDoctorId = userByDoctorId;
    }

    @OneToMany(mappedBy = "revisionsByRevisionsId")
    public Collection<Conclusions> getConclusionsesById() {
        return conclusionsesById;
    }

    public void setConclusionsesById(Collection<Conclusions> conclusionsesById) {
        this.conclusionsesById = conclusionsesById;
    }

    @ManyToOne
    @JoinColumn(name = "refferal_dictionary_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public RefferalDictionary getRefferalDictionaryByRefferalDictionaryId() {
        return refferalDictionaryByRefferalDictionaryId;
    }

    public void setRefferalDictionaryByRefferalDictionaryId(RefferalDictionary refferalDictionaryByRefferalDictionaryId) {
        this.refferalDictionaryByRefferalDictionaryId = refferalDictionaryByRefferalDictionaryId;
    }

    @Basic
    @Column(name = "first_patalogy")
    public Integer getFirstPatalogy() {
        return firstPatalogy;
    }

    public void setFirstPatalogy(Integer firstPatalogy) {
        this.firstPatalogy = firstPatalogy;
    }
}
