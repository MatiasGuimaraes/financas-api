package com.matias.financas_api.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.matias.financas_api.model.Lancamento;
import com.matias.financas_api.model.Categoria; // Importação corrigida aqui!
import com.matias.financas_api.repository.LancamentoRepository;
import com.matias.financas_api.dto.CompraParceladaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lancamentos")
@CrossOrigin(origins = "*")
public class LancamentoController {

    @Autowired
    private LancamentoRepository repository;

    // Rota para buscar todos os lancamentos
    @GetMapping
    public List<Lancamento> listarTodos() {
        return repository.findAll();
    }

    // Rota pra salvar um novo lançamento completo
    @PostMapping
    public Lancamento salvar(@RequestBody Lancamento lancamento) {
        return repository.save(lancamento);
    }

    // Rota para atualizar um lançamento existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody Lancamento dadosAtualizados){
        return repository.findById(id)
                .map(lancamento -> {
                    lancamento.setDescricao(dadosAtualizados.getDescricao());
                    lancamento.setValor(dadosAtualizados.getValor());
                    repository.save(lancamento);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Rota para deletar um lançamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ROTA UNIFICADA: Cria o parcelamento diretamente com os dados exatos do DTO novo
    @PostMapping("/parcelado")
    public ResponseEntity<Void> criarParcelamento(@RequestBody CompraParceladaDTO dto) {
        List<Lancamento> parcelas = new ArrayList<>();
        LocalDate dataInicial = LocalDate.parse(dto.getDataPrimeiraParcela());

        // Mantemos a divisão normal
        Double valorParcela = dto.getValorTotal() / dto.getQuantidadeParcela();

        Categoria categoriaRef = new Categoria();
        categoriaRef.setId(dto.getCategoriaId());

        for (int i = 1; i <= dto.getQuantidadeParcela(); i++) {
            Lancamento l = new Lancamento();
            // Já com a correção do UUID que fizemos antes!
            l.setUsuarioId(java.util.UUID.fromString("38dab698-149d-40c0-8dd1-21a1a9150ae6"));
            l.setCategoria(categoriaRef);
            l.setDescricao(dto.getDescricao() + " (" + i + "/" + dto.getQuantidadeParcela() + ")");

            // CONVERSÃO PARA BIGDECIMAL AQUI:
            l.setValor(java.math.BigDecimal.valueOf(valorParcela));

            l.setTipo("DESPESA");
            l.setDataCompetencia(dataInicial.plusMonths(i - 1));
            l.setIsFixo(false);
            parcelas.add(l);
        }

        repository.saveAll(parcelas);
        return ResponseEntity.status(201).build();
    }
}