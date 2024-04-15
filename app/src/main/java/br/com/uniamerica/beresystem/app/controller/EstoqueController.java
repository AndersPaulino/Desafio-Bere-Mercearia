package br.com.uniamerica.beresystem.app.controller;

import br.com.uniamerica.beresystem.app.dto.EstoqueDTO;
import br.com.uniamerica.beresystem.app.entity.Estoque;
import br.com.uniamerica.beresystem.app.service.EstoqueService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {
    private EstoqueService estoqueService;

    @Autowired
    public EstoqueController(EstoqueService estoqueService){
        this.estoqueService = estoqueService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDTO> findById(@PathVariable Long id) {
        return estoqueService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EstoqueDTO>> findAll() {
        List<EstoqueDTO> estoqueDTO = estoqueService.findAll();
        return ResponseEntity.ok(estoqueDTO);
    }
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Estoque estoque) {
        try {
            estoqueService.cadastrar(estoque);
            return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/nome/{id}")
    public ResponseEntity<String> atualizar(@PathVariable @NotNull Long id, @RequestBody Estoque estoque) {
        try {
            estoqueService.atualizar(id, estoque);
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
            estoqueService.desativar(id);
            return ResponseEntity.ok().body("Registro desativado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao desativar o registro.");
        }
    }
}
