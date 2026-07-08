package com.matias.financas_api.controller;

import com.matias.financas_api.model.Categoria;
import com.matias.financas_api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Diz ao spring q essa classe responde a requisicoes web
@RequestMapping("/categorias") // qual é a url base desse controller
@CrossOrigin(origins = "*") // MUITO IMPORTANTE: permite q o arquivo html consiga acessar essa api


public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    // rota para o front buscar as categorias
    @GetMapping
    public List<Categoria> listarTodas(){
        return repository.findAll();
    }

}
