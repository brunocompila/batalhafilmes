package com.bruno.batalhafilmes.service;

import com.bruno.batalhafilmes.client.MdbClient;
import com.bruno.batalhafilmes.domain.JogoUser;
import com.bruno.batalhafilmes.dto.LanceDto;
import com.bruno.batalhafilmes.dto.LanceFilmeDto;
import com.bruno.batalhafilmes.dto.JogoUserDto;
import com.bruno.batalhafilmes.dto.ResultadoJogoResponseDto;
import com.bruno.batalhafilmes.dto.FilmeResponseDto;
import com.bruno.batalhafilmes.exceptions.BatalhaFilmesException;
import com.bruno.batalhafilmes.repository.JogoUserRepository;
import feign.FeignException.FeignClientException;
import feign.FeignException.FeignServerException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Bruno Henrique
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class FilmeService {

   private final MdbClient mdbClient;

   @Value("${client.mdb.apiKey}")
   private String apikey;

   @Value("${app.movies.list}")
   private  String[] moviesNames;

   public List<FilmeResponseDto> getFilmes() {

     final List<FilmeResponseDto> listMovies = new ArrayList<>();

     try {

       for (String name : moviesNames) {
         FilmeResponseDto movieInfo = mdbClient.getMovieInfo(name, apikey);
         listMovies.add(movieInfo);
       }

     } catch (FeignClientException | FeignServerException e) {
          log.error("Erro ao obter lista de Filmes, requisição em Api externa {}",
              e.request() + e.getMessage());
          throw new BatalhaFilmesException("Erro ao obter lista de Filmes para o Jogo");
     }

     return listMovies;
   }

   public ResultadoJogoResponseDto verificarLance(LanceDto lanceDto){

     LanceFilmeDto movieSelected = lanceDto.getMovieSelected();
     LanceFilmeDto movieChallenger = lanceDto.getMovieChallenger();

     BigDecimal imbRatingSelected = new BigDecimal(movieSelected.getImdbRating());
     BigDecimal ratingResultSelectec = imbRatingSelected.multiply(
         new BigDecimal(movieSelected.getImdbVotes().replace(",", "")));

     BigDecimal imbRatingChallenged = new BigDecimal(movieChallenger.getImdbRating());
     BigDecimal ratingResultChallenged = imbRatingChallenged.multiply(
         new BigDecimal(movieChallenger.getImdbVotes().replace(",", "")));

     return ResultadoJogoResponseDto.builder()
         .acerto(ratingResultSelectec.compareTo(ratingResultChallenged) > 0)
         .build();
   }



}
