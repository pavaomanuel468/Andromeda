package ao.com.mauel.luminet.andromeda.users;

import java.time.LocalDate;

public record UserDTO(String nome, String numero, String email, LocalDate dataNascimento, String genero) {
}
