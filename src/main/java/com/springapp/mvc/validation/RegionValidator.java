package com.springapp.mvc.validation;

import com.springapp.mvc.domain.RegionDictionary;
import com.springapp.mvc.repository.RegionRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 16.02.2016.
 */
@Component
public class RegionValidator implements Validator{
    private RegionRepository regionRepository;

    public void setRegionRepository(RegionRepository regionRepository){
        this.regionRepository = regionRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegionDictionary.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<RegionDictionary> regionDictionaries = this.regionRepository.getAll();
        for(int i = 0; i < regionDictionaries.size(); i++ ){
            if(regionDictionaries.get(i).getName().equals(((RegionDictionary) model).getName())
                    && regionDictionaries.get(i).getIdOrgan().equals(((RegionDictionary) model).getIdOrgan())){
                errors.rejectValue("name","alreadyExist.name","Ділянка з такою назвою у вибраного органа уже зареєстрована.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Введіть назву ділянки.");
    }
}
