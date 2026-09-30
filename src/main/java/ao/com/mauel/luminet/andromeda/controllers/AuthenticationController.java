package ao.com.mauel.luminet.andromeda.controllers;

import ao.com.mauel.luminet.andromeda.exceptions.UserNotFoundException;
import ao.com.mauel.luminet.andromeda.repository.UserRepository;
import ao.com.mauel.luminet.andromeda.servisses.TokenService;
import ao.com.mauel.luminet.andromeda.servisses.UserServisse;
import ao.com.mauel.luminet.andromeda.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServisse userServisse;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Autowired private PasswordEncoder passwordEncoder;

    List<String> produtos = new ArrayList<>();
    List<String> frutas = new ArrayList<>();

    @GetMapping("/produtos")
    public List<String> produtos() {
        return List.of("Maça", "Pera", "Manga", "Banana", "Uva");
    }

    @GetMapping("/frutas")
    public List<String> frutas(){
        return List.of("Futa Pinha", "Abacaxi", "Tomate", "Cenoura");
    }

   @GetMapping("/usuarios")
   public List<User> usuarios(){
        return List.of((User) this.userServisse.usuarios());
   }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        System.out.println("LOGIN DO USUÁRIO: " + data.login() + " | " + data.password());
        try {
            System.out.println("Entrou no login");
            var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            System.out.println(data.login() + " + " + data.password());
            var auth = this.authenticationManager.authenticate(userNamePassword);
            User user = (User) auth.getPrincipal();
            System.out.println("Autenticou com sucesso!");
            var token = tokenService.generateToken(user);
            System.out.println("TOKEN NA CONTROLLER: " + token);
            return ResponseEntity.ok(new LoginTokenDTO(token));
        }catch (AuthenticationException e){
            throw  new UserNotFoundException();
        }
    }

    //TODO COLOCR QUI  ANOTAÇÃO @VALID
    @PostMapping("/register") //Retorna ResponseEntity
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO){

        System.out.println("Entrou no register");

        if ((this.userRepository.findByLogin(registerDTO.numero())!=null)) return ResponseEntity.badRequest().build();
        System.out.println("\bLogin: " + registerDTO.numero()+"\n\bPasword: "+registerDTO.password());

        String encryptPassword = passwordEncoder.encode(registerDTO.password());

        User newUser = new User(registerDTO.nome(), registerDTO.numero(), registerDTO.email(), registerDTO.dataNascimento(), registerDTO.genero(),
                registerDTO.numero(), encryptPassword, UserRoles.USER_NORMAL.getRole());

        //String encryptPassword = new BCryptPasswordEncoder().encode(registerDTO.password()); Mais vale o Spring gerenciar a dependência

        System.out.println("\bLogin: " + registerDTO.numero()+"\n\bPasword: "+encryptPassword+"\n\bRole: "+newUser.getRolle());
        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();

    }

}
