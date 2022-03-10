package com.bruno.batalhafilmes.repository;

import com.bruno.batalhafilmes.domain.JogoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Henrique
 **/
@Repository
public interface JogoUserRepository extends JpaRepository<JogoUser, Integer> {

  JogoUser getByNameUser(String nameUser);

}
