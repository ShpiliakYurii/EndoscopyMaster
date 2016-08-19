package com.springapp.mvc.validation;

import com.springapp.mvc.domain.Medicinecard;
import com.springapp.mvc.repository.MedicinecardRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.List;

/**
 * Created by Yurii on 21.12.2015.
 */

@Component
public class MedicinecardValidator implements Validator {
    public void setMedicinecardRepository(MedicinecardRepository medicinecardRepository) {
        this.medicinecardRepository = medicinecardRepository;
    }

    MedicinecardRepository medicinecardRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Medicinecard.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {

        List<Medicinecard> medicinecards = this.medicinecardRepository.getAll();
        if((""+((Medicinecard) model).getIdentifyCode()).length() != 10){
            errors.rejectValue("identifyCode","badFormat.identifyCode","Ідентифікаційний код повинен складатися з десяти цифр.");
        }
        for(int i = 0; i < medicinecards.size(); i++ ){
            if(medicinecards.get(i).getIdentifyCode() == ((Medicinecard)model).getIdentifyCode()){
                errors.rejectValue("identifyCode","alreadyExist.identifyCode","Пацієнт з таким ідентифікаційним кодом уже існує.");
            }
            if(medicinecards.get(i).getPib().equals(((Medicinecard) model).getPib())){
                errors.rejectValue("pib","alreadyExist.pib","Пацієнт з таким прізвищем ім'ям по батькові уже існує.");
            }
        }
        if(((Medicinecard) model).getBurnDate().getTime() > (new Date().getTime() - 3600)){
            errors.rejectValue("burnDate","badDate.burnDate","Некоректна дата народження.");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identifyCode", "required.identifyCode", "Введіть ідентифікаційний код.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"pib","required.pib","Введіть празвище ім'я по батькові.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"burnDate","required.burnDate","Введіть дату народження.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"adress","required.adress","Введіть адрес постійного місця проживання.");
    }
}
