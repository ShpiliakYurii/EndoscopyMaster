package com.springapp.mvc.validation;

import com.springapp.mvc.domain.ConclusionDictionary;
import com.springapp.mvc.repository.ConclusionRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 18.02.2016.
 */
@Component
public class ConclusionValidator implements Validator{

    private ConclusionRepository conclusionRepository;
    public void setConclusionRepository(ConclusionRepository conclusionRepository){
        this.conclusionRepository = conclusionRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ConclusionDictionary.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<ConclusionDictionary> conclusionDictionaries = this.conclusionRepository.getAll();
        for(int i = 0; i < conclusionDictionaries.size(); i++ ){
            if(conclusionDictionaries.get(i).getName().equals(((ConclusionDictionary) model).getName())){
                errors.rejectValue("name","alreadyExist.name","Заключення з такою назвою уже зареєстровано.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Введіть назву заключення.");
    }
}
