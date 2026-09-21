package ao.com.mauel.luminet.andromeda.infra.security;

import ao.com.mauel.luminet.andromeda.repository.UserRepository;
import ao.com.mauel.luminet.andromeda.servisses.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        System.out.println("TOKEN RECEBIDO: " + token);
        if(token != null){
            var login = tokenService.validateToken(token);
            System.out.println("LOGIN do token: " + login);
            if (login != null){
                UserDetails user = userRepository.findByLogin(login);
                System.out.println("USUÁRIO: " + user.getUsername());
                System.out.println("AUTHORITIES: " + user.getAuthorities());
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                System.out.println("LOGIN NULL");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
