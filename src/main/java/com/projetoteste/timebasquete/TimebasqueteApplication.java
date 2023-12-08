package com.projetoteste.timebasquete;

import com.projetoteste.timebasquete.Util.ExternaApiUtil;
import com.projetoteste.timebasquete.controller.TimeController;
import com.projetoteste.timebasquete.models.Dolar;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class TimebasqueteApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(TimebasqueteApplication.class);
	public static final LocalDateTime currentTime = LocalDateTime.now();
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) {
		SpringApplication.run(TimebasqueteApplication.class, args);
	}

	@PostConstruct
	public void DolarExterno() {
		ExternaApiUtil api = new ExternaApiUtil();
		Dolar dolar = api.getDolar();
		Double valorDolarParaReal = dolar.getRates().get("BRL");
		LOGGER.info("[CONSUMO API EXTERNA---------------[DOLAR PARA REAL]---------------] - - -- - - - - - - - Valor do dólar para o Real: " + valorDolarParaReal +"   "+ currentTime.format(formatter));
	}

}
