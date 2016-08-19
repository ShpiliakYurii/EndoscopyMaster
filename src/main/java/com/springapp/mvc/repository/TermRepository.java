package com.springapp.mvc.repository;

import com.springapp.mvc.domain.TermDictionary;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yurii on 12.12.2015.
 */
@Repository
@Transactional
public class TermRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<TermDictionary> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from TermDictionary ").list();
    }

    public void deleteTermById(Integer id){
        TermDictionary termDictionary = (TermDictionary)this.sessionFactory.getCurrentSession().load(TermDictionary.class, id);
        if(termDictionary != null)
            this.sessionFactory.getCurrentSession().delete(termDictionary);
    }

    public void addNewTerm(TermDictionary termDictionary){
        if(termDictionary.getName().length() > 49){
            termDictionary.setName(termDictionary.getName().substring(0,49));
        }
        this.sessionFactory.getCurrentSession().save(termDictionary);
    }

    public TermDictionary getTermById(Integer id){
        return (TermDictionary)this.sessionFactory.getCurrentSession().createQuery("from TermDictionary where idTerm = " + id).list().get(0);
    }

    public void update(TermDictionary termDictionary){
        this.sessionFactory.getCurrentSession().update(termDictionary);
    }

}
