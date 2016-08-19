package com.springapp.mvc.repository;

import com.springapp.mvc.domain.ConclusionDictionary;
import com.springapp.mvc.domain.Conclusions;
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
public class ConclusionRepository {

    @Autowired
    SessionFactory sessionFactory;

    public List<ConclusionDictionary> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from ConclusionDictionary").list();
    }

    public void addConclusion(Conclusions c){
        this.sessionFactory.getCurrentSession().save(c);
    }

    public List getConclusionForRevision(int rId){
        return this.sessionFactory.getCurrentSession().createQuery("select conclusionDictionary.name," +
                " conclusions.id" +
                "  from Conclusions conclusions," +
                " ConclusionDictionary conclusionDictionary " +
                "where conclusionDictionary.id = conclusions.conclusionDictionaryId " +
                "and conclusions.revisionsId = "+rId).list();
    }


    public void save(ConclusionDictionary conclusionDictionary){
        this.sessionFactory.getCurrentSession().save(conclusionDictionary);
    }

    public void update(ConclusionDictionary conclusionDictionary){
        this.sessionFactory.getCurrentSession().update(conclusionDictionary);
    }

    public void delete(Integer id){
        ConclusionDictionary conclusionDictionary = (ConclusionDictionary)this.sessionFactory.getCurrentSession().load(ConclusionDictionary.class, id);
        if(conclusionDictionary != null){
            this.sessionFactory.getCurrentSession().delete(conclusionDictionary);
        }
    }

    public ConclusionDictionary getConclusionById(Integer id){
        return (ConclusionDictionary)this.sessionFactory.getCurrentSession().createQuery("from ConclusionDictionary where id="+id).list().get(0);
    }

    public Integer getCountConclusions(Integer id){
        return this.sessionFactory.getCurrentSession().createSQLQuery("SELECT conclusion_dictionary_id from conclusions where conclusion_dictionary_id="+id).list().size();
    }

    public void deleteFromConclusions(Integer id){
        this.sessionFactory.getCurrentSession().createQuery("delete from Conclusions where id = "+ id).executeUpdate();
    }

}
