package com.kodekonveyor.market.project;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProjectEntityRepository
    extends CrudRepository<ProjectEntity, Long> {

  @Override
  Optional<ProjectEntity> findById(Long projectId);
}
