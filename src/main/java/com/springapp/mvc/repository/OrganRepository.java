package com.springapp.mvc.repository;

import com.springapp.mvc.domain.OrganDictionary;
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
public class OrganRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<OrganDictionary> getById(int id){
        return this.sessionFactory.getCurrentSession().createQuery("from OrganDictionary where idRevisionType = "+id).list();
    }

    public OrganDictionary getOrganById(Integer id){
        return (OrganDictionary)this.sessionFactory.getCurrentSession().createQuery("from OrganDictionary where id = " + id).list().get(0);
    }

    public OrganDictionary getOrganByOrganId(Integer id){
        return (OrganDictionary)this.sessionFactory.getCurrentSession().createQuery("from OrganDictionary where idOrgan = " + id).list().get(0);
    }

    public List<OrganDictionary> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from OrganDictionary order by idOrgan").list();
    }

    public void deleteOrganById(Integer id){
        OrganDictionary organDictionary = (OrganDictionary)this.sessionFactory.getCurrentSession().load(OrganDictionary.class, id);
        if(organDictionary != null){
            this.sessionFactory.getCurrentSession().delete(organDictionary);
        }
    }



    public void setNewRecord(Integer idOrgan, Integer idRType){
        List<OrganDictionary> organDictionaries =  this.sessionFactory.getCurrentSession().createQuery("from OrganDictionary where idOrgan = " + idOrgan).list();
        for(int i = 0; i < organDictionaries.size(); i++){
            if(organDictionaries.get(i).getIdRevisionType() == idRType){
                return;
            }
        }
        OrganDictionary newOrgan = new OrganDictionary();
        newOrgan.setIdOrgan(idOrgan);
        newOrgan.setName(organDictionaries.get(0).getName());
        newOrgan.setIdRevisionType(idRType);
        this.sessionFactory.getCurrentSession().save(newOrgan);
    }

    public void addNewOrgan(OrganDictionary organDictionary){
        List<OrganDictionary> organDictionaries =  this.sessionFactory.getCurrentSession().createQuery("from OrganDictionary order by idOrgan").list();
        organDictionary.setIdOrgan(organDictionaries.get(organDictionaries.size() - 1).getIdOrgan() + 1);
        if(organDictionary.getName().length() > 49) {
            organDictionary.setName(organDictionary.getName().substring(0, 49));
        }
        this.sessionFactory.getCurrentSession().save(organDictionary);
    }

    public void update(OrganDictionary organDictionary){
        this.sessionFactory.getCurrentSession().update(organDictionary);//createSQLQuery("UPDATE organ_dictionary SET id_revision_type='"+organDictionary.getIdRevisionType()+"', name='"+organDictionary.getName()+"' WHERE id="+organDictionary.getId()).executeUpdate();
    }

}
