package com.bruno.batalhafilmes.web;

import com.bruno.batalhafilmes.dto.JogoUserDto;
import com.bruno.batalhafilmes.dto.ResultadoJogoResponseDto;
import com.bruno.batalhafilmes.dto.FilmeResponseDto;
import com.bruno.batalhafilmes.dto.LanceDto;
import com.bruno.batalhafilmes.service.FilmeService;
import com.bruno.batalhafilmes.service.JogoUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bruno Henrique
 **/
@RestController
@RequiredArgsConstructor
public class BatalhaFilmeController {

  private final FilmeService filmeService;

  private final JogoUserService jogoUserService;

  @Operation(summary = "Recebe informações de dois filmes sendo um escolhido como o possível de maior pontuação em seguida \n"
      + "é feita a comparação e retorna o resultado se houve acerto na escolha. Assim o jogador terá informação\n"
      + "de sua escolha entre os pares e saberá se pontuou no jogo. ", tags = {"lances"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "succesfull",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ResultadoJogoResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content)})

  @PostMapping(value = "/lances",
      produces = {"application/json"}
  )
  public ResponseEntity<ResultadoJogoResponseDto> verificarLance(@RequestBody LanceDto lanceDto) {

    return new ResponseEntity<>(filmeService.verificarLance(lanceDto), HttpStatus.OK);

  }


  @Operation(summary = "Salva jogos que o usuário realizou na rodada ", tags = {"jogos"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "succesfull",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ResultadoJogoResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content)})

  @PostMapping(value = "/jogos",
      produces = {"application/json"}
  )
  public ResponseEntity<Void> salvarJogosUsuario(@RequestBody JogoUserDto jogoUserDto) {
    jogoUserService.salvarJogos(jogoUserDto);
    return new ResponseEntity<>(HttpStatus.OK);

  }


  @Operation(summary = "Obtém rodada de Jogos que o Usuário realizou ", tags = {"jogos"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "succesfull",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ResultadoJogoResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content)})

  @GetMapping(value = "/jogos",
      produces = {"application/json"}
  )
  public ResponseEntity<JogoUserDto> getJogoUsuario(@RequestParam String idUser) {

    return new ResponseEntity<>(jogoUserService.getJogoUsuario(idUser), HttpStatus.OK);

  }


  @Operation(summary = "Obtém a Lista de Informações sobre os filmes para o Game", tags = {
      "filmes"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "succesfull",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ResultadoJogoResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content)})

  @GetMapping("/filmes")
  public ResponseEntity<List<FilmeResponseDto>> getListaFilmes() {

    return new ResponseEntity<>(filmeService.getFilmes(), HttpStatus.OK);

  }


}
