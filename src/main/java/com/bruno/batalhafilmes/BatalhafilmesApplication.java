package com.bruno.batalhafilmes;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "BatalhaFilmes API", version = "0.1",
		description = "Serviços REST para o Jogo \"batalhafilmes\". O Jogo consiste em obter uma lista de filmes\n"
				+ "e a mesma será organizada em pares para que o jogador escolha uma que ele acredita ter \n"
				+ "maior pontação entre os dois. É feita algunas rodadas e persistido os resultados. Outro \n"
				+ "jogador irá realizar o mesmo procedimento e após será analizado qual tem o maior número de\n"
				+ "acertos. A autorização e autenticação de usuários é feita utilizando o padrão OAuth2.0 utilizando \n"
				+ "um servidor externo para realizá-la e disponilizar o \"token\" para realizar requisições\n"
				+ "nos serviços."))
public class BatalhafilmesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatalhafilmesApplication.class, args);
	}

}
