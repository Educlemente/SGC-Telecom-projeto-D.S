package br.com.sgc.config;

import br.com.sgc.domain.model.Produto;
import br.com.sgc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        
        // Verifica se a base de dados já tem produtos
        if (produtoRepository.count() == 0) {
            
            Produto p1 = new Produto();
            p1.setNome("Plano Fibra 500 Mega");
            p1.setDescricao("Internet fibra ótica de alta velocidade.");
            p1.setPreco(new BigDecimal("99.90"));
            p1.setQuantidadeEmEstoque(999); // Obrigatório preencher!
            
            Produto p2 = new Produto();
            p2.setNome("Plano Fibra 1 Giga");
            p2.setDescricao("Internet ultra rápida para empresas e gamers.");
            p2.setPreco(new BigDecimal("149.90"));
            p2.setQuantidadeEmEstoque(999);
            
            Produto p3 = new Produto();
            p3.setNome("Roteador Wi-Fi 6");
            p3.setDescricao("Equipamento de última geração com maior alcance.");
            p3.setPreco(new BigDecimal("299.00"));
            p3.setQuantidadeEmEstoque(50);

            // Guardando tudo na base de dados de uma vez só
            produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
            
            System.out.println("✅ Produtos iniciais cadastrados na base de dados com sucesso!");
        }
    }
}