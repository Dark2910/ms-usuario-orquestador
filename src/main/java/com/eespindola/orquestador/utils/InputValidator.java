package com.eespindola.orquestador.utils;

import com.eespindola.orquestador.exceptions.InvalidArgument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@Component
public class InputValidator {

    private final Validator validator;

    @Autowired
    public InputValidator(Validator validator1) {
        this.validator = validator1;
    }

    public void bindingResult(Object target, String objectName) throws InvalidArgument  {
//    public BindingResult bindingResult(Object target, String objectName) throws InvalidArgument  {

        BindingResult bindingResult = new BeanPropertyBindingResult(target, objectName);

        validator.validate(target, bindingResult);

        if(bindingResult.hasErrors()){
            throw new InvalidArgument(bindingResult, FolioRequest.getFolio());
        }
//        return bindingResult;
    }

}
