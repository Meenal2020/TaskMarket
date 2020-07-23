package com.kodekonveyor.market.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.market.tasks.TaskEntityDescriptionsTestData;
import com.kodekonveyor.market.tasks.TaskEntityTestData;
import com.kodekonveyor.market.tasks.UpdateTasksServiceStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("update tasks")
@TestedService("UpdateProjectModelController")
public class UpdateProjectModelControllerUpdateTasksTest
    extends UpdateProjectModelControllerTestBase {

  @Test
  @DisplayName("Update tasks service is called")
  public void testUpdateTasksService() {
    updateProjectModelController
        .call(ProjectModelDTOTestData.get(), ProjectTestData.PROJECT_NAME);
    Mockito.verify(updateTasksService)
        .call(TaskEntityTestData.get());

  }

  @Test
  @DisplayName("Updated tasks are saved ")
  public void testSaveTask() {
    UpdateTasksServiceStubs.behaviour(updateTasksService);
    updateProjectModelController
        .call(ProjectModelDTOTestData.get(), ProjectTestData.PROJECT_NAME);
    Mockito.verify(taskEntityRepository).save(
        TaskEntityDescriptionsTestData.getDescriptionDifferent()
    );
  }

}
