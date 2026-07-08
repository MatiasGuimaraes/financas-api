package com.matias.financas_api.dto;

public class CompraParceladaDTO {
    private String descricao;
    private Double valorTotal;
    private Integer quantidadeParcela;
    private Integer categoriaId;
    private String dataPrimeiraParcela;

    // getters e setters
    public  String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public  Integer getQuantidadeParcela() {
        return quantidadeParcela;
    }
    public void setQuantidadeParcela(Integer quantidadeParcela) {
        this.quantidadeParcela = quantidadeParcela;
    }
    public  Integer getCategoriaId() {
        return categoriaId;
    }
    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }
    public  String getDataPrimeiraParcela() {
        return dataPrimeiraParcela;
    }
    public void setDataPrimeiraParcela(String dataPrimeiraParcela) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
    }
}
