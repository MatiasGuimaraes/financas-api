package com.matias.financas_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias") // o hibernate vai criar esta tabela no banco de dados

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String nome;

    private String tipo;

    //costrutor vazio obrigatorio para o spring
    public Categoria(){}

    //getters e setters
    public  Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
       return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
