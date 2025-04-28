package com.eespindola.orquestador.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Random;

public class FolioRequest {

    public static String CrearFolioRequest() {
        Random random = new Random();

        String[] numeros = new String[20];
        //StringBuilder numeroAleatorio = new StringBuilder();

        for (int i = 0; i < numeros.length; i++) {
            int numero = random.nextInt(0, 9);
            numeros[i] = Integer.toString(numero);
            //numeroAleatorio.append(numero);
        }

        String numeroAleatorio = Arrays.stream(numeros).reduce("", String::concat);

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String text = date.format(formatter);
        LocalDateTime parsedDate = LocalDateTime.parse(text, formatter);

        return (numeroAleatorio + "-" + parsedDate);
    }

}
