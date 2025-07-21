package com.eespindola.orquestador.interceptorAOP;


import com.eespindola.orquestador.exceptions.InvalidArgument;
import com.eespindola.orquestador.models.Usuario;
import com.eespindola.orquestador.models.dto.Result;
import com.eespindola.orquestador.utils.FolioRequest;
import com.eespindola.orquestador.utils.InputValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class MyInterceptorAop {

    /***
     * Los interceptores AOP son ideales para lógica transversal,
     * como logging, transacciones, manejo de excepciones y seguridad,
     * en una forma modular, que se puede aplicar a varios métodos o clases.
     */

    @Pointcut("@annotation(com.eespindola.orquestador.annotations.BeforeAOP)")
    public void before() {
    }

    @Pointcut("@annotation(com.eespindola.orquestador.annotations.AroundAOP)")
    public void around() {
    }

    @Pointcut("@annotation(com.eespindola.orquestador.annotations.AfterAOP)")
    public void after() {
    }

    private final InputValidator validator;

    @Autowired
    public MyInterceptorAop(
            InputValidator inputValidator
    ) {
        this.validator = inputValidator;
    }

    @Before("before()")
    public void preHandle(JoinPoint joinPoint) throws InvalidArgument {
        System.out.println("preHandle method: " + joinPoint.getSignature().getName());
    }

    @Around("around()")
    public Object aroundHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("aroundHandle method: " + joinPoint.getSignature().getName());

        boolean hasUsuario = false;
        List<Usuario> usuarios =  new ArrayList<>();

        Object [] args = joinPoint.getArgs();
        for(Object arg : args){
            if (arg instanceof Result<?>) {
                ((Result<?>) arg).setFolioRequest(FolioRequest.getFolio());
                hasUsuario = userExists((Result<?>) arg, usuarios);
            }
        }
        if(hasUsuario){
            validarUsuario(usuarios);
        }

        System.out.println("Antes de ejecutar el método interceptado");
        Object object = joinPoint.proceed(args);
        System.out.println("Después de ejecutar el método interceptado");
        return object;
    }

    @After("after()")
    public void afterHandle(JoinPoint joinPoint) {
        System.out.println("afterHandle method: " + joinPoint.getSignature().getName());
    }

    private boolean userExists(Result<?> result, List<Usuario> usuarios) {
        boolean hasUsuario = false;

        if (result.getObjects() != null && !result.getObjects().isEmpty()) {
            for(Object obj : result.getObjects()){
                if(obj instanceof Usuario){
                    usuarios.add((Usuario)obj);
                    hasUsuario = true;
                }
            }
        }

        if (result.getObject() != null) {
            if((result.getObject() instanceof Usuario)){
                usuarios.add((Usuario) result.getObject());
                hasUsuario = true;
            }
        }

        if(usuarios.isEmpty()){
            System.out.println("No hay usuarios dentro del la parametria");
        } else {
            System.out.println("Usuarios encontrados: " + usuarios);
        }

        return hasUsuario;
    }

    private void validarUsuario(List<Usuario> usuarios) throws InvalidArgument {
        String objName = Usuario.class.getName();
        for(Usuario usuario : usuarios){
            validator.bindingResult(usuario, objName);
        }
    }

}
