package br.com.uniamerica.beresystem.app.controller;

import br.com.uniamerica.beresystem.app.dto.MovimentacaoDTO;
import br.com.uniamerica.beresystem.app.entity.Movimentacao;
import br.com.uniamerica.beresystem.app.service.MovimentacaoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacao")
@CrossOrigin(origins = "http://localhost:4200")
public class MovimentacaoController {
    private final MovimentacaoService movimentacaoService;

    @Autowired
    public MovimentacaoController(MovimentacaoService movimentacaoService) {this.movimentacaoService = movimentacaoService;}

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoDTO> findById(@PathVariable Long id){
        return movimentacaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoDTO>> findAll() {
        List<MovimentacaoDTO> movimentacaoDTO = movimentacaoService.findAll();
        return ResponseEntity.ok(movimentacaoDTO);
    }
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Movimentacao movimentacao) {
        try {
            movimentacaoService.cadastrar(movimentacao);
            return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@PathVariable @NotNull Long id, @RequestBody Movimentacao movimentacao) {
        try {
            movimentacaoService.atualizar(id, movimentacao);
            return ResponseEntity.ok().body("Registro atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o registro.");
        }
    }

    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            movimentacaoService.deletar(id);
            return ResponseEntity.ok().body("Registro desativado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao desativar o registro");
        }
    }
}
