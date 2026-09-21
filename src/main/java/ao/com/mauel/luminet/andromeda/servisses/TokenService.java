package ao.com.mauel.luminet.andromeda.servisses;

import ao.com.mauel.luminet.andromeda.users.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("${secretkey}")
    private String SECRETKEY;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRETKEY);
            String token = JWT.create()
                    .withIssuer("manuel-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(this.genExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token", exception);
        }
    }
    //TODO 1:03:41
    public String validateToken(String token){

        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRETKEY);
            return JWT.require(algorithm)
                    .withIssuer("manuel-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException exception){
            exception.printStackTrace();
            return "";
        }

    }

    public Instant genExpirationDate(){
        return Instant.now().plus(2, ChronoUnit.HOURS);
    }

}
