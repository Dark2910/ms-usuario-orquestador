package com.eespindola.orquestador.controller;

import com.eespindola.orquestador.exceptions.InvalidArgument;
import com.eespindola.orquestador.models.dto.Result;
import com.eespindola.orquestador.models.Usuario;
import com.eespindola.orquestador.services.OrquestadorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orquestador")
public class OrquestadorController {

    @Autowired
    OrquestadorService service;

    @PostMapping
    public Result<Usuario> orquesatdorGetAll(
            HttpSession session
    ) {
        return service.getAll(session);
    }

    @PostMapping("/{folioId}")
    public Result<Usuario> orquestadorGetByFolio(
            HttpSession session,
            @PathVariable String folioId
    ) {
        return service.getByFolio(session, folioId);
    }

    @PostMapping("/post")
    public Result<Void> orquestadorPost(
            HttpSession session,
            @RequestBody Result<Usuario> request
    ) throws InvalidArgument {
        return service.post(session, request);
    }

    @PostMapping("/put")
    public Result<Void> orquestadorPut(
            HttpSession session,
            @RequestBody Result<Usuario> request
    ) throws InvalidArgument {
        return service.put(session, request);
    }

    @PostMapping("/delete/{folioId}")
    public Result<Void> orquestadorDelete(
            HttpSession session,
            @PathVariable String folioId
    ) {
        return service.delete(session, folioId);
    }

}
