package com.kodekonveyor.authentication;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

  List<UserEntity> findByLogin(String login);
}
