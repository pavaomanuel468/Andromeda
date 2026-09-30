package ao.com.mauel.luminet.andromeda.servisses;

import ao.com.mauel.luminet.andromeda.repository.UserRepository;
import ao.com.mauel.luminet.andromeda.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServisse {

    @Autowired
    private UserRepository userRepository;

    public List<User> usuarios(){

        return userRepository.findAll();

    }

}
