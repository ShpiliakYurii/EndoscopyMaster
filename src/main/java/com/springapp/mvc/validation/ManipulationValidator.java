package com.springapp.mvc.validation;

import com.springapp.mvc.domain.ManipulationDictionary;
import com.springapp.mvc.repository.ManipulationRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 23.02.2016.
 */
@Component
public class ManipulationValidator implements Validator{
    private ManipulationRepository manipulationRepository;

    public void setManipulationRepository(ManipulationRepository manipulationRepository){
        this.manipulationRepository = manipulationRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ManipulationDictionary.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<ManipulationDictionary> manipulationDictionaries = this.manipulationRepository.getDictionary();
        for(int i = 0; i < manipulationDictionaries.size(); i++ ){
            if(manipulationDictionaries.get(i).getName().equals(((ManipulationDictionary) model).getName())){
                errors.rejectValue("name","alreadyExist.name","Введена маніпуляція уже зареєстрована.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Введіть назву маніпуляції.");
    }
}
