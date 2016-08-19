package com.springapp.mvc.validation;

import com.springapp.mvc.domain.TermDictionary;
import com.springapp.mvc.repository.TermRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 16.02.2016.
 */
@Component
public class TermValidator implements Validator{
    private TermRepository termRepository;

    public void setTermRepository(TermRepository termRepository){
        this.termRepository = termRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TermDictionary.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<TermDictionary> termDictionaries = this.termRepository.getAll();
        for(int i = 0; i < termDictionaries.size(); i++ ){
            if(termDictionaries.get(i).getName().equals(((TermDictionary) model).getName())
                    && termDictionaries.get(i).getIdRegion() == ((TermDictionary) model).getIdRegion()){
                errors.rejectValue("name","alreadyExist.name","Термін з заданою назвою у вибраної ділянки уже зареєстровано.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Введіть назву терміну.");
    }
}
