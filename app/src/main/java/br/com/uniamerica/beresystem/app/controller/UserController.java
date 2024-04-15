package br.com.uniamerica.beresystem.app.controller;

import br.com.uniamerica.beresystem.app.dto.MensagemDTO;
import br.com.uniamerica.beresystem.app.entity.User;
import br.com.uniamerica.beresystem.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<MensagemDTO> CadastrarNovoUsuario(@RequestBody User user){
        userService.cadastrarUsuario(user);
        MensagemDTO mensagemDTO = new MensagemDTO("Usuário cadastrado com sucesso!");
        return new ResponseEntity<>(mensagemDTO, HttpStatus.CREATED);
    }
}
