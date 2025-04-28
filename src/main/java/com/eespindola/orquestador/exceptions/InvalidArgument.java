package com.eespindola.orquestador.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@Setter
public class InvalidArgument extends MethodArgumentNotValidException {

    private String folioRequest;

    /**
     * Constructor for {@link MethodArgumentNotValidException}.
     *
     * @param parameter     the parameter that failed validation
     * @param bindingResult the results of the validation
     */
    public InvalidArgument(MethodParameter parameter, BindingResult bindingResult) {
        super(parameter, bindingResult);
    }

    public InvalidArgument(BindingResult bindingResult, String folioRequest) {
        super(null, bindingResult);
        this.folioRequest = folioRequest;
    }

    @Override
    public @NotNull String getMessage(){
        return "";
    }
}
