package com.eespindola.orquestador.controller;

import com.eespindola.orquestador.annotations.AroundAOP;
import com.eespindola.orquestador.annotations.BeforeAOP;
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
    public Result<Usuario> OrquesatdorGetAll(HttpSession session, @RequestBody Result<Void> request) {
        return service.GetAll(session, request);
    }

    @PostMapping("/{folioId}")
    public Result<Usuario> OrquestadorGetByFolio(HttpSession session, @PathVariable String folioId){
        return service.GetByFolio(session, folioId);
    }

    @PostMapping("/post")
    public Result<Void> OrquestadorPost(HttpSession session, @RequestBody Result<Usuario> request) throws InvalidArgument {
        return service.Post(session, request);
    }

    @PostMapping("/put")
    public Result<Void> OrquestadorPut(HttpSession session, @RequestBody Result<Usuario> request) throws InvalidArgument {
        return service.Put(session, request);
    }

    @PostMapping("/delete/{folioId}")
    public Result<Void> OrquestadorDelete(HttpSession session, @PathVariable String folioId){
        return service.Delete(session, folioId);
    }

}
