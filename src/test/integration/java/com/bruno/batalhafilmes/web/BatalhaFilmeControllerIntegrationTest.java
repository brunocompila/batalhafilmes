package com.bruno.batalhafilmes.web;

import static org.junit.jupiter.api.Assertions.*;

import com.bruno.batalhafilmes.BatalhafilmesApplication;
import com.bruno.batalhafilmes.client.MdbClient;
import com.bruno.batalhafilmes.domain.JogoUser;
import com.bruno.batalhafilmes.dto.JogoUserDto;
import com.bruno.batalhafilmes.dto.LanceDto;
import com.bruno.batalhafilmes.dto.LanceFilmeDto;
import com.bruno.batalhafilmes.repository.JogoUserRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author Bruno Henrique
 **/
@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
    classes = BatalhafilmesApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
class BatalhaFilmeControllerIntegrationTest {

  @Autowired
  private JogoUserRepository jogoUserRepository;

  @Autowired
  private MockMvc mvc;

  @After
  public void resetDb() {
    jogoUserRepository.deleteAll();
  }

  @Test
  void verificarLance() throws Exception {

    LanceFilmeDto movieSelected = new LanceFilmeDto("Spider", "5", "8");
    LanceFilmeDto movieChallenger = new LanceFilmeDto("Spider", "5", "3");

    LanceDto lanceDto = new LanceDto(movieSelected, movieChallenger);

    mvc.perform(post("/lances").contentType(
        MediaType.APPLICATION_JSON).content(toJson(lanceDto)))
       .andExpect(jsonPath("acerto", is(Boolean.TRUE)));

  }

  @Test
  void salvarJogosUsuario_sucess() throws Exception{

    final String nameUser = "Joaquim";

    final JogoUserDto jogoUserDto = JogoUserDto.builder()
        .nameUser(nameUser)
        .build();

    mvc.perform(post("/jogos").contentType(
        MediaType.APPLICATION_JSON).content(toJson(jogoUserDto)));

    final JogoUser jogoUserSalvo = jogoUserRepository.getByNameUser(nameUser);

    Assert.assertNotNull(jogoUserSalvo);

  }

  @Test
  void getJogoUsuario_sucess() throws Exception{

    String nameUser = "Maria";

    JogoUser jogoUser = JogoUser.builder()
        .nameUser(nameUser)
        .build();

    jogoUserRepository.save(jogoUser);
     mvc.perform(get("/jogos").param("idUser", nameUser))
        .andExpect(status().isOk())
        .andExpect(jsonPath("nameUser", is(nameUser)));
  }


  private  byte[] toJson(Object object) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper.writeValueAsBytes(object);
  }
}