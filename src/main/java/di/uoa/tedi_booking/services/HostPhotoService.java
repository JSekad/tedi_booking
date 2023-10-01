package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.entities.HostPhoto;
import di.uoa.tedi_booking.repositories.HostPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostPhotoService extends GenericService<HostPhoto>{

    private final HostPhotoRepository hostPhotoRepository;

    @Autowired
    public HostPhotoService(HostPhotoRepository repository){
        super(repository);
        this.hostPhotoRepository = repository;
    }
}
