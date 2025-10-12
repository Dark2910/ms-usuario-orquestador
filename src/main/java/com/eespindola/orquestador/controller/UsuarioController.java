package com.eespindola.orquestador.controller;

import com.eespindola.orquestador.exceptions.InvalidArgument;
import com.eespindola.orquestador.models.dto.Result;
import com.eespindola.orquestador.models.Usuario;
import com.eespindola.orquestador.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orquestador")
public class UsuarioController {

    private final UsuarioService service;

    @Autowired
    public UsuarioController(
            UsuarioService usuarioService
    ){
      this.service = usuarioService;
    }

    @GetMapping("/all")
    public Result<Usuario> getAll(
            HttpSession session
    ) {
        return service.getAll(session);
    }

    @GetMapping("/{folioId}")
    public Result<Usuario> getByFolio(
            HttpSession session,
            @PathVariable String folioId
    ) {
        return service.getByFolio(session, folioId);
    }

    @PostMapping("/post")
    public Result<Void> post(
            HttpSession session,
            @RequestBody Result<Usuario> body
    ) throws InvalidArgument {
        return service.post(session, body);
    }

    @PutMapping("/put")
    public Result<Void> put(
            HttpSession session,
            @RequestBody Result<Usuario> body
    ) throws InvalidArgument {
        return service.put(session, body);
    }

    @DeleteMapping("/delete/{folioId}")
    public Result<Void> delete(
            HttpSession session,
            @PathVariable String folioId
    ) {
        return service.delete(session, folioId);
    }

}
