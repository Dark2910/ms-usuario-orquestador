package com.eespindola.orquestador.controller;

import com.eespindola.orquestador.dto.Result;
import com.eespindola.orquestador.models.Usuario;
import com.eespindola.orquestador.services.OrquestadorService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Ejecutar todos los test en una sola instancia
@WebMvcTest(controllers = Orquestador.class)
public class OrquestadorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrquestadorService orquestadorService;


//    @BeforeAll
//    public static void beforeAll(){
//        System.out.println("****** Annotation BeforeAll");
//    }
//
//    @AfterAll
//    public static void afterAll(){
//        System.out.println("****** Annotation AfterAll");
//    }
//
//    @BeforeEach
//    public void beforeEach(){
//        System.out.println("****** Annotation BeforeEach");
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @AfterEach
//    public void AfterEach(){
//        System.out.println("****** Annotation AfterEach");
//    }

//    test<SystemUnderTest>_<ConditionOrStateChange>_<ExpectedResult>

    @Test
    @DisplayName("TestOrquestador-GetAll")
    public void testOrquestadorGetAll_CuantoTodosLosParametrosSonLosEsperados_EsperamosObtenerUnaListaDeUsuarios() throws Exception {
        //Arrange - Given
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setUsername("Pau");
        usuario.setPassword("qwerty");
        usuario.setNombre("Paulina");
        usuario.setApellidoPaterno("Ayala");
        usuario.setApellidoMaterno("Parra");
        usuario.setEmail("Paulina@hotmail.com");
        usuario.setFolio("lkjghaibvrairb");
        usuario.setFechaNacimiento("2000-12-12");
        usuario.setStatus("1");

        Result<Usuario> result = new Result<>();
        result.setIsCorrect(true);
        result.setObjects(List.of(usuario));

        when(orquestadorService.GetAll(any(HttpSession.class))).thenReturn(result);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orquestador")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
//                .content(new ObjectMapper().writeValueAsString(result));

        //Act - When
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseContent = mvcResult.getResponse().getContentAsString();
        Result<Usuario> responseResult = new ObjectMapper().readValue(responseContent, new TypeReference<Result<Usuario>>() {});

        //Assert - Then
        Assertions.assertTrue(responseResult.getIsCorrect());
        Assertions.assertEquals(result.getObjects().get(0), responseResult.getObjects().get(0));
    }

    @Test
    @DisplayName("TestOrquestador-POST-Error400")
    public void testOrquestadorGetAll_Fail() throws Exception {
        //Arrange - Given
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setUsername("");
        usuario.setPassword("qwerty");
        usuario.setNombre("Paulina");
        usuario.setApellidoPaterno("Ayala");
        usuario.setApellidoMaterno("Parra");
        usuario.setEmail("Paulina@hotmail.com");
        usuario.setFolio("lkjghaibvrairb");
        usuario.setFechaNacimiento("2000-12-12");
        usuario.setStatus("1");

        Result<Usuario> result = new Result<>();
        result.setIsCorrect(true);
        result.setObjects(List.of(usuario));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orquestador/post")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        //Act - When
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        //Assert - Then
        Assertions.assertEquals(
                HttpStatus.BAD_REQUEST.value(),
                mvcResult.getResponse().getStatus(),
                () -> "Incorrect Status code"
        );

    }

}
