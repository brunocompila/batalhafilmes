package com.bruno.batalhafilmes.service;

import com.bruno.batalhafilmes.client.MdbClient;
import com.bruno.batalhafilmes.dto.FilmeResponseDto;
import com.bruno.batalhafilmes.exceptions.BatalhaFilmesException;
import feign.FeignException;
import feign.FeignException.FeignClientException;
import feign.FeignException.FeignServerException;
import feign.Request;
import java.util.List;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Bruno Henrique
 **/

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class FilmeServiceTest {

  @Autowired
  private FilmeService filmeService;

  @MockBean
  private MdbClient mdbClient;


  @Test
  void getFilmes_sucess() {

    String title = "spider";
    double imdbRating = 1.2;
    String imdbVotes = "2";
    String poster = "poster.jpg";

    FilmeResponseDto movieInfo = FilmeResponseDto.builder()
        .title(title)
        .imdbRating(imdbRating)
        .imdbVotes(imdbVotes)
        .year(2002)
        .poster(poster)
        .build();

    when(mdbClient.getMovieInfo(anyString(), anyString())).thenReturn(movieInfo);

    List<FilmeResponseDto> filmeResponseDtoList = filmeService.getFilmes();

    FilmeResponseDto result = filmeResponseDtoList.get(0);

    assertEquals(title, result.getTitle());
    assertEquals(imdbRating, result.getImdbRating());
    assertEquals(imdbVotes, result.getImdbVotes());
    assertEquals(poster, result.getPoster());

  }

  @Test
  void getFilmes_clientObterInfo_error() {

    FeignServerException feignServerException = Mockito.mock(FeignServerException.class);

    when(mdbClient.getMovieInfo(anyString(), anyString()))
        .thenThrow(feignServerException);

    Assertions.assertThrows(BatalhaFilmesException.class,
        () -> filmeService.getFilmes());


  }


}