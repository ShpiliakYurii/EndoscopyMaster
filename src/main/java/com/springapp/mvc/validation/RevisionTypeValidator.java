package com.springapp.mvc.validation;

import com.springapp.mvc.domain.Revisiontype;
import com.springapp.mvc.repository.RevisionTypeRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 14.02.2016.
 */
@Component
public class RevisionTypeValidator implements Validator{

    private RevisionTypeRepository revisionTypeRepository;

    public void setRevisionTypeRepository(RevisionTypeRepository revisionTypeRepository){
        this.revisionTypeRepository = revisionTypeRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Revisiontype.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<Revisiontype> revisiontypes = this.revisionTypeRepository.getAll();
        if(((Revisiontype) model).getAbr().length() > 10)
            errors.rejectValue("abr","tooLong.abr","Абривіатура до 10 символів.");
        for(int i = 0; i < revisiontypes.size(); i++ ){
            if(revisiontypes.get(i).getRevisionName().equals(((Revisiontype) model).getRevisionName())){
                errors.rejectValue("revisionName","alreadyExist.revisionName","Такий вид обстеження уже зареєстровано.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "revisionName", "required.revisionName", "Введіть вид обстеження.");
    }
}
