package com.springapp.mvc.repository;

import com.springapp.mvc.domain.RegionDictionary;
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
public class RegionRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<RegionDictionary> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from RegionDictionary ").list();
    }

    public void deleteRegionById(Integer id){
        RegionDictionary regionDictionary = (RegionDictionary)this.sessionFactory.getCurrentSession().load(RegionDictionary.class, id);
        if(regionDictionary != null){
            this.sessionFactory.getCurrentSession().delete(regionDictionary);
        }
    }

    public void addNewRegion(RegionDictionary regionDictionary){
        if(regionDictionary.getName().length() > 49){
            regionDictionary.setName(regionDictionary.getName().substring(0,49));
        }
        this.sessionFactory.getCurrentSession().save(regionDictionary);
    }

    public RegionDictionary getRegionById(Integer id){
        return (RegionDictionary)this.sessionFactory.getCurrentSession().createQuery("from RegionDictionary where idRegion = "+ id).list().get(0);
    }

    public void update(RegionDictionary regionDictionary){
        this.sessionFactory.getCurrentSession().update(regionDictionary);
    }

}
