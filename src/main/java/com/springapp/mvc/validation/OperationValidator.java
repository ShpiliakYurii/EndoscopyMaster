package com.springapp.mvc.validation;

import com.springapp.mvc.domain.OperationDictionary;
import com.springapp.mvc.repository.OperationRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 23.02.2016.
 */
@Component
public class OperationValidator implements Validator{
    private OperationRepository operationRepository;

    public void setOperationRepository(OperationRepository operationRepository){
        this.operationRepository = operationRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OperationDictionary.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<OperationDictionary> operationDictionaries = this.operationRepository.getAll();
        for(int i = 0; i < operationDictionaries.size(); i++ ){
            if(operationDictionaries.get(i).getName().equals(((OperationDictionary) model).getName())){
                errors.rejectValue("name","alreadyExist.name","Введена операція уже зареєстрована.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Введіть операцію.");
    }
}
