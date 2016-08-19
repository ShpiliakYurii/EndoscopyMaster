package com.springapp.mvc.validation;

import com.springapp.mvc.domain.DisinfectantSolution;
import com.springapp.mvc.repository.DisinfectantSolutionRepository;
import com.springapp.mvc.repository.RevisionsRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 14.02.2016.
 */
@Component
public class DisinfectantSolutionValidator implements Validator{
    private DisinfectantSolutionRepository disinfectantSolutionRepository;

    public void setDisinfectantSolutionRepository(DisinfectantSolutionRepository disinfectantSolutionRepository){
        this.disinfectantSolutionRepository = disinfectantSolutionRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return DisinfectantSolution.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<DisinfectantSolution> disinfectantSolutions = this.disinfectantSolutionRepository.getAll();
        for(int i = 0; i < disinfectantSolutions.size(); i++ ){
            if(disinfectantSolutions.get(i).getName().equals(((DisinfectantSolution) model).getName())){
                errors.rejectValue("name","alreadyExist.name","Такий розчин уже зареєстровано.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Введіть ім'я розчину.");
    }
}
