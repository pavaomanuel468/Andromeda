package ao.com.mauel.luminet.andromeda.users;

public class UserErrorDTO{

    String message;
    String status;
    public UserErrorDTO(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
