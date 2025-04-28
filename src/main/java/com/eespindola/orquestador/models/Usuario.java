package com.eespindola.orquestador.models;

import com.eespindola.orquestador.utils.Constantes;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario {

    private int idUsuario;
    private String folio;

    @NotBlank(message = Constantes.NOT_BLANK)
    private String nombre;
    @NotBlank(message = Constantes.NOT_BLANK)
    private String apellidoPaterno;
    @NotBlank(message = Constantes.NOT_BLANK)
    private String apellidoMaterno;
    @NotBlank(message = Constantes.NOT_BLANK)
    private String fechaNacimiento;
    @NotBlank(message = Constantes.NOT_BLANK)
    private String username;
    @Email(message = "Por favor inserta un correo valido.")
    @NotBlank(message = Constantes.NOT_BLANK)
    private String email;
    @NotBlank(message = Constantes.NOT_BLANK)
    private String password;

    private String status;

}
