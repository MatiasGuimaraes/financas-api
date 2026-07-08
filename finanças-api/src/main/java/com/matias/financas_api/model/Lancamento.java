package com.matias.financas_api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.UUID;

@Entity
@Table(name = "lancamentos")
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_Id")
    private UUID usuarioId;

    // NOME CORRIGIDO AQUI:
    @ManyToOne
    @JoinColumn(name = "categoria_Id")
    private Categoria categoria;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false, length = 20)
    private String tipo; //receita, despesa, investimento

    @Column(nullable = false, precision =  10, scale = 2)
    private BigDecimal valor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name ="data_competencia", nullable = false)
    private LocalDate dataCompetencia;

    @Column(name = "numero_parcela")
    private Integer numeroParcela;

    @Column(name = "is_fixo")
    private Boolean isFixo;

    @Column(name = "criado_em", insertable = false, updatable = false)
    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "compra_parcelada_id")
    private CompraParcelada compraParcelada;

    // Construtor vazio exigido pelo JPA
    public Lancamento() {}

    // ── GETTERS E SETTERS ──

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDate getDataCompetencia() { return dataCompetencia; }
    public void setDataCompetencia(LocalDate dataCompetencia) { this.dataCompetencia = dataCompetencia; }

    public Integer getNumeroParcela() { return numeroParcela; }
    public void setNumeroParcela(Integer numeroParcela) { this.numeroParcela = numeroParcela; }

    public Boolean getIsFixo() { return isFixo; }
    public void setIsFixo(Boolean isFixo) { this.isFixo = isFixo; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public CompraParcelada getCompraParcelada(){ return compraParcelada; }
    public void setCompraParcelada(CompraParcelada compraParcelada){ this.compraParcelada = compraParcelada; }
}