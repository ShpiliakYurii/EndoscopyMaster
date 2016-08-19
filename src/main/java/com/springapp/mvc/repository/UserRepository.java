package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Settings;
import com.springapp.mvc.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yurii on 18.10.2015.
 */
@Repository
@Transactional
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private String USER_INFO = "select u.id, u.login, u.pass, u.pib, u.workPlace," +
            " s.apparatusId, s.disinfectantSolutionId " +
            "from User u, Settings s " +
            "where u.id = s.userId and u.id = :id";

    public List<User> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    public void removeUser(Integer id){
        User user = (User)this.sessionFactory.getCurrentSession().load(User.class, id);
        if(user != null){
            this.sessionFactory.getCurrentSession().createQuery("delete from Settings s where s.userId = " + id).executeUpdate();
            this.sessionFactory.getCurrentSession().delete(user);
        }
    }

    public Integer addUser(User user){
        this.sessionFactory.getCurrentSession().save(user);
        return user.getId();
    }

    public Object getSettings(Integer userId){
        Query query = this.sessionFactory.getCurrentSession().createQuery(USER_INFO);
        query.setParameter("id", userId);
        return query.list().get(0);
    }

    public void update(String sql){
        this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
    }

    public void addSettings(Settings settings){
        this.sessionFactory.getCurrentSession().save(settings);
    }

}
