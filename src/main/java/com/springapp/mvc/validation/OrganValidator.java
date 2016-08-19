package com.springapp.mvc.validation;

import com.springapp.mvc.domain.OrganDictionary;
import com.springapp.mvc.repository.OrganRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 16.02.2016.
 */
@Component
public class OrganValidator implements Validator{
    private OrganRepository organRepository;

    public void setOrganRepository(OrganRepository organRepository){
        this.organRepository = organRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OrganDictionary.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<OrganDictionary> organDictionaries = this.organRepository.getAll();
        for(int i = 0; i < organDictionaries.size(); i++ ){
            if(organDictionaries.get(i).getName().equals(((OrganDictionary) model).getName())){
                errors.rejectValue("name","alreadyExist.name","Орган з такою назвою уже зареєстровано.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Введіть назву органу.");
    }
}
