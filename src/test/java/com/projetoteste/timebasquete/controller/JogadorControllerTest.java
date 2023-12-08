package com.projetoteste.timebasquete.controller;
import com.projetoteste.timebasquete.ResourceNotFoundException;
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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Transactional
public class JogadorControllerTest {

    @Autowired
    TimeService timeService;
    @Autowired
    JogadorService jservice;
    @Autowired
    ServiceTest service;
    @Test
    @DisplayName("Deve retornar todos os Jogadorres")
    void deveRetornarTodosOsJogadores(){
        List<JogadorBasquete> jogadores =  service.getAll();
        assertTrue(jogadores.size() > 5);
    }
    @Test
    @DisplayName("Deve retornar um jogador pelo ID")
    void deveRetornarUmJogadorPeloId() {
        JogadorBasquete jogadorBasquete = service.getById(1L);
        assertEquals(jogadorBasquete.getNome(), "Lebron James");
        assertEquals(jogadorBasquete.getId(), 1L);
        assertEquals(jogadorBasquete.getPosicao(), "Ala pivô");
        assertEquals(jogadorBasquete.getMediadePontos(), 29);
       assertThrows(NoSuchElementException.class, ()->{
           service.getById(-1L);
       });
    }

    @Test
    @DisplayName("Deve retornar um jogador pelo Name")
    void deveRetornarUmJogadorPeloName(){
        JogadorBasquete jogadorBasquete = service.getByName("Lebron James");
        assertEquals(jogadorBasquete.getNome(), "Lebron James");
        assertEquals(jogadorBasquete.getId(), 1L);
        assertEquals(jogadorBasquete.getPosicao(), "Ala pivô");
        assertEquals(jogadorBasquete.getMediadePontos(), 29);
    }
    @Test
    @DisplayName("Deve retornar um jogador pelo posicao")
    void deveRetornarUmJogadorPelaPosicao(){
        JogadorBasquete jogadorBasquete = service.getByPosicao("Ala pivô");
        assertEquals(jogadorBasquete.getNome(), "Lebron James");
        assertEquals(jogadorBasquete.getId(), 1L);
        assertEquals(jogadorBasquete.getPosicao(), "Ala pivô");
        assertEquals(jogadorBasquete.getMediadePontos(), 29);
    }
    @Test
    @DisplayName("Deve testar update")
    void deveTestarUpdatenaAplicacao() {
        assertNotNull(jservice);

        JogadorBasquete jogadorBasquete = jservice.getById(1L);
        jogadorBasquete.setPosicao("pivô");
        jogadorBasquete.setMediadePontos(30);
        jogadorBasquete.setNome("Lucas James");
        JogadorBasquete jogadorUpdate = jservice.save(jogadorBasquete);
        assertEquals(jogadorUpdate.getNome(), "Lucas James");
        assertEquals(jogadorUpdate.getPosicao(), "pivô");
        assertEquals(jogadorUpdate.getMediadePontos(), 30);
    }
    @Test
    @DisplayName("Deve testar criacao")
    void deveTestarCriacaonaAplicacao() {
        Optional<Time> timeDenverNuggets = timeService.findById(6L);
        Time timeDenverNuggetss = timeDenverNuggets.get();

        assertNotNull(jservice);
        JogadorBasquete jogadorBasquete = JogadorBasquete.builder()
                .posicao("Pivô")
                .mediadePontos(52)
                .nome("Joao")
                .time(timeDenverNuggetss)
                .build();
                jservice.save(jogadorBasquete);
                assertEquals(52,jogadorBasquete.getMediadePontos());
                assertEquals("Joao",jogadorBasquete.getNome());
                assertEquals("Pivô",jogadorBasquete.getPosicao());
    }
    @Test
    @DisplayName("Deve testar Deleção")
    void deveTestarDeletenaAplicacao() {
        List<JogadorBasquete> jogadorBasquetesInicio = service.getAll();
        jservice.deleteById(1L);
        List<JogadorBasquete> jogadorBasquetes = service.getAll();
        assertTrue(jogadorBasquetes.size() < jogadorBasquetesInicio.size() );
    }



}
