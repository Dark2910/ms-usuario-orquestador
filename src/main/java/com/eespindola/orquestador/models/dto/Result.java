package com.eespindola.orquestador.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result <T>{

    @JsonProperty("isCorrect")
    private Boolean isCorrect;

    @JsonProperty("objects")
    private List<T> objects;

    @JsonProperty("object")
    private T object;

    @JsonPropertyOrder("exception")
    private Exception exception;

    @JsonProperty("message")
    private String message;

}



