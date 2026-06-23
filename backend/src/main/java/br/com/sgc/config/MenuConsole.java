package br.com.sgc.config;

import br.com.sgc.domain.model.Produto;
import br.com.sgc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Component
public class MenuConsole implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        // Pausa para os logs do Spring subirem
        Thread.sleep(2000);

        while (rodando) {
            System.out.println("\n========================================");
            System.out.println("       SGC TELECOM - SYSTEM MENU        ");
            System.out.println("========================================");
            System.out.println("1. Listar Todos os Produtos (Vitrine)");
            System.out.println("2. Cadastrar Novo Produto");
            System.out.println("3. Deletar Produto por ID");
            System.out.println("4. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa buffer

                switch (opcao) {
                    case 1:
                        System.out.println("\n--- PRODUTOS CADASTRADOS NO BANCO ---");
                        List<Produto> produtos = produtoRepository.findAll();
                        if (produtos.isEmpty()) {
                            System.out.println("[!] Nenhum produto encontrado no banco.");
                        } else {
                            for (Produto p : produtos) {
                                System.out.println("ID: " + p.getId() + 
                                                   " | Nome: " + p.getNome() + 
                                                   " | Preço: R$ " + p.getPreco() + 
                                                   " | Estoque: " + p.getQuantidadeEmEstoque());
                            }
                        }
                        System.out.println("-------------------------------------");
                        break;

                    case 2:
                        System.out.println("\n--- TELA DE CADASTRO DE PRODUTO ---");
                        Produto novoProduto = new Produto();

                        System.out.print("Digite o nome do produto: ");
                        novoProduto.setNome(scanner.nextLine());

                        System.out.print("Digite a descrição: ");
                        novoProduto.setDescricao(scanner.nextLine());

                        System.out.print("Digite o preço (Ex: 150.00): ");
                        novoProduto.setPreco(new BigDecimal(scanner.nextLine()));

                        System.out.print("Digite a quantidade em estoque: ");
                        novoProduto.setQuantidadeEmEstoque(scanner.nextInt());
                        scanner.nextLine(); 

                        produtoRepository.save(novoProduto);
                        System.out.println("\n✅ SUCESSO: Produto salvo no MySQL!");
                        break;

                    case 3:
                        System.out.println("\n--- TELA DE DELEÇÃO DE PRODUTO ---");
                        System.out.print("Digite o ID do produto que deseja excluir: ");
                        Long idDeletar = scanner.nextLong();
                        scanner.nextLine();

                        // O Java verifica se o ID realmente existe antes de tentar deletar
                        if (produtoRepository.existsById(idDeletar)) {
                            produtoRepository.deleteById(idDeletar);
                            System.out.println("\n✅ SUCESSO: Produto com ID " + idDeletar + " foi removido do banco!");
                        } else {
                            System.out.println("\n❌ ERRO: Produto com ID " + idDeletar + " não foi encontrado.");
                        }
                        break;

                    case 4:
                        System.out.println("\nEncerrando o sistema... Até logo!");
                        rodando = false;
                        System.exit(0);
                        break;

                    default:
                        System.out.println("\n❌ Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("\n❌ Erro na operação. Verifique os dados digitados.");
                scanner.nextLine(); 
            }
        }
    }
}