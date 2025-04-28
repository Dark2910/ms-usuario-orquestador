package com.eespindola.orquestador.interceptorAOP;


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

    @Pointcut("execution(* com.EEspindola.orquestador.services.imp.*.*(..))")
    public void servicePointCut(){}

    @Before("servicePointCut()")
    public void preHandle(JoinPoint joinPoint) {
        System.out.println("preHandle method: " + joinPoint.getSignature().getName());
    }

    @Around("servicePointCut()")
    public Object postHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Antes de ejecutar el método (postHandle)");
        Object result = joinPoint.proceed();
        System.out.println("Después de ejecutar el método (postHandle)");
        return result;
    }

    @After("servicePointCut()")
    public void postHandle(JoinPoint joinPoint){
        System.out.println("postHandle method: " + joinPoint.getSignature().getName());
    }

}
