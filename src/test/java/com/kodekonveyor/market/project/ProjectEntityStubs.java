package com.kodekonveyor.market.project;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;

public class ProjectEntityStubs {

  public static void behaviour(
      final ProjectEntityRepository projectEntityRepository
  ) {
    reset(projectEntityRepository);
    doReturn(ProjectEntityTestData.list()).when(projectEntityRepository)
        .findAll();
  }
}
