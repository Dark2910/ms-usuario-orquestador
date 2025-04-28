package com.eespindola.orquestador.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result <T>{

    private Boolean isCorrect;
    private List<T> objects;
    private T object;
    private Exception exception;
    private String message;

}



