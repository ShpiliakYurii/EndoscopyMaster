package com.springapp.mvc.validation;

import com.springapp.mvc.domain.CharacteristicDictionary;
import com.springapp.mvc.repository.CharacteristicRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 18.02.2016.
 */
@Component
public class CharacteristicValidator implements Validator{
    private CharacteristicRepository characteristicRepository;

    public void setCharacteristicRepository(CharacteristicRepository characteristicRepository){
        this.characteristicRepository = characteristicRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CharacteristicDictionary.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        if(((CharacteristicDictionary) model).getName().length()>49){
            ((CharacteristicDictionary) model).setName(((CharacteristicDictionary) model).getName().substring(0,49));
        }
        List<CharacteristicDictionary> characteristicDictionaries = this.characteristicRepository.getAll();
        for(int i = 0; i < characteristicDictionaries.size(); i++ ){
            if(characteristicDictionaries.get(i).getName().equals(((CharacteristicDictionary) model).getName())
                    && characteristicDictionaries.get(i).getIdFeatures() == (((CharacteristicDictionary) model).getIdFeatures())){
                errors.rejectValue("name","alreadyExist.name","Задана характеристика у вибраної ознаки уже зареєстрована.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Введіть назву характеристики.");
    }
}
