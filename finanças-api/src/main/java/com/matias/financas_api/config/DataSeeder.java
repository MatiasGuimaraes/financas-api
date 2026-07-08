package com.matias.financas_api.config;

import com.matias.financas_api.model.Categoria;
import com.matias.financas_api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Regra 1: Instalação Limpa (Para quando você distribuir o app para outras pessoas)
        if (categoriaRepository.count() <= 1) {
            Categoria c1 = new Categoria();
            c1.setNome("Salário");
            c1.setTipo("RECEITA");

            Categoria c2 = new Categoria();
            c2.setNome("Alimentação");
            c2.setTipo("DESPESA");

            Categoria c3 = new Categoria();
            c3.setNome("Lazer");
            c3.setTipo("DESPESA");

            Categoria c4 = new Categoria();
            c4.setNome("Contas Fixas");
            c4.setTipo("DESPESA");

            Categoria c5 = new Categoria();
            c5.setNome("Transporte");
            c5.setTipo("DESPESA");

            Categoria c6 = new Categoria();
            c6.setNome("Saúde e Higiene");
            c6.setTipo("DESPESA");

            Categoria c7 = new Categoria();
            c7.setNome("Vestuário");
            c7.setTipo("DESPESA");

            // A sua nova categoria padrão mundial:
            Categoria c8 = new Categoria(); c8.setNome("Renda Extra"); c8.setTipo("RECEITA");

            categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8));
            System.out.println("🌱 Categorias base semeadas com sucesso no banco de dados!");
        }
        // Regra 2: Atualização do Sistema (Para quem já tem dados, como você agora)
        else {
            List<Categoria> categorias = categoriaRepository.findAll();
            boolean temRendaExtra = categorias.stream().anyMatch(c -> "Renda Extra".equals(c.getNome()));

            if (!temRendaExtra) {
                Categoria nova = new Categoria();
                nova.setNome("Renda Extra");
                nova.setTipo("RECEITA");
                categoriaRepository.save(nova);
                System.out.println("🌱 Atualização: Categoria 'Renda Extra' adicionada aos usuários existentes!");
            }
        }
    }
}