package com.springapp.mvc.repository;

import com.springapp.mvc.domain.OperationDictionary;
import com.springapp.mvc.domain.Operations;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yurii on 23.02.2016.
 */
@Repository
@Transactional
public class OperationRepository {
    @Autowired
    SessionFactory sessionFactory;

    public List<OperationDictionary> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from OperationDictionary ").list();
    }

    public OperationDictionary getOperationById(Integer id){
        return (OperationDictionary)this.sessionFactory.getCurrentSession().createQuery("from OperationDictionary where id ="+id).list().get(0);
    }

    public void save(OperationDictionary operationDictionary){
        this.sessionFactory.getCurrentSession().save(operationDictionary);
    }

    public void delete(Integer id){
        OperationDictionary operationDictionary = (OperationDictionary)this.sessionFactory.getCurrentSession().load(OperationDictionary.class, id);
        if(operationDictionary != null){
            this.sessionFactory.getCurrentSession().delete(operationDictionary);
        }
    }

    public void update(OperationDictionary operationDictionary){
        this.sessionFactory.getCurrentSession().update(operationDictionary);
    }

    public Integer getCountOperationById(Integer id){
        return this.sessionFactory.getCurrentSession().createQuery("from Operations where operationDictionaryId = " + id).list().size();
    }

    public void addOperation(Operations operations){
        this.sessionFactory.getCurrentSession().save(operations);
    }


    public List getOperationsForRevision(Integer rId){
        return this.sessionFactory.getCurrentSession().createQuery("select " +
                "  operationDictionary.name, operations.id, operations.description from Operations operations," +
                " OperationDictionary operationDictionary" +
                " WHERE operationDictionary.id = operations.operationDictionaryId and" +
                " operations.revisionsId = "+ rId).list();
    }

    public void deleteFromOperations(Integer id){
        this.sessionFactory.getCurrentSession().createQuery("delete from Operations where id = " + id).executeUpdate();
    }

    public void update(Integer id, String description){
        Query query = this.sessionFactory.getCurrentSession().createQuery("UPDATE Operations " +
                "set description = :description where id = :id");
        query.setParameter("description", description);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
