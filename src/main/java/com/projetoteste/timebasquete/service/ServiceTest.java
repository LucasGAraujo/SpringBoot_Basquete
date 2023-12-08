package com.projetoteste.timebasquete.service;

import com.projetoteste.timebasquete.models.JogadorBasquete;
import com.projetoteste.timebasquete.models.Time;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ServiceTest {
    public List<JogadorBasquete> jogadores;
    @Autowired
    JogadorService service;
    @Autowired

    TimeService TService;


    public List<JogadorBasquete> getAll() {
        return service.findAll();
    }

    public JogadorBasquete getById(Long id) {
        Optional<JogadorBasquete> optionalJogador = service.findById(id);
        if (optionalJogador != null) {
            return optionalJogador.get();
        } else {

            return null;
        }
    }
    public JogadorBasquete getByName(String nome) {
        List<JogadorBasquete> jogadores = service.findByNome(nome);
        return jogadores.get(0);
    }

    public JogadorBasquete getByPosicao(String posicao) {
        List<JogadorBasquete> jogadores = service.findByPosicao(posicao);
        return jogadores.get(0);
    }
    public List<Time> getAllTimes() {
        return TService.findAll();
    }

    public Time getByIdT(long l) {
        Optional<Time> optionaltime = TService.findById(l);
        if (optionaltime != null) {
            return optionaltime.get();
        } else {
            return null;
        }
    }
}
