package com.springapp.mvc.repository;

import com.springapp.mvc.domain.ManipulationDictionary;
import com.springapp.mvc.domain.Manipulations;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yurii on 04.01.2016.
 */
@Repository
@Transactional
public class ManipulationRepository {

    @Autowired
    SessionFactory sessionFactory;

    private String MainpulationWithoutValue = "select manipulations.id, manipulations.value," +
            " manipulation_dictionary.name, manipulations.revisions_id, manipulations.place " +
            "from manipulations, manipulation_dictionary, revisions where (manipulations.value is null or " +
            "manipulations.value = \"\") and revisions.id = manipulations.revisions_id and revisions.doctor_id = :doctorId " +
            "and manipulations.manipulation_dictionary_id = manipulation_dictionary.id " +
            "order by manipulations.id";

    public List<ManipulationDictionary> getDictionary(){
        return this.sessionFactory.getCurrentSession().createQuery("from ManipulationDictionary").list();
    }

    public List<Manipulations> getFullManipulations(Integer doctorId) {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select manipulations.id, manipulations.value, " +
                "manipulation_dictionary.name, manipulations.revisions_id, manipulations.place " +
                "from manipulations, manipulation_dictionary, revisions WHERE " +
                "manipulations.manipulation_dictionary_id = manipulation_dictionary.id and revisions.id = manipulations.revisions_id and revisions.doctor_id = :doctorId " +
                "order by manipulations.id");
        query.setParameter("doctorId", doctorId);
        return query.list();
    }

    public List getManipulationsWithoutValue(Integer doctorId){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(MainpulationWithoutValue);
        query.setParameter("doctorId", doctorId);
        return query.list();
    }

    public void addManipulation(Manipulations m){
        this.sessionFactory.getCurrentSession().save(m);
    }

    public List<Manipulations> getManipulationsForRevision(int rId){
        return this.sessionFactory.getCurrentSession().createQuery("select manipulationDictionary.name, manipulations.place," +
                " manipulations.value, manipulations.id from Manipulations manipulations, ManipulationDictionary manipulationDictionary " +
                "where manipulations.manipulationDictionaryId = manipulationDictionary.id and manipulations.revisionsId = "+rId).list();
    }

    public void save(ManipulationDictionary manipulationDictionary){
        this.sessionFactory.getCurrentSession().save(manipulationDictionary);
    }

    public void delete(Integer id){
        ManipulationDictionary manipulationDictionary = (ManipulationDictionary)this.sessionFactory.getCurrentSession().load(ManipulationDictionary.class, id);
        if(manipulationDictionary != null){
            this.sessionFactory.getCurrentSession().delete(manipulationDictionary);
        }
    }

    public void update(ManipulationDictionary manipulationDictionary){
        this.sessionFactory.getCurrentSession().update(manipulationDictionary);
    }

    public Integer getCountManipulation(Integer id){
        return this.sessionFactory.getCurrentSession().createQuery("from Manipulations where manipulationDictionaryId = " + id).list().size();
    }

    public ManipulationDictionary getManipulationById(Integer id){
        return (ManipulationDictionary)this.sessionFactory.getCurrentSession().createQuery("from ManipulationDictionary where id = "+id).list().get(0);
    }

    public void update(Integer id, String mDescription, String value1){
        Query query = this.sessionFactory.getCurrentSession().
                createQuery("UPDATE Manipulations SET place = :mDescription, value = :value1 " +
                        "where id = :id");
        query.setParameter("mDescription", mDescription);
        query.setParameter("value1", value1);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void deleteFromManipulations(Integer id){
        this.sessionFactory.getCurrentSession().createQuery("DELETE from Manipulations where id = " + id).executeUpdate();
    }
}
