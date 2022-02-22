package com.bruno.batalhafilmes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bruno Henrique
 **/

@Getter
@Setter
@Builder
public class JogoUserDto {

  private String nameUser;

  private Integer qtdJogo;

  private Integer qtdAcertos;

}
