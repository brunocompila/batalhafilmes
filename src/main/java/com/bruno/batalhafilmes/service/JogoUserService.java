package com.bruno.batalhafilmes.service;

import com.bruno.batalhafilmes.domain.JogoUser;
import com.bruno.batalhafilmes.dto.JogoUserDto;
import com.bruno.batalhafilmes.repository.JogoUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Bruno Henrique
 **/

@Service
@RequiredArgsConstructor
@Slf4j
public class JogoUserService {

  private final JogoUserRepository jogoUserRepository;

  public void salvarJogos(JogoUserDto jogoUserDto){

    jogoUserRepository.save(JogoUser.builder()
        .nameUser(jogoUserDto.getNameUser())
        .qtdAcertos(jogoUserDto.getQtdAcertos())
        .qtdJogo(jogoUserDto.getQtdJogo())
        .build());

  }

  public JogoUserDto getJogoUsuario(String idUsuario){

    JogoUser jogoUser = jogoUserRepository.getByNameUser(idUsuario);

    return JogoUserDto.builder()
        .nameUser(jogoUser.getNameUser())
        .qtdAcertos(jogoUser.getQtdAcertos())
        .qtdJogo(jogoUser.getQtdJogo())
        .build();

  }

}
