package br.com.uniamerica.beresystem.app.controller;

import br.com.uniamerica.beresystem.app.dto.ProdutoDTO;
import br.com.uniamerica.beresystem.app.entity.Produto;
import br.com.uniamerica.beresystem.app.service.ProdutoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdutoController {
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        return produtoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<ProdutoDTO> produtoDTO = produtoService.findAll();
        return ResponseEntity.ok(produtoDTO);
    }
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Produto produto) {
        try {
            produtoService.cadastrar(produto);
            return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/nome/{id}")
    public ResponseEntity<String> atualizar(@PathVariable @NotNull Long id, @RequestBody Produto produto) {
        try {
            produtoService.atualizar(id, produto);
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
            produtoService.desativar(id);
            return ResponseEntity.ok().body("Registro desativado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao desativar o registro");
        }
    }
}
