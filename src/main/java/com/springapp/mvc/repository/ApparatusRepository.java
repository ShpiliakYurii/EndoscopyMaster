package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Apparatus;
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
public class ApparatusRepository {

    @Autowired
    SessionFactory sessionFactory;

    public List<Apparatus> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from Apparatus order by num").list();
    }

    public void addApparatus(Apparatus apparatus){
        apparatus.setNum(0);
        this.sessionFactory.getCurrentSession().save(apparatus);
    }

    public void deleteApparatus(Integer id){
        Apparatus apparatus = (Apparatus)this.sessionFactory.getCurrentSession().load(Apparatus.class, id);
        if(apparatus != null){
            this.sessionFactory.getCurrentSession().delete(apparatus);
        }
    }

    public Apparatus getApparatusById(Integer id){
        return (Apparatus)this.sessionFactory.getCurrentSession().createQuery("from Apparatus where id = "+ id).list().get(0);
    }

    public void update(Apparatus apparatus){
        this.sessionFactory.getCurrentSession().update(apparatus);
    }


}
