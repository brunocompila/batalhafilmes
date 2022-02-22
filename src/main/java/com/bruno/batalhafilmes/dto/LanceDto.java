package com.bruno.batalhafilmes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Bruno Henrique
 **/

@Data
@AllArgsConstructor
public class LanceDto {

  @JsonProperty("movieSelected")
  private LanceFilmeDto movieSelected;

  private LanceFilmeDto movieChallenger;



}
