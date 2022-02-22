package com.bruno.batalhafilmes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Bruno Henrique
 **/

@Data
@AllArgsConstructor
public class LanceFilmeDto {

  private String title;

  private String imdbRating;

  private String imdbVotes;

}
