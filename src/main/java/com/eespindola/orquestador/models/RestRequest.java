package com.eespindola.orquestador.models;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestRequest<T> {

  private String url;
  private HttpMethod method;
  private Object body;
  private HttpHeaders headers;
  // RestTemplate
  private ParameterizedTypeReference<T> parameterizedTypeReference;
  // HttpClient (Jackson)
  private TypeReference<T> typeReference;

  @Builder.Default
  private Object[] uriVariables = new Object[0];

}
