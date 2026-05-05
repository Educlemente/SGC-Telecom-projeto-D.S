# SGC Telecom - Sistema de Gestão de Clientes

Este é o repositório do projeto SGC Telecom, desenvolvido como parte da Entrega 2 (Backend e API). O projeto utiliza uma arquitetura em camadas e foca na organização, segurança e boas práticas de desenvolvimento.

##  Tecnologias Utilizadas

* **Java 17:** Linguagem principal do backend.
* **Spring Boot 3:** Framework para criação da API REST.
* **Spring Security & JWT:** Implementação de segurança e autenticação baseada em tokens.
* **Spring Data JPA / Hibernate:** Integração e persistência de dados.
* **MySQL:** Banco de dados relacional.
* **JUnit 5:** Framework para testes automatizados.

##  Arquitetura Implementada

O projeto segue estritamente a arquitetura em camadas para garantir a separação de responsabilidades (Design Pattern):

1.  **Controller (`br.com.sgc.controller`):** Responsável por expor os endpoints REST (GET, POST, PUT, DELETE) e receber as requisições HTTP.
2.  **Service (`br.com.sgc.service`):** Contém as regras de negócio da aplicação.
3.  **Repository (`br.com.sgc.repository`):** Interfaces que interagem diretamente com o banco de dados via JPA.
4.  **Domain/Model (`br.com.sgc.domain.model`):** Classes de entidade que representam as tabelas no banco de dados.
5.  **DTO (`br.com.sgc.dto`):** Objetos de transferência de dados utilizados para trafegar informações de forma segura (ex: dados de login).

##  Funcionalidades Principais (Entrega 2)

* **Autenticação JWT:** Sistema de login funcional (`AutenticacaoController`) que gera e valida tokens JWT para proteger a API.
* **CRUD de Clientes e Produtos:** Endpoints funcionais para criar, ler, atualizar e deletar clientes e produtos, com integração real ao banco MySQL.
* **Tratamento Global de Exceções:** Implementação de um `@RestControllerAdvice` (`TratadorDeErros`) para capturar exceções (como erro 404 - `EntityNotFoundException`) e retornar respostas JSON formatadas e amigáveis.
* **Testes Automatizados:** Configuração de testes básicos (`BackendApplicationTests`) para garantir a integridade do contexto do Spring Boot.

##  Como Rodar o Projeto

1.  Clone este repositório: `git clone <URL_DO_REPOSITORIO>`
2.  Configure as credenciais do seu banco de dados MySQL no arquivo `application.yml` (localizado em `src/main/resources`).
3.  Execute o projeto através da sua IDE (VS Code, IntelliJ, Eclipse) rodando a classe principal `BackendApplication.java`.
4.  A API estará disponível na porta `8081` (ex: `http://localhost:8081`).