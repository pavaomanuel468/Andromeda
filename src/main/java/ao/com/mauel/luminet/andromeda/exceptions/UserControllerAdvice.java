package ao.com.mauel.luminet.andromeda.exceptions;

import ao.com.mauel.luminet.andromeda.users.UserErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice()
public class UserControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public UserErrorDTO handleUserNotFound(UserNotFoundException userNotFoundException){
        UserErrorDTO userErrorDTO = new UserErrorDTO();
        userErrorDTO.setMessage("LOGIN Inválido: Verifique o Número e a Senha");
        userErrorDTO.setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()));
        return userErrorDTO;
    }

}
