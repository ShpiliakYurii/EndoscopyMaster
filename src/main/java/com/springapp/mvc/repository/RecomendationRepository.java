package com.springapp.mvc.repository;

import com.springapp.mvc.domain.RecomendationDictionary;
import com.springapp.mvc.domain.Recomendations;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yurii on 18.02.2016.
 */
@Repository
@Transactional
public class RecomendationRepository {
    @Autowired
    SessionFactory sessionFactory;

    public List<RecomendationDictionary> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from RecomendationDictionary ").list();
    }

    public RecomendationDictionary getRecomendationById(Integer id){
        return (RecomendationDictionary)this.sessionFactory.getCurrentSession().createQuery("from RecomendationDictionary where id="+id).list().get(0);
    }

    public void update(RecomendationDictionary recomendationDictionary){
        this.sessionFactory.getCurrentSession().update(recomendationDictionary);
    }

    public void delete(Integer id){
        RecomendationDictionary recomendationDictionary = (RecomendationDictionary)this.sessionFactory.getCurrentSession().load(RecomendationDictionary.class, id);
        if(recomendationDictionary != null){
            this.sessionFactory.getCurrentSession().delete(recomendationDictionary);
        }
    }

    public List<Recomendations> getAllRecomendations(){
        return this.sessionFactory.getCurrentSession().createQuery("from Recomendations ").list();
    }

    public void setNewRecord(Recomendations recomendations){
        List list = this.sessionFactory.getCurrentSession().createQuery("from Recomendations ").list();
        for(int i = 0; i < list.size(); i++){
            if(((Recomendations)list.get(i)).getConclusionDictionaryId() == recomendations.getConclusionDictionaryId() && ((Recomendations)list.get(i)).getRecomendationDictionaryId() == recomendations.getRecomendationDictionaryId()){
                return;
            }
        }
        this.sessionFactory.getCurrentSession().save(recomendations);
    }

    public void addNewRecomendation(RecomendationDictionary recomendationDictionary){
        this.sessionFactory.getCurrentSession().save(recomendationDictionary);
    }

    public Integer getCountRecomendation(Integer id){
        return this.sessionFactory.getCurrentSession().createQuery("from Recomendations where recomendationDictionaryId = " + id).list().size();
    }

    public List<Recomendations> getRecomendations(Integer id){
        return this.sessionFactory.getCurrentSession().createQuery("from Recomendations where recomendationDictionaryId = " + id).list();
    }

    public void deleteFromRecomendations(Integer id){
        Recomendations recomendations = (Recomendations)this.sessionFactory.getCurrentSession().load(Recomendations.class, id);
        if(recomendations != null){
            this.sessionFactory.getCurrentSession().delete(recomendations);
        }
    }

    public List getRecomendationsForRevision(Integer id){
        return this.sessionFactory.getCurrentSession().createQuery("SELECT " +
                "distinct rd.name " +
                "from RecomendationDictionary rd, Recomendations r, ConclusionDictionary  cd, Conclusions c" +
                " where rd.id = r.recomendationDictionaryId " +
                "and r.conclusionDictionaryId = cd.id " +
                "and cd.id = c.conclusionDictionaryId " +
                "and c.revisionsId = " + id).list();
    }

}
