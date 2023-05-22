package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.Role;
import di.uoa.tedi_booking.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends GenericService<Role>{

    @Autowired
    public RoleService(RoleRepository roleRepository){ super(roleRepository); }
}
