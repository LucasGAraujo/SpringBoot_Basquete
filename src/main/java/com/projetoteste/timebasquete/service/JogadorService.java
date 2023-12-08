package com.projetoteste.timebasquete.service;

import com.projetoteste.timebasquete.models.JogadorBasquete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JogadorService extends JpaRepository<JogadorBasquete, Long> {
    List<JogadorBasquete> findByNome(String nome);

    List<JogadorBasquete> findByPosicao(String posicao);

}
