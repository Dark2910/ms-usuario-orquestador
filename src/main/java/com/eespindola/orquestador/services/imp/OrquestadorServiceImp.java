package com.eespindola.orquestador.services.imp;

import com.eespindola.orquestador.exceptions.InvalidArgument;
import com.eespindola.orquestador.dto.Result;
import com.eespindola.orquestador.models.Usuario;
import com.eespindola.orquestador.services.OrquestadorService;
import com.eespindola.orquestador.utils.Constantes;
import com.eespindola.orquestador.utils.FolioRequest;
import com.eespindola.orquestador.validator.ValidateInput;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrquestadorServiceImp implements OrquestadorService {

    private final RestTemplate restTemplate;
    private final ValidateInput validateInput;

    @Autowired
    public OrquestadorServiceImp(ValidateInput validateInput){
        this.restTemplate = new RestTemplate();
        this.validateInput = validateInput;
    }

    @Override
    public Result<Usuario> GetAll(HttpSession session) {
//        session.setAttribute("session", FolioRequest.CrearFolioRequest());

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("folioRequest", FolioRequest.CrearFolioRequest());

        HttpEntity<Result<Usuario>> httpEntity = new HttpEntity<>(null, httpHeader);

        ResponseEntity<Result<Usuario>> response = restTemplate.exchange(
                Constantes.ENDPOINT_GET_ALL,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Result<Usuario>>() {}
        );

        return  response.getBody();
    }

    @Override
    public Result<?> GetByFolio(String folioId, HttpSession session) {
        //session.setAttribute("session", FolioRequest.CrearFolioRequest());

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("folioRequest", FolioRequest.CrearFolioRequest());

        HttpEntity<?> httpEntity = new HttpEntity<>(HttpEntity.EMPTY, httpHeader);

        ResponseEntity<Result<?>> response = restTemplate.exchange(
                Constantes.ENDPOINT_GET_BY_FOLIO,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Result<?>>() {},
                folioId
        );

        return  response.getBody();
    }

    @Override
    public Result<?> Post(Usuario usuario, HttpSession session) throws InvalidArgument {
        //session.setAttribute("session", FolioRequest.CrearFolioRequest());

        validateInput.BindingResult(usuario, "Usuario");

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("folioRequest", FolioRequest.CrearFolioRequest());

        HttpEntity<?> httpEntity = new HttpEntity<>(usuario, httpHeader);

        ResponseEntity<Result<?>> response = restTemplate.exchange(
                Constantes.ENDPOINT_POST,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Result<?>>() {}
        );

        return  response.getBody();
    }

    @Override
    public Result<?> Put(Usuario usuario, HttpSession session) throws InvalidArgument {
        //session.setAttribute("session", FolioRequest.CrearFolioRequest());

        validateInput.BindingResult(usuario, "Usuario");

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("folioRequest", FolioRequest.CrearFolioRequest());

        HttpEntity<?> httpEntity = new HttpEntity<>(usuario, httpHeader);

        ResponseEntity<Result<?>> response = restTemplate.exchange(
                Constantes.ENDPOINT_PUT,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Result<?>>() {}
        );

        return  response.getBody();
    }

    @Override
    public Result<?> Delete(String folioId, HttpSession session) {
        //session.setAttribute("session", FolioRequest.CrearFolioRequest());

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("folioRequest", FolioRequest.CrearFolioRequest());

        HttpEntity<?> httpEntity = new HttpEntity<>(HttpEntity.EMPTY, httpHeader);

        ResponseEntity<Result<?>> response = restTemplate.exchange(
                Constantes.ENDPOINT_DELETE,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Result<?>>() {},
                folioId
        );

        return  response.getBody();
    }


}

