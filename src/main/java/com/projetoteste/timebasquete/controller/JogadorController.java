package com.projetoteste.timebasquete.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.projetoteste.timebasquete.models.JogadorBasquete;
import com.projetoteste.timebasquete.service.JogadorService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/jogadorBasquete")
public class JogadorController {
     private static final Logger LOGGER = LoggerFactory.getLogger(JogadorController.class);
    public static final LocalDateTime data = LocalDateTime.now();
    public static final DateTimeFormatter formatacaodeData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private JogadorService service;

    @GetMapping
    public ResponseEntity<List<JogadorBasquete>> GetAll() {
        try {
            LOGGER.info("Iniciando processo para pegar todos os jogadores  -" + data.format(formatacaodeData));
            List<JogadorBasquete> result = service.findAll();

            if (result != null && !result.isEmpty()) {
                LOGGER.info("Lista de jogadores recuperada com sucesso. " + data.format(formatacaodeData));
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                LOGGER.info(HttpStatus.NOT_FOUND + "A lista de jogadores está vazia ou não pôde ser recuperada. " + data.format(formatacaodeData));
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro ao buscar todos os jogadores: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<JogadorBasquete> GetById(@PathVariable Long id) {
        try {
            LOGGER.info("Iniciando processo para pegar jogadores pelo id: " + id + " "+ data.format(formatacaodeData));
            JogadorBasquete result= service.findById(id).get();
            if(result != null) {
                LOGGER.info("Jogador pego pelo id " + id + " Retornou o resultado: " + result  +" "+ data.format(formatacaodeData));
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                LOGGER.info(HttpStatus.NOT_FOUND + " Não foi possível pegar o jogador do id " + id + data.format(formatacaodeData));
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro ao buscar o jogador por ID: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<JogadorBasquete>> GetByNomeJogador(@RequestParam String nome) {
        try {
            List<JogadorBasquete> result = service.findByNome(nome);

            if (!result.isEmpty()) {
                LOGGER.info("Jogadores encontrados para o nome '" + nome + "': " + result.size() + " "+ data.format(formatacaodeData));
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                LOGGER.info("Nenhum jogador encontrado para o nome '" + nome + "'" + data.format(formatacaodeData));
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro ao pesquisar jogadores pelo nome: " + e.getMessage() + " "+ data.format(formatacaodeData));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pesquisarPosicao")
    public ResponseEntity<List<JogadorBasquete>> GetByPosicaoJogador(@RequestParam String posicao) {
        try {
            List<JogadorBasquete> result = service.findByPosicao(posicao);

            if (!result.isEmpty()) {
                LOGGER.info("Jogadores encontrados para a Posição '" + posicao + "': " + result.size() + " "+ data.format(formatacaodeData));
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                LOGGER.info("Nenhum jogador encontrado para a Posição '" + posicao + "'" + data.format(formatacaodeData));
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro ao pesquisar jogadores pela posição: " + e.getMessage()+ " " + data.format(formatacaodeData));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<JogadorBasquete> PostJogador(@RequestBody JogadorBasquete jogadorBasquete) {
        try {
            LOGGER.info("Adicionando jogador em: " + data.format(formatacaodeData));
            JogadorBasquete result = service.save(jogadorBasquete);

            if (result != null) {
                LOGGER.info("Jogador adicionado com sucesso  - Detalhes: "+ result + " "+ data.format(formatacaodeData));
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            } else {
                LOGGER.info(HttpStatus.NOT_FOUND + " Não foi possível adicionar o jogador: " + " "+ data.format(formatacaodeData));
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro ao adicionar o jogador: " + e.getMessage() + " "+ data.format(formatacaodeData));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorBasquete> UpdateJogador(@PathVariable Long id, @RequestBody JogadorBasquete jogadorBasquete) {
        try {
            LOGGER.info("Editando jogador " + " "+ data.format(formatacaodeData));
            JogadorBasquete result = service.findById(id).get();

            if (result != null) {
                result.setMediadePontos(jogadorBasquete.getMediadePontos());
                result.setPosicao(jogadorBasquete.getPosicao());
                result.setNome(jogadorBasquete.getNome());
                result.setTime(jogadorBasquete.getTime());
                JogadorBasquete updatedJogador = service.save(result);
                LOGGER.info("Jogador editado com sucesso em: " + " "+ data.format(formatacaodeData));
                return new ResponseEntity<>(updatedJogador, HttpStatus.OK);
            } else {
                LOGGER.info("Error na aplicação: " + HttpStatus.NOT_FOUND + " "+ data.format(formatacaodeData));
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro ao editar o jogador: " + e.getMessage() + " "+ data.format(formatacaodeData));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JogadorBasquete> deletebyid(@PathVariable Long id){
           LOGGER.info("Deletando jogador " + " "+ data.format(formatacaodeData));
        try {
            service.deleteById(id);
                LOGGER.info(" jogador deletado com sucesso "+ " "+ data.format(formatacaodeData));

            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception ex) {
                LOGGER.info("Error na aplicação : "+ HttpStatus.NOT_FOUND + " "+ data.format(formatacaodeData));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}