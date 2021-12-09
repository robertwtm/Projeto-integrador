package com.generation.AppProjetoIntegrador.controller;

import com.generation.AppProjetoIntegrador.model.Postagem;
import com.generation.AppProjetoIntegrador.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.generation.AppProjetoIntegrador.model.Tema;

import java.util.List;

@RestController
@RequestMapping("/tema")
@CrossOrigin("*")
public class TemaController {

    @Autowired
    private TemaRepository repository;

    @GetMapping
    public ResponseEntity<List<Tema>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Tema> findById(@PathVariable Long id){
            return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/materia/{materia}")
    public ResponseEntity<List<Tema>> findByMateria(@PathVariable String materia){
        return ResponseEntity.ok(repository.findAllByMateriaContainingIgnoreCase(materia));
    }
    
    @PutMapping
    public ResponseEntity putTema(@RequestBody Tema tema){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
    }
    
    @PostMapping
    public ResponseEntity postTema(@RequestBody Tema tema){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
    }
    
    @DeleteMapping("delete/{id}")
    public void deleteTema(@PathVariable Long id){
        repository.deleteById(id);
    }
    
    

}
