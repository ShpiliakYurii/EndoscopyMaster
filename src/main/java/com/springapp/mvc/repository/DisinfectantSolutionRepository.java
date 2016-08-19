package com.springapp.mvc.repository;

import com.springapp.mvc.domain.DisinfectantSolution;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yurii on 09.01.2016.
 */
@Repository
@Transactional
public class DisinfectantSolutionRepository {
    @Autowired
    SessionFactory sessionFactory;

    public List<DisinfectantSolution> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from DisinfectantSolution ").list();
    }

    public void addDisinfectantSolution(DisinfectantSolution disinfectantSolution){
        this.sessionFactory.getCurrentSession().save(disinfectantSolution);
    }

    public DisinfectantSolution getDisinfectantSolutionById(Integer id){
        return (DisinfectantSolution)this.sessionFactory.getCurrentSession().createQuery("from DisinfectantSolution where id = "+id).list().get(0);
    }

    public void update(DisinfectantSolution disinfectantSolution){
        this.sessionFactory.getCurrentSession().update(disinfectantSolution);
    }

    public void delete(Integer id){
        DisinfectantSolution disinfectantSolution = (DisinfectantSolution)this.sessionFactory.getCurrentSession().load(DisinfectantSolution.class, id);
        if(disinfectantSolution != null){
            this.sessionFactory.getCurrentSession().delete(disinfectantSolution);
        }
    }
}
