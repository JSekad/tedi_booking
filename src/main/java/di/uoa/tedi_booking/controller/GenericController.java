package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.repositories.GenericRepository;
import di.uoa.tedi_booking.services.GenericService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericController<T> {

    private final GenericService<T> service;

    GenericController(GenericRepository<T> repository) { this.service = new GenericService<T>(repository) {}; }

    @GetMapping(path = "/all")
    public @ResponseBody List<T> getAll() {
        return service.findAll();
    }

    @GetMapping(path="{id}")
    public @ResponseBody T find(@PathVariable Long id){
        return service.find(id);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> add(@RequestBody T t){
        return service.add(t);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(@RequestBody T t){
        return service.update(t);
    }

    @DeleteMapping(path = "/removeById/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
