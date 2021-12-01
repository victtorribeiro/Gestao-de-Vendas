package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria){
        return new ResponseEntity<Categoria>(categoriaService.salvar(categoria), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Categoria>> getAllCategoria(){
        return new ResponseEntity<List<Categoria>>(categoriaService.getAllCategoria(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id){
        Optional<Categoria> optionalCategoria = categoriaService.getById(id);
        return optionalCategoria.isPresent() ? new ResponseEntity<Categoria>(optionalCategoria.get(), HttpStatus.OK) :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria){
        categoria.setId(id);
        return new ResponseEntity<Categoria>(categoriaService.atualizar(categoria), HttpStatus.OK);
    }
}