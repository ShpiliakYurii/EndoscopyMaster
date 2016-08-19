package com.springapp.mvc.repository;

import com.springapp.mvc.domain.FeaturesDictionary;
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
public class FeaturesRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<FeaturesDictionary> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from FeaturesDictionary").list();
    }

    public void deleteFeatureById(Integer id){
        FeaturesDictionary featuresDictionary = (FeaturesDictionary)this.sessionFactory.getCurrentSession().load(FeaturesDictionary.class, id);
        if(featuresDictionary != null){
            this.sessionFactory.getCurrentSession().delete(featuresDictionary);
        }
    }

    public void addNewFeature(FeaturesDictionary featuresDictionary){
        if(featuresDictionary.getName().length() > 49){
            featuresDictionary.setName(featuresDictionary.getName().substring(0,49));
        }
        this.sessionFactory.getCurrentSession().save(featuresDictionary);
    }

    public FeaturesDictionary getFeatureById(Integer id){
        return (FeaturesDictionary)this.sessionFactory.getCurrentSession().createQuery("from FeaturesDictionary where idFeatures = " + id).list().get(0);
    }

    public void update(FeaturesDictionary featuresDictionary){
        this.sessionFactory.getCurrentSession().update(featuresDictionary);
    }
}
