package com.vucko.generic_crud.controller;



import com.vucko.generic_crud.service.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class GenericController<T,DTO, ID> {
    private final GenericService<T,DTO, ID> service;
    protected GenericController(GenericService<T,DTO,ID> service){
        this.service=service;
    }

    @GetMapping("")
    public Iterable<DTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> findById(@PathVariable ID id) {
        DTO dto = service.findById(id);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@RequestBody final DTO dto, @PathVariable("id") final ID id) {
        if (service.existsById(id)) {
            service.update(dto, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody T t){
        T createdEntity = service.save(t);
        return new ResponseEntity<>("Entitet je kreiran!",HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ID id){
        if(!service.existsById(id)){
            return new ResponseEntity<>("Trazeni entitet nije pronadjen!",HttpStatus.NOT_FOUND);
        }
        service.remove(id);
        return new ResponseEntity<>("Entitet uspesno obrisan!",HttpStatus.OK);
    }
}
