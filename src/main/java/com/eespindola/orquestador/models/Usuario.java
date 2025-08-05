package com.eespindola.orquestador.models;

import com.eespindola.orquestador.utils.ConstantesUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario {

    @JsonProperty("idUsuario")
    private int idUsuario;

    @JsonProperty("folioId")
    private String folioId;

    @JsonProperty("nombre")
    @NotBlank(message = ConstantesUtils.NOT_BLANK)
    private String nombre;

    @JsonProperty("apellidoPaterno")
    @NotBlank(message = ConstantesUtils.NOT_BLANK)
    private String apellidoPaterno;

    @JsonProperty("apellidoMaterno")
    @NotBlank(message = ConstantesUtils.NOT_BLANK)
    private String apellidoMaterno;

    @JsonProperty("fechaNacimiento")
    @NotBlank(message = ConstantesUtils.NOT_BLANK)
    private String fechaNacimiento;

    @JsonProperty("username")
    @NotBlank(message = ConstantesUtils.NOT_BLANK)
    private String username;

    @JsonProperty("email")
    @Email(message = "Por favor inserta un correo valido.")
    @NotBlank(message = ConstantesUtils.NOT_BLANK)
    private String email;

    @JsonProperty("password")
    @NotBlank(message = ConstantesUtils.NOT_BLANK)
    private String password;

    @JsonProperty("status")
    private String status;

}
