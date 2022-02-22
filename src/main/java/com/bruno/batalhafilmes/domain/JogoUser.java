package com.bruno.batalhafilmes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Bruno Henrique
 **/
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JogoUser {


  @Id
  @GeneratedValue
  private Integer id;

  private String nameUser;

  private Integer qtdJogo;

  private Integer qtdAcertos;

}
