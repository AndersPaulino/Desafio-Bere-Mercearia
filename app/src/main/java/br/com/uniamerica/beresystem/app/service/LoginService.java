package br.com.uniamerica.beresystem.app.service;

import br.com.uniamerica.beresystem.app.dto.LoginDTO;
import br.com.uniamerica.beresystem.app.dto.UserDTO;
import br.com.uniamerica.beresystem.app.entity.User;
import br.com.uniamerica.beresystem.app.repository.LoginRepository;
import br.com.uniamerica.beresystem.app.security.JwtServiceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private JwtServiceGenerator jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDTO logar(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );
        User user = loginRepository.findByUsername(loginDTO.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return  toUserDTO(user, jwtToken);
    }

    private UserDTO toUserDTO(User user, String token){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setToken(token);
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
}
