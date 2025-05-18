package com.eespindola.orquestador.interceptorAOP;


import com.eespindola.orquestador.models.Usuario;
import com.eespindola.orquestador.models.dto.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyInterceptorAop {

    /***
     * Los interceptores AOP son ideales para lógica transversal,
     * como logging, transacciones, manejo de excepciones y seguridad,
     * en una forma modular, que se puede aplicar a varios métodos o clases.
     */

    @Pointcut("@annotation(com.eespindola.orquestador.annotations.BeforeAOP)")
    public void before(){}

    @Pointcut("@annotation(com.eespindola.orquestador.annotations.AroundAOP)")
    public void around(){}

    @Pointcut("@annotation(com.eespindola.orquestador.annotations.AfterAOP)")
    public void after(){}


    @Before("before()")
    public void preHandle(JoinPoint joinPoint) {
        System.out.println("preHandle method: " + joinPoint.getSignature().getName());
    }

    @Around("around()")
    public Object postHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Antes de ejecutar el método interceptado");
        /*
        El metodo joinPoint.proceed() dentro de un interceptor @Around es clave para entender cómo funciona el flujo de ejecución
        joinPoint.proceed() es la instrucción que ejecuta el metodo objetivo (es decir, el metodo real)
        */
        Object object = joinPoint.proceed();
        System.out.println("Después de ejecutar el método interceptado");
        return object;
    }

    @After("after()")
    public void afterHandle(JoinPoint joinPoint){
        System.out.println("postHandle method: " + joinPoint.getSignature().getName());
    }

}
