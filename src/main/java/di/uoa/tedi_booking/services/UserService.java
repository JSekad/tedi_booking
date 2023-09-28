package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.DTOS.PasswordDTO;
import di.uoa.tedi_booking.entities.User;
import di.uoa.tedi_booking.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public User getUserByUserName(String username) throws Exception {
        User user1 = userRepository.getUserByUserName(username);
        if (user1 != null){
            return user1;
        }else {
            throw new Exception();
        }
    }

    public String updateDetails(User user) throws Exception{
        Optional<User> us = userRepository.findById((long) user.getId());
        if (us.isPresent()){
            User current = us.get();
            current.setName(user.getName());
            current.setSurname(user.getSurname());
            current.setEmail(user.getEmail());
            current.setIdNumber(user.getIdNumber());
            current.setBirthDate(user.getBirthDate());
            current.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(current);
        }
        return "ok";

    }
}
