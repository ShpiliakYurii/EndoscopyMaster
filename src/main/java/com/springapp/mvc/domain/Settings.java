package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by Yurii on 04.04.2016.
 */
@Entity
public class Settings {
    private Integer id;
    private Integer userId;
    private Integer apparatusId;
    private Integer disinfectantSolutionId;

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
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Settings settings = (Settings) o;

        if (apparatusId != null ? !apparatusId.equals(settings.apparatusId) : settings.apparatusId != null)
            return false;
        if (disinfectantSolutionId != null ? !disinfectantSolutionId.equals(settings.disinfectantSolutionId) : settings.disinfectantSolutionId != null)
            return false;
        if (id != null ? !id.equals(settings.id) : settings.id != null) return false;
        if (userId != null ? !userId.equals(settings.userId) : settings.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (apparatusId != null ? apparatusId.hashCode() : 0);
        result = 31 * result + (disinfectantSolutionId != null ? disinfectantSolutionId.hashCode() : 0);
        return result;
    }
}
