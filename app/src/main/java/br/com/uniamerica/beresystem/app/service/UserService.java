package br.com.uniamerica.beresystem.app.service;

import br.com.uniamerica.beresystem.app.entity.User;
import br.com.uniamerica.beresystem.app.repository.UserRepository;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void cadastrarUsuario(User user){
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        User newUser = new User();
        newUser.setPassword(encryptedPassword);
        newUser.setRole(user.getRole());
        newUser.setUsername(user.getUsername());
        userRepository.save(newUser);
    }
}
