package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.Role;
import di.uoa.tedi_booking.DTOS.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends GenericService<Role>{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository repository){
        super(repository);
        this.roleRepository = repository;
    }
}
