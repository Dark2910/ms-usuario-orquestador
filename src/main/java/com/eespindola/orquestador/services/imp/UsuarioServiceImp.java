package com.eespindola.orquestador.services.imp;

import com.eespindola.orquestador.annotations.AroundAOP;
import com.eespindola.orquestador.models.RestRequest;
import com.eespindola.orquestador.models.dto.Result;
import com.eespindola.orquestador.models.Usuario;
import com.eespindola.orquestador.services.UsuarioService;
import com.eespindola.orquestador.utils.ConstantesUtils;
import com.eespindola.orquestador.utils.InputValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Service
public class UsuarioServiceImp implements UsuarioService {

  private final RestTemplate restTemplate;
  private final InputValidator inputValidator;

  @Autowired
  public UsuarioServiceImp(InputValidator validator) {
    this.restTemplate = new RestTemplate();
    this.inputValidator = validator;
  }

  @AroundAOP
  @Override
  public Result<Usuario> getAll(HttpSession session) {
    log.info("Consultando todos los registros");

//        HttpHeaders httpHeader = new HttpHeaders();
//        httpHeader.add("folioRequest", FolioRequest.getFolio());

    RestRequest<Result<Usuario>> request = RestRequest.<Result<Usuario>>builder()
            .url(ConstantesUtils.ENDPOINT_GET_ALL_DOCKER)
            .method(HttpMethod.GET)
            .parameterizedTypeReference(new ParameterizedTypeReference<>() {})
            .typeReference(new TypeReference<>() {})
            .build();
    log.info("Request: {}", request);

    return sendHttpClientPetition(request);
  }

  @AroundAOP
  @Override
  public Result<Usuario> getByFolio(HttpSession session, String folioId) {
    log.info("Consultando registro por folio");

//        HttpHeaders httpHeader = new HttpHeaders();
//        httpHeader.add("folioRequest", FolioRequest.getFolio());

    RestRequest<Result<Usuario>> request = RestRequest.<Result<Usuario>>builder()
            .url(ConstantesUtils.ENDPOINT_GET_BY_FOLIO_DOCKER)
            .method(HttpMethod.GET)
            .parameterizedTypeReference(new ParameterizedTypeReference<>() {})
            .typeReference(new TypeReference<>() {})
            .uriVariables(new String[]{folioId})
            .build();
    log.info("Request: {}", request);

    return sendHttpClientPetition(request);
  }

  @AroundAOP
  @Override
  public Result<Void> post(HttpSession session, Result<Usuario> body) {
    log.info("Registrando nuevo usuario");

//        HttpHeaders httpHeader = new HttpHeaders();
//        httpHeader.add("folioRequest", FolioRequest.getFolio());

    RestRequest<Result<Void>> request = RestRequest.<Result<Void>>builder()
            .url(ConstantesUtils.ENDPOINT_POST)
            .method(HttpMethod.POST)
            .body(body)
            .parameterizedTypeReference(new ParameterizedTypeReference<>() {})
            .typeReference(new TypeReference<>() {})
            .build();
    log.info("Request: {}", request);

    return sendHttpClientPetition(request);
  }

  @AroundAOP
  @Override
  public Result<Void> put(HttpSession session, Result<Usuario> body) {
    log.info("Actualizando usuario");

//        HttpHeaders httpHeader = new HttpHeaders();
//        httpHeader.add("folioRequest", FolioRequest.getFolio());

    RestRequest<Result<Void>> request = RestRequest.<Result<Void>>builder()
            .url(ConstantesUtils.ENDPOINT_PUT)
            .method(HttpMethod.PUT)
            .body(body)
            .parameterizedTypeReference(new ParameterizedTypeReference<>() {})
            .typeReference(new TypeReference<>() {})
            .build();
    log.info("Request: {}", request);

    return sendHttpClientPetition(request);
  }

  @AroundAOP
  @Override
  public Result<Void> delete(HttpSession session, String folioId) {
    log.info("Eliminando usuario");

//        HttpHeaders httpHeader = new HttpHeaders();
//        httpHeader.add("folioRequest", FolioRequest.getFolio());

    RestRequest<Result<Void>> request = RestRequest.<Result<Void>>builder()
            .url(ConstantesUtils.ENDPOINT_DELETE)
            .method(HttpMethod.DELETE)
            .parameterizedTypeReference(new ParameterizedTypeReference<>() {})
            .typeReference(new TypeReference<>() {})
            .uriVariables(new String[]{folioId})
            .build();
    log.info("Request: {}", request);

    return sendRestTemplatePetition(request);
  }

  private <T> T sendRestTemplatePetition(RestRequest<T> request) {
    log.info("Lanzando peticion por RestTemplate");
    try {
      HttpEntity<Object> body = (Objects.nonNull(request.getHeaders())) ?
              new HttpEntity<>(request.getBody(), request.getHeaders()) :
              new HttpEntity<>(request.getBody());

      ResponseEntity<T> response = restTemplate.exchange(
              request.getUrl(),
              request.getMethod(),
              body,
              request.getParameterizedTypeReference(),
              request.getUriVariables()
      );

      log.info("ResponseEntity: {}", response);
      return response.getBody();
    } catch (Throwable e) {
      log.info("Error al lanzar RestTemplatePetition: {}", e.toString());
      return null;
    }
  }

  private <T> T sendHttpClientPetition(RestRequest<T> request) {
    log.info("Lanzando peticion por HttpClient");
    try {
      HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder();

      URI uri = buildURI(request.getUrl(), request.getUriVariables());
      httpRequestBuilder.uri(uri);

      HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher(request.getBody(), request.getMethod());
      httpRequestBuilder.method(request.getMethod().name(), bodyPublisher);

      addHeaders(httpRequestBuilder, request.getHeaders(), request.getMethod());

      HttpClient httpClient = HttpClient.newHttpClient();
      HttpRequest httpRequest = httpRequestBuilder.build();
      HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

      log.info("HttpResponse: {}", httpResponse);
      return new ObjectMapper().readValue(httpResponse.body(), request.getTypeReference());
    } catch (Throwable e) {
      log.info("Error al lanzar HttpClientPetition: {}", e.toString());
      return null;
    }
  }

  private static URI buildURI(String url, Object[] uriVariables) {
    log.info("Armando URI");

    if (Objects.nonNull(uriVariables)) {
      for (Object uriVariable : uriVariables) {
        url = url.replaceFirst("\\{[^}]+}", URLEncoder.encode(uriVariable.toString(), StandardCharsets.UTF_8));
      }
    }
    log.info("URI: {}", url);
    return URI.create(url);
  }

  private static final Set<HttpMethod> NO_BODY_METHODS = Set.of(
          HttpMethod.GET,
          HttpMethod.DELETE,
          HttpMethod.OPTIONS
  );

  private HttpRequest.BodyPublisher getBodyPublisher(Object body, HttpMethod method) throws JsonProcessingException {
    log.info("Obteniendo BodyPublisher");

    HttpRequest.BodyPublisher bodyPublisher;
    if (Objects.isNull(body) || NO_BODY_METHODS.contains(method)) {
      bodyPublisher = HttpRequest.BodyPublishers.noBody();
    } else {
      String jsonBody = new ObjectMapper().writeValueAsString(body);
      bodyPublisher = HttpRequest.BodyPublishers.ofString(jsonBody);
    }
    log.info("BodyPublisher: {}", bodyPublisher);
    return bodyPublisher;
  }

  private void addHeaders(HttpRequest.Builder httpRequestBuilder, HttpHeaders headers, HttpMethod method) {
    log.info("Agregando headers");

    String CONTENT_TYPE = "Content-Type";
    String APPLICATION_JSON = "application/json";

    if (!NO_BODY_METHODS.contains(method)) {
      if (Objects.isNull(headers) || headers.isEmpty()) {
        log.info("Headers por defecto: {}, {}", CONTENT_TYPE, APPLICATION_JSON);
        httpRequestBuilder.header(CONTENT_TYPE, APPLICATION_JSON);
      } else {
        log.info("Heders encontrados");
        headers.forEach((key, values) -> {
                log.info("key: {}, value: {}", key, values);
                values.forEach(value -> httpRequestBuilder.header(key, value));
        });
        // Agregar Content-Type por defecto si no está definido
        if (!headers.containsKey(CONTENT_TYPE)) {
          log.info("Asignando headers por defecto: {}, {}", CONTENT_TYPE, APPLICATION_JSON);
          httpRequestBuilder.header(CONTENT_TYPE, APPLICATION_JSON);
        }
      }
    }
  }

}

