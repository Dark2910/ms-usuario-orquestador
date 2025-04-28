package com.eespindola.orquestador.utils;

public class Constantes {

    public static final String NOT_NULL = "No puede dejar el campo como nulo";
    public static final String NOT_EMPTY = "El conjunto de datos no puede esta vacio";
    public static final String NOT_BLANK = "No se puede dejar el campo en blanco, como espacio vacio o nulo";

    public static final String ERROR_400 = "Bad Request, por favor verifica tu solicitud";



    public static final String ENDPOINT_GET_ALL = "http://localhost:8081/usuarioAPI";
    public static final String ENDPOINT_GET_BY_FOLIO = "http://localhost:8082/usuarioAPI/{folioId}";
    public static final String ENDPOINT_POST = "http://localhost:8083/usuarioAPI/post";
    public static final String ENDPOINT_PUT = "http://localhost:8084/usuarioAPI/put";
    public static final String ENDPOINT_DELETE = "http://localhost:8085/usuarioAPI/delete/{folioId}";

}
