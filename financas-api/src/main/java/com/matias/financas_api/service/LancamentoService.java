package com.matias.financas_api.service;

import com.matias.financas_api.dto.CompraParceladaDTO;
import com.matias.financas_api.model.Categoria;
import com.matias.financas_api.model.CompraParcelada;
import com.matias.financas_api.model.Lancamento;
import com.matias.financas_api.repository.CategoriaRepository;
import com.matias.financas_api.repository.CompraPaceladaRepository;
import com.matias.financas_api.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private CompraPaceladaRepository compraParceladaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional // Garante que, se der erro no meio, ele cancela tudo e não salva parcelas pela metade
    public void processarCompraParcelada(CompraParceladaDTO dto, String usuarioId) {

        // 1. Salva a "Compra Mãe"
        CompraParcelada compra = new CompraParcelada();
        compra.setDescricao(dto.getDescricao());
        compra.setValorTotal(dto.getValorTotal());
        compra.setQuantidadeParcelas(dto.getQuantidadeParcela());
        compra = compraParceladaRepository.save(compra); // Salva e pega o ID gerado

        // 2. Busca a Categoria escolhida
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        // 3. Calcula o valor de cada parcela (convertendo de Double para BigDecimal)
        BigDecimal valorParcela = BigDecimal.valueOf(dto.getValorTotal() / dto.getQuantidadeParcela());

        // Converte a data que veio do front-end (ex: "2026-06-01") para o Java entender
        LocalDate dataCompetencia = LocalDate.parse(dto.getDataPrimeiraParcela());

        // 4. O Loop: Cria N Lançamentos avançando 1 mês de cada vez!
        for (int i = 1; i <= dto.getQuantidadeParcela(); i++) {
            Lancamento lancamento = new Lancamento();
            lancamento.setUsuarioId(UUID.fromString(usuarioId));
            lancamento.setCategoria(categoria); // Corrigido de setCategoriaId para setCategoria
            lancamento.setDescricao(dto.getDescricao());
            lancamento.setValor(valorParcela);
            lancamento.setTipo("DESPESA"); // Parcelas são sempre despesas
            lancamento.setIsFixo(false);
            lancamento.setCompraParcelada(compra); // Vincula à compra mãe
            lancamento.setNumeroParcela(i); // Ex: Parcela 1, 2, 3...

            // Define a data desta parcela
            lancamento.setDataCompetencia(dataCompetencia);

            // Salva a parcela no banco
            lancamentoRepository.save(lancamento);

            // Avança o mês para a próxima volta do loop!
            dataCompetencia = dataCompetencia.plusMonths(1);
        }
    }
}