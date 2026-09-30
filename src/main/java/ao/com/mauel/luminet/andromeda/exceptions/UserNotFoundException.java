package ao.com.mauel.luminet.andromeda.exceptions;

import java.time.LocalDate;

public class UserNotFoundException extends RuntimeException{

    String message;
    String status;

    public UserNotFoundException(){
        super("Erro de usuario não encontrado");
    }

    public UserNotFoundException(String messsage, String status){
        this.message = messsage;
        this.status = status;
    }

}
