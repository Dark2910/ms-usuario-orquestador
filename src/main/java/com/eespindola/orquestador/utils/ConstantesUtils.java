package com.eespindola.orquestador.utils;

public class ConstantesUtils {

    private ConstantesUtils() {
        throw new IllegalStateException("Util class");
    }

    public static final String NOT_NULL = "No puede dejar el campo como nulo";
    public static final String NOT_EMPTY = "El conjunto de datos no puede esta vacio";
    public static final String NOT_BLANK = "No se puede dejar el campo en blanco, como espacio vacio o nulo";

    public static final String ERROR_400 = "Bad Request, por favor verifica tu solicitud";

    public static final String ENDPOINT_GET_ALL = "http://localhost:8081/usuarioAPI";
    public static final String ENDPOINT_GET_BY_FOLIO = "http://localhost:8082/usuarioAPI/{folioId}";
    public static final String ENDPOINT_POST = "http://localhost:8083/usuarioAPI/post";
    public static final String ENDPOINT_PUT = "http://localhost:8084/usuarioAPI/put";
    public static final String ENDPOINT_DELETE = "http://localhost:8085/usuarioAPI/delete/{folioId}";

    public static final String ENDPOINT_GET_ALL_JPA = "http://localhost:8081/usuarioAPI/jpa";
    public static final String ENDPOINT_GET_BY_FOLIO_JPA = "http://localhost:8082/usuarioAPI/jpa/{folioId}";
    public static final String ENDPOINT_POST_JPA = "http://localhost:8083/usuarioAPI/jpa/post";
    public static final String ENDPOINT_PUT_JPA = "http://localhost:8084/usuarioAPI/jpa/put";
    public static final String ENDPOINT_DELETE_JPA = "http://localhost:8085/usuarioAPI/delete/jpa/{folioId}";

    public static final String ENDPOINT_GET_ALL_DOCKER = "http://usuario-getall:8081/usuarioAPI";
    public static final String ENDPOINT_GET_BY_FOLIO_DOCKER = "http://usuario-getbyfolio:8082/usuarioAPI/{folioId}";
    public static final String ENDPOINT_POST_DOCKER = "http://usuario-post:8083/usuarioAPI/post";
    public static final String ENDPOINT_PUT_DOCKER = "http://usuario-put:8084/usuarioAPI/put";
    public static final String ENDPOINT_DELETE_DOCKER = "http://usuario-delete:8085/usuarioAPI/delete/{folioId}";

    public static final String ENDPOINT_GET_ALL_JPA_DOCKER = "http://usuario-getall:8081/usuarioAPI/jpa";
    public static final String ENDPOINT_GET_BY_FOLIO_JPA_DOCKER = "http://usuario-getbyfolio:8082/usuarioAPI/jpa/{folioId}";
    public static final String ENDPOINT_POST_JPA_DOCKER = "http://usuario-post:8083/usuarioAPI/jpa/post";
    public static final String ENDPOINT_PUT_JPA_DOCKER = "http://usuario-put:8084/usuarioAPI/jpa/put";
    public static final String ENDPOINT_DELETE_JPA_DOCKER = "http://usuario-delete:8085/usuarioAPI/delete/jpa/{folioId}";

}
