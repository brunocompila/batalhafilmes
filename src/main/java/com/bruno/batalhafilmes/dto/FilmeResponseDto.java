package com.bruno.batalhafilmes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bruno Henrique
 **/

@Getter
@Setter
@Builder
public class FilmeResponseDto {

  @JsonProperty("Title")
  private String title;

  @JsonProperty("Year")
  private Integer year;

  @JsonProperty("Poster")
  private String poster;

  @JsonProperty("imdbRating")
  private Double imdbRating;

  @JsonProperty("imdbVotes")
  private String imdbVotes;

}
