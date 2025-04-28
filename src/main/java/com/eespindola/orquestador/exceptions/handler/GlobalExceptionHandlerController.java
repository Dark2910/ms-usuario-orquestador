package com.eespindola.orquestador.exceptions.handler;

import com.eespindola.orquestador.dto.Result;
import com.eespindola.orquestador.exceptions.InvalidArgument;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler({MethodArgumentNotValidException.class, InvalidArgument.class})
    protected ResponseEntity<Result> handleNoHandlerFoundException(InvalidArgument ex){

        Result<String> result = new Result<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::mensajeDeErrorFormateado)
                .collect(Collectors.toList());

        result.setObjects(errors);
        result.setMessage("Folio: " + ex.getFolioRequest());

        return ResponseEntity.badRequest().body(result);
    }

    private String mensajeDeErrorFormateado(FieldError fieldError){
        return fieldError.getDefaultMessage().concat(" ( ").concat(fieldError.getField() + " ).");
    }

}
