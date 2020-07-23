package com.kodekonveyor.market.tasks;

import static org.mockito.Mockito.doReturn;

public class UpdateTasksServiceStubs {

  public static void
      behaviour(final UpdateTasksService updateTasksService) {
    doReturn(TaskEntityDescriptionsTestData.getDescriptionDifferent())
        .when(updateTasksService)
        .call(TaskEntityTestData.get());

  }
}
