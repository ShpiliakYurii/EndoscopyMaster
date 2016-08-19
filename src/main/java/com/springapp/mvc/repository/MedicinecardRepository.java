package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Medicinecard;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yurii on 20.12.2015.
 */
@Repository
@Transactional
public class MedicinecardRepository {
    @Autowired
    SessionFactory sessionFactory;

    public List<Medicinecard> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from Medicinecard m order by m.pib").list();
    }

    public void addMedicinecard(Medicinecard medicinecard){
        this.sessionFactory.getCurrentSession().save(medicinecard);
    }

    public String getMedicinecardById(Integer id) {
        return ((Medicinecard)this.sessionFactory.getCurrentSession().load(Medicinecard.class, id)).getPib();
    }

    public Medicinecard getMedicinecardById1(Integer id){
        return (Medicinecard) this.sessionFactory.getCurrentSession().createQuery("from Medicinecard where id = " + id).list().get(0);
    }

    public void update(Medicinecard medicinecard){
        this.sessionFactory.getCurrentSession().update(medicinecard);
    }

    public void delete(Integer id){
        Medicinecard medicinecard = (Medicinecard)this.sessionFactory.getCurrentSession().load(Medicinecard.class, id);
        if(medicinecard != null){
            this.sessionFactory.getCurrentSession().delete(medicinecard);
        }
    }

}
