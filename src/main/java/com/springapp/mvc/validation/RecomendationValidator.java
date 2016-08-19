package com.springapp.mvc.validation;

import com.springapp.mvc.domain.RecomendationDictionary;
import com.springapp.mvc.repository.RecomendationRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 23.02.2016.
 */
@Component
public class RecomendationValidator implements Validator{
    private RecomendationRepository recomendationRepository;

    public void setRecomendationDictionary(RecomendationRepository recomendationRepository){
        this.recomendationRepository = recomendationRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RecomendationDictionary.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<RecomendationDictionary> recomendationDictionaries = this.recomendationRepository.getAll();
        for(int i = 0; i < recomendationDictionaries.size(); i++ ){
            if(recomendationDictionaries.get(i).getName().equals(((RecomendationDictionary) model).getName())){
                errors.rejectValue("name","alreadyExist.name","Ця рекомендація уже зареєстрована.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Введіть рекомендацію.");
    }
}
