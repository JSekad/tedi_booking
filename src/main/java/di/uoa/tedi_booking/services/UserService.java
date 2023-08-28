package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.User;
import di.uoa.tedi_booking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends GenericService<User>{

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
        this.userRepository = repository;
    }

    public List<User> usersMeAitimaEggrafis(){
        return userRepository.usersMeAitimaEggrafis();
    }

}
