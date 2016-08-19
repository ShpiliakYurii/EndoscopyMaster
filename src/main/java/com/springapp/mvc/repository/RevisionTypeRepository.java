package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Revisiontype;
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
public class RevisionTypeRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Revisiontype> getAll(){
        return sessionFactory.getCurrentSession().createQuery("from Revisiontype ").list();
    }

    public void addRevisionType(Revisiontype revisiontype){
        this.sessionFactory.getCurrentSession().save(revisiontype);
    }

    public void deleteRevisionTypeById(Integer id){
        Revisiontype revisiontype = (Revisiontype) this.sessionFactory.getCurrentSession().load(Revisiontype.class, id);
        if(revisiontype != null){
            this.sessionFactory.getCurrentSession().delete(revisiontype);
        }
    }

    public Revisiontype getRevisionTypeById(Integer id){
        return (Revisiontype)this.sessionFactory.getCurrentSession().createQuery("from Revisiontype where id = " + id).list().get(0);
    }

    public void update(Revisiontype revisiontype){
        this.sessionFactory.getCurrentSession().update(revisiontype);
    }

}
