package di.uoa.tedi_booking.services;

import java.util.List;
import java.util.Optional;

import di.uoa.tedi_booking.repositories.GenericRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericService<T> {
    private final GenericRepository<T> repository;

    public GenericService(GenericRepository<T> repository) { this.repository = repository; }

    public List<T> findAll(){
        return repository.findAll();
    }

    public T find(Long id){
        Optional<T> o = repository.findById(id);
        return o.orElse(null);
    }

    @Transactional
    public ResponseEntity<?> add(T t){
        try {
            repository.save(t);
            return ResponseEntity.status(HttpStatus.OK).body("New entry saved successfully");
        }
        catch(IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Persist failed. Objects was null");
        }
    }

    @Transactional
    public ResponseEntity<?> update(T t){
        try{
            repository.save(t);
            return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");

        }
        catch(IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Persist failed. Objects was null");
        }
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) {
        if(!repository.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deleted failed. Object not found");

        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }
}
