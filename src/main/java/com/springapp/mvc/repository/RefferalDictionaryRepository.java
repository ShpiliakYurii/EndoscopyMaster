package com.springapp.mvc.repository;

import com.springapp.mvc.domain.RefferalDictionary;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yurii on 11.01.2016.
 */
@Repository
@Transactional
public class RefferalDictionaryRepository {
    @Autowired
    SessionFactory sessionFactory;

    public List<RefferalDictionary> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from RefferalDictionary order by name").list();
    }

    public void addNewRefferal(RefferalDictionary refferalDictionary){
        this.sessionFactory.getCurrentSession().save(refferalDictionary);
    }

    public RefferalDictionary getRefferalById(Integer id){
        return (RefferalDictionary)this.sessionFactory.getCurrentSession().createQuery("from RefferalDictionary where id="+id).list().get(0);
    }

    public void delete(Integer id){
        RefferalDictionary refferalDictionary = (RefferalDictionary)this.sessionFactory.getCurrentSession().load(RefferalDictionary.class, id);
        if(refferalDictionary != null){
            this.sessionFactory.getCurrentSession().delete(refferalDictionary);
        }
    }

    public void update(RefferalDictionary refferalDictionary){
        this.sessionFactory.getCurrentSession().update(refferalDictionary);
    }

}
