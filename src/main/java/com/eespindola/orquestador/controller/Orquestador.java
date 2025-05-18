package com.eespindola.orquestador.controller;

import com.eespindola.orquestador.exceptions.InvalidArgument;
import com.eespindola.orquestador.models.dto.Result;
import com.eespindola.orquestador.models.Usuario;
import com.eespindola.orquestador.services.OrquestadorService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orquestador")
public class Orquestador {

    @Autowired
    OrquestadorService service;

    @PostMapping
    public Result<Usuario> OrquesatdorGetAll(HttpSession session) {
        return service.GetAll(session);
    }

    @PostMapping("/{folioId}")
    public Result<Usuario> OrquestadorGetByFolio(@PathVariable String folioId, HttpSession session){
        return service.GetByFolio(folioId,session);
    }

    @PostMapping("/post")
    public Result<Void> OrquestadorPost(@RequestBody @Valid Usuario usuario, HttpSession session) throws InvalidArgument {
        return service.Post(usuario,session);
    }

    @PostMapping("/put")
    public Result<Void> OrquestadorPut(@RequestBody Usuario usuario, HttpSession session) throws InvalidArgument {
        return service.Put(usuario,session);
    }

    @PostMapping("/delete/{folioId}")
    public Result<Void> OrquestadorDelete(@PathVariable String folioId, HttpSession session){
        return service.Delete(folioId, session);
    }

}
