package com.bruno.batalhafilmes.client;

import com.bruno.batalhafilmes.dto.FilmeResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bruno Henrique
 **/

@FeignClient(name = "mdbClient", url = "${client.mdb.url}")
public interface MdbClient {

  @GetMapping
  FilmeResponseDto getMovieInfo(@RequestParam("t") String nome,
      @RequestParam("apikey") String apiKey);

}
