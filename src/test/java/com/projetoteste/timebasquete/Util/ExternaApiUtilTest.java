package com.projetoteste.timebasquete.Util;

import com.projetoteste.timebasquete.models.Dolar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExternaApiUtilTest {

    @Test
    @DisplayName("Verificar se o objeto esta null")
    void VerificarObjeto() {
        Dolar dolar = new Dolar();
        assertEquals(0.0, dolar.getUSD());
    }
    @Test
    @DisplayName("Verificar se deu 200 a aplicação ")
    public void verificarStatus200() {
        try {
            URL url = new URL("https://api.exchangerate-api.com/v4/latest/USD");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            assertEquals(200, responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
}}
