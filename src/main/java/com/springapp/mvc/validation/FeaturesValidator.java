package com.springapp.mvc.validation;

import com.springapp.mvc.domain.FeaturesDictionary;
import com.springapp.mvc.repository.FeaturesRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 16.02.2016.
 */
@Component
public class FeaturesValidator implements Validator{
    private FeaturesRepository featuresRepository;

    public void setFeaturesRepository(FeaturesRepository featuresRepository){
        this.featuresRepository = featuresRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FeaturesDictionary.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<FeaturesDictionary> featuresDictionaries = this.featuresRepository.getAll();
        if(((FeaturesDictionary) model).getName().length()>49){
            ((FeaturesDictionary) model).setName(((FeaturesDictionary) model).getName().substring(0,49));
        }
        for(int i = 0; i < featuresDictionaries.size(); i++ ){
            if(featuresDictionaries.get(i).getName().equals(((FeaturesDictionary) model).getName())
                    && featuresDictionaries.get(i).getIdTerm() == ((FeaturesDictionary) model).getIdTerm()){
                errors.rejectValue("name","alreadyExist.name","Ознака з такою назвою для вибраного терміну уже зареєстрована.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Введіть назву ознаки.");
    }
}
