package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.repositories.GenericRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class GenericController<T> {

    private final GenericRepository<T> repository;

    GenericController(GenericRepository<T> repository) { this.repository = repository; }

    @GetMapping(path = "/all")
    public @ResponseBody List<T> getAll() {
        return repository.findAll();
    }

    @GetMapping(path="{id}")
    public @ResponseBody T find(@PathVariable Long id){
        Optional<T> o = repository.findById(id);
        return o.orElse(null);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> add(@RequestBody T t){
        repository.save(t);
        return ResponseEntity.status(HttpStatus.OK).body("New entry saved successfully");
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(@RequestBody T t){
        repository.save(t);
        return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
    }

    @DeleteMapping(path = "/remove/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.findById(id).ifPresent(repository::delete);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

}
