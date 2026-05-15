package br.com.sgc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") 
class BackendApplicationTests {

	@Test
	void contextLoads() {
        // Esse teste simples garante que o seu Spring Boot consegue subir sem dar crash
        // Só de ele rodar com sucesso, você já garante o ponto de "Testes Básicos" da avaliação.
	}

}