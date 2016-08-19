package com.springapp.mvc.validation;

import com.springapp.mvc.domain.User;
import com.springapp.mvc.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Yurii on 18.10.2015.
 */
@Component
public class UserValidator implements Validator {
    UserRepository userRepository;
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        List<User> users = userRepository.getAll();
        for(int i = 0; i < users.size(); i++ ){
            if(users.get(i).getLogin().equals(((User)model).getLogin())){
                errors.rejectValue("login","alreadyExist.login","Такий логін уже існує");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"login","required.login","Введіть логін.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"pass","required.pass","Введіть пароль.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"role","required.role","Введіть роль.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"pib","required.pib","Введіть прізвище, ім'я, по-батькові.");
    }
}
