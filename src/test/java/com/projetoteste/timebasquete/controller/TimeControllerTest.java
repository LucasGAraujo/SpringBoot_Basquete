package com.projetoteste.timebasquete.controller;

import com.projetoteste.timebasquete.models.JogadorBasquete;
import com.projetoteste.timebasquete.models.Time;
import com.projetoteste.timebasquete.service.JogadorService;
import com.projetoteste.timebasquete.service.ServiceTest;
import com.projetoteste.timebasquete.service.TimeService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class TimeControllerTest {
    @Autowired
    TimeService timeService;
    @Autowired
    JogadorService jservice;
    @Autowired
    ServiceTest service;
    @Test
    @DisplayName("Deve retornar todos os Times")
    void DeveRetornarTodosOsTimes(){
        List<Time> time =  service.getAllTimes();
        assertEquals(6, time.size());
    }
    @Test
    @DisplayName("Deve retornar um Time pelo ID")
    void deveRetornarUmTimePeloId(){
        Time time = service.getByIdT(1L);
        assertEquals(time.getNomeTime(), "Los Angeles Lakers");
        assertEquals(time.getId(), 1L);
    }
    @Test
    @DisplayName("Deve testar update")
    void deveTestarUpdatedeTime() {
        assertNotNull(timeService);
        Time time = timeService.getById(1L);
        time.setNomeTime("Lucas Organization Basquete");
        Time timeupdate = timeService.save(time);
        assertEquals(timeupdate.getNomeTime(), "Lucas Organization Basquete");
    }
    @Test
    @DisplayName("Deve testar criacao")
    void deveTestarCriacaonaAplicacao() {
        assertNotNull(timeService);
        Time time = Time.builder()
                .nomeTime("Lucas Gomes Basquete")
                .build();
        timeService.save(time);
        assertEquals("Lucas Gomes Basquete",time.getNomeTime());
    }
}
