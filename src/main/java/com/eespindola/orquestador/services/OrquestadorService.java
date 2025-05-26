package com.eespindola.orquestador.services;

import com.eespindola.orquestador.exceptions.InvalidArgument;
import com.eespindola.orquestador.models.dto.Result;
import com.eespindola.orquestador.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrquestadorService {

    @PostMapping
    Result<Usuario> GetAll(HttpSession session, Result<Void> request);

    @PostMapping("/{folioId}")
    Result<Usuario> GetByFolio(HttpSession session, @PathVariable String folioId);

    @PostMapping("/post")
    Result<Void> Post(HttpSession session, @RequestBody Result<Usuario> request) throws InvalidArgument;

    @PostMapping("/put")
    Result<Void> Put(HttpSession session, @RequestBody Result<Usuario> request) throws InvalidArgument;

    @PostMapping("/delete/{folioId}")
    Result<Void> Delete(HttpSession session, @PathVariable String folioId);
}
