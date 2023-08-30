package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.User;
import di.uoa.tedi_booking.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends GenericService<User>{

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
        this.userRepository = repository;
    }

    @Transactional
    public List<User> usersMeAitimaEggrafis(){
        List<User> users = userRepository.usersMeAitimaEggrafis();
        users.forEach(user -> Hibernate.initialize(user.getRoles()));
        return users;
    }

}
