package com.projetoteste.timebasquete.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.projetoteste.timebasquete.models.JogadorBasquete;
import com.projetoteste.timebasquete.models.Time;
import com.projetoteste.timebasquete.service.JogadorService;


import com.projetoteste.timebasquete.service.TimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/time")
public class TimeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeController.class);
    public static final LocalDateTime currentTime = LocalDateTime.now();
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private TimeService service;

    @GetMapping
    public ResponseEntity<List<Time>> GetAll() {
        try {
            LOGGER.info("[Time]---------------Pegando todos os Times do Programa " + currentTime.format(formatter));
            List<Time> result = service.findAll();

            if (result != null && !result.isEmpty()) {
                LOGGER.info("[Time]---------------Lista de Time recuperada com sucesso. " + currentTime.format(formatter));
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                LOGGER.info("[Time]---------------" + HttpStatus.NOT_FOUND + " A lista de times está vazia ou não pôde ser recuperada. " + currentTime.format(formatter));
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("[Time]---------------Ocorreu um erro ao buscar todos os times: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Time> GetById(@PathVariable Long id) {
        try {
            LOGGER.info("[Time]--------------- Pegando time por id " + currentTime.format(formatter));
            Time result = service.findById(id).get();
            if (result != null) {
                LOGGER.info("[Time]---------------time pego por id " + result + currentTime.format(formatter));
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                LOGGER.info("[Time]---------------" + HttpStatus.NOT_FOUND + " Não foi possível pegar o time do id " + id + currentTime.format(formatter));
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("[Time]---------------Ocorreu um erro ao buscar o time por ID: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Time> postTime(@RequestBody Time time) {
        try {
            LOGGER.info("[Time]---------------Adicionando time em: " + currentTime.format(formatter));
            Time result = service.save(time);

            if (result != null) {
                LOGGER.info("[Time]---------------time adicionado com sucesso em: " + currentTime.format(formatter) + " - Detalhes: " + result);
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            } else {
                LOGGER.info("[Time]---------------Não foi possível adicionar o time: " + currentTime.format(formatter));
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            LOGGER.error("[Time]---------------Ocorreu um erro ao adicionar o time: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Time> updateTime(@PathVariable Long id, @RequestBody Time time) {
        try {
            LOGGER.info("[Time]---------------Editando time em: " + currentTime.format(formatter));
            Time result = service.findById(id).get();

            if (result != null) {
                result.setNomeTime(time.getNomeTime());
                Time updatedTime = service.save(result);
                LOGGER.info("[Time]--------------- time editado com sucesso em: " + currentTime.format(formatter));
                return new ResponseEntity<>(updatedTime, HttpStatus.OK);
            } else {
                LOGGER.info("[Time]--------------- Error na aplicação : " + HttpStatus.NOT_FOUND + currentTime.format(formatter));
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("[Time]---------------Ocorreu um erro ao editar o time: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Time> deletebyid(@PathVariable Long id){
        LOGGER.info("[Time]---------------Deletando time em: " + currentTime.format(formatter));
        try {
            service.deleteById(id);
            LOGGER.info(" [Time]---------------time deletado com sucesso em: " + currentTime.format(formatter));

            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.info("[Time]---------------Error na aplicação : "+ HttpStatus.NOT_FOUND + currentTime.format(formatter));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}