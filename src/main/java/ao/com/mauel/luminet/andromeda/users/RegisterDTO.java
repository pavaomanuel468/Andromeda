package ao.com.mauel.luminet.andromeda.users;

import java.time.LocalDate;

public record RegisterDTO(String nome, String numero, String email, LocalDate dataNascimento, String genero, String password) {
}
