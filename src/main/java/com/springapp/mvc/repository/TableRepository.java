package com.springapp.mvc.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yurii on 28.02.2016.
 */
@Repository
@Transactional
public class TableRepository {
    @Autowired
    SessionFactory sessionFactory;

    public List makeSql(String sql){
        return this.sessionFactory.getCurrentSession().createSQLQuery(sql).list();
    }

    public void makeUpdate(String sql){
        this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
    }

    public void makeInsert(String sql){
        this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
    }

    public void makeDelete(String sql){
        this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
    }

}
