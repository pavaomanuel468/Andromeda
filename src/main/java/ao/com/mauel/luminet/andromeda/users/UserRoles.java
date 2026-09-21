package ao.com.mauel.luminet.andromeda.users;

public enum UserRoles {

    ADMIN("ADMIN"),
    USER_NORMAL("USER");

    private final String role;

    private UserRoles(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }

}
