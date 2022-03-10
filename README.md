# Introdução

Serviços REST para o Jogo "batalhafilmes". O Jogo consiste em obter uma lista de filmes
e a mesma será organizada em pares para que o jogador escolha uma que ele acredita ter 
maior pontação entre os dois. É feita algunas rodadas e persistido os resultados. Outro 
jogador irá realizar o mesmo procedimento e após será analizado qual tem o maior número de
acertos. A autorização e autenticação de usuários é feita utilizando o padrão OAuth2.0 utilizando 
um servidor externo para realizá-la e disponilizar o "token" para realizar requisições
nos serviços. Ao ser implantada a aplicação a documentação dos serviços poderá ser consultada no endpoint: 
"<http://<servidor_da_aplicação>>/swagger-ui/index.html#" 

# Serviços
> - GET /filmes
Obtém a Lista de Informações sobre os filmes. É definido a lista de filmes no arquivo de 
propriedades da aplicação "application.yml" assim é possível mudar a lista de filme para o 
jogo alterando a propriedade "app.movies.list" neste arquivo. Através dessa lista é feita a
busca de informações sobre pontuação dos filmes na API externa: "http://www.omdbapi.com"
com as informações obtidas é feito o cálculo das pontuações dos filmes.

> - GET /lances
Recebe informações de dois filmes sendo um escolhido como o possível de maior pontuação em seguida 
é feita a comparação e retorna o resultado se houve acerto na escolha. Assim o jogador terá informação
de sua escolha entre os pares e saberá se pontuou no jogo.

> - POST /jogos
Após o jogador ter realizados várias jogadas a chamada desse Serviço irá armazenar
o resultado dos jogos para cada Jogador.

> - GET /jogos
Obtém o resultado de cada jogador para comparação entre ambos e definir qual é o vencedor
da rodada.

# Tecnologias

>
>- SpringSecuriy e Keycloak 
 Autenticação e Autorização
 
>- OpenFeigin 
 Chamadas em API's externas
 
>- vOpenApi 3.0 
 Documentação
 
>- H2 
 banco de dados
 
>- JUnit, Mock e Hamcrest 
 Testes Unitários
 
>- Lombok 
 Geração de códigos


