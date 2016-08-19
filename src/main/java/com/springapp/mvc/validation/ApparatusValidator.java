package com.springapp.mvc.validation;
import com.springapp.mvc.domain.Apparatus;
import com.springapp.mvc.repository.ApparatusRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 14.02.2016.
 */
@Component
public class ApparatusValidator implements Validator{

    private ApparatusRepository apparatusRepository;

    public void setApparatusRepository(ApparatusRepository apparatusRepository){
        this.apparatusRepository = apparatusRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Apparatus.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<Apparatus> apparatuses = this.apparatusRepository.getAll();
        for(int i = 0; i < apparatuses.size(); i++ ){
            if(apparatuses.get(i).getName().equals(((Apparatus) model).getName())){
                errors.rejectValue("name","alreadyExist.name","Апарат з таким ім'ям уже зареєстровано.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Введіть ім'я апарату.");
    }
}
