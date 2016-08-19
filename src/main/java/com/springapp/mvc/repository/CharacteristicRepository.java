package com.springapp.mvc.repository;

import com.springapp.mvc.domain.CharacteristicDictionary;
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
public class CharacteristicRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<CharacteristicDictionary> getAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from CharacteristicDictionary ").list();
    }

    public void deleteCharacteristicById(Integer id){
        CharacteristicDictionary characteristicDictionary = (CharacteristicDictionary)this.sessionFactory.getCurrentSession().load(CharacteristicDictionary.class, id);
        if(characteristicDictionary != null){
            this.sessionFactory.getCurrentSession().delete(characteristicDictionary);
        }
    }

    public void addNewCharacteristic(CharacteristicDictionary characteristicDictionary){
        characteristicDictionary.setLast(0);
        this.sessionFactory.getCurrentSession().save(characteristicDictionary);
    }

    public CharacteristicDictionary getCharacteristicById(Integer id){
        return (CharacteristicDictionary)this.sessionFactory.getCurrentSession().createQuery("from CharacteristicDictionary where idCharacteristic = " + id).list().get(0);
    }

    public void update(CharacteristicDictionary characteristicDictionary){
        this.sessionFactory.getCurrentSession().update(characteristicDictionary);
    }
}
