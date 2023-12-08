package com.projetoteste.timebasquete.service;


import com.projetoteste.timebasquete.models.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeService extends JpaRepository<Time, Long> {

}