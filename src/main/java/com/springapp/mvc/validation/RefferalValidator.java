package com.springapp.mvc.validation;

import com.springapp.mvc.domain.RefferalDictionary;
import com.springapp.mvc.repository.RefferalDictionaryRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 14.02.2016.
 */
@Component
public class RefferalValidator implements Validator{
    private RefferalDictionaryRepository refferalDictionaryRepository;

    public void setRefferalDictionaryRepository(RefferalDictionaryRepository refferalDictionaryRepository){
        this.refferalDictionaryRepository = refferalDictionaryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RefferalDictionary.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<RefferalDictionary> refferalDictionaries = this.refferalDictionaryRepository.getAll();
        for(int i = 0; i < refferalDictionaries.size(); i++ ){
            if(refferalDictionaries.get(i).getName().equals(((RefferalDictionary) model).getName())){
                errors.rejectValue("name","alreadyExist.name","Таке направлення уже зареєстровано.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Введіть направлення.");
    }
}
