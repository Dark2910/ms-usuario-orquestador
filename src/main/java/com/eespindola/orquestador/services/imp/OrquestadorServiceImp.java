package com.eespindola.orquestador.services.imp;

import com.eespindola.orquestador.annotations.AroundAOP;
import com.eespindola.orquestador.annotations.BeforeAOP;
import com.eespindola.orquestador.exceptions.InvalidArgument;
import com.eespindola.orquestador.models.dto.Result;
import com.eespindola.orquestador.models.Usuario;
import com.eespindola.orquestador.services.OrquestadorService;
import com.eespindola.orquestador.utils.Constantes;
import com.eespindola.orquestador.utils.FolioRequest;
import com.eespindola.orquestador.utils.InputValidator;
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
    private final InputValidator inputValidator;

    @Autowired
    public OrquestadorServiceImp(InputValidator validator) {
        this.restTemplate = new RestTemplate();
        this.inputValidator = validator;
    }

    @AroundAOP
    @Override
    public Result<Usuario> GetAll(HttpSession session, Result<Void> request) {
//        session.setAttribute("session", FolioRequest.CrearFolioRequest());
//        HttpHeaders httpHeader = new HttpHeaders();
//        httpHeader.add("folioRequest", FolioRequest.getFolio());

//        HttpEntity<Result<Usuario>> httpEntity = new HttpEntity<>(null, httpHeader);
        HttpEntity<Result<Void>> httpEntity = new HttpEntity<>(request);

        ResponseEntity<Result<Usuario>> response = restTemplate.exchange(
                Constantes.ENDPOINT_GET_ALL,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }

    @AroundAOP
    @Override
    public Result<Usuario> GetByFolio(HttpSession session, String folioId) {
        //session.setAttribute("session", FolioRequest.CrearFolioRequest());

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("folioRequest", FolioRequest.getFolio());

        HttpEntity<Result<Usuario>> httpEntity = new HttpEntity<>(null, httpHeader);

        ResponseEntity<Result<Usuario>> response = restTemplate.exchange(
                Constantes.ENDPOINT_GET_BY_FOLIO,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                },
                folioId
        );

        return response.getBody();
    }

    @AroundAOP
    @Override
    public Result<Void> Post(HttpSession session, Result<Usuario> request) throws InvalidArgument {
//        session.setAttribute("session", FolioRequest.CrearFolioRequest());
//        HttpHeaders httpHeader = new HttpHeaders();
//        httpHeader.add("folioRequest", FolioRequest.getFolio());

        HttpEntity<Result<Usuario>> httpEntity = new HttpEntity<>(request);

        ResponseEntity<Result<Void>> response = restTemplate.exchange(
                Constantes.ENDPOINT_POST,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }

    @AroundAOP
    @Override
    public Result<Void> Put(HttpSession session, Result<Usuario> request) throws InvalidArgument {
//        session.setAttribute("session", FolioRequest.CrearFolioRequest());
//        HttpHeaders httpHeader = new HttpHeaders();
//        httpHeader.add("folioRequest", FolioRequest.getFolio());

        HttpEntity<Result<Usuario>> httpEntity = new HttpEntity<>(request);

        ResponseEntity<Result<Void>> response = restTemplate.exchange(
                Constantes.ENDPOINT_PUT,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }

    @Override
    public Result<Void> Delete(HttpSession session, String folioId) {
        //session.setAttribute("session", FolioRequest.CrearFolioRequest());

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("folioRequest", FolioRequest.getFolio());

        HttpEntity<Void> httpEntity = new HttpEntity<>(null, httpHeader);

        ResponseEntity<Result<Void>> response = restTemplate.exchange(
                Constantes.ENDPOINT_DELETE,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                },
                folioId
        );

        return response.getBody();
    }


}

