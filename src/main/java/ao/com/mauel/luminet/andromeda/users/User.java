package ao.com.mauel.luminet.andromeda.users;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.script.SimpleScriptContext;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Table(schema = "product", name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    private String numero;

    private String email;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String genero;

    private String login;

    private String pasword;

    private String rolle;

    public User(){}
    public User(String nome, String numero, String email, LocalDate dataNascimento, String genero,
            String login, String encryptPassword, String role){
        this.nome = nome;
        this.numero = numero;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.login = login;
        this.pasword = encryptPassword;
        this.rolle = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (this.rolle.equals(UserRoles.ADMIN.getRole())){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public @Nullable String getPassword() {
        return pasword;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                ", numero='" + numero + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", genero='" + genero + '\'' +
                ", pasword='" + pasword + '\'' +
                ", login='" + login + '\'' +
                ", rolle='" + rolle + '\'' +
                '}';
    }
}
