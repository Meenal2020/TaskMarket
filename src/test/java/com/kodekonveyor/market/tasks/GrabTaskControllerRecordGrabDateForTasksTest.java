package com.kodekonveyor.market.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.market.kpi.DateUtilStubs;
import com.kodekonveyor.market.kpi.EventEntityTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("record grab date for tasks")
@TestedService("GrabTaskController")
public class GrabTaskControllerRecordGrabDateForTasksTest
    extends GrabTaskControllerTestBase {

  @Test
  @DisplayName("grab date for the task is recorded successfully")
  public void testGrabDate() {
    DateUtilStubs.getInstant(dateUtilService);
    grabTaskController.call(TaskTestData.ID_2);

    final ArgumentCaptor<TaskEntity> entity =
        ArgumentCaptor.forClass(TaskEntity.class);
    verify(taskEntityRepository).save(entity.capture());

    assertEquals(DateUtilTestData.INSTANT, entity.getValue().getGrabDate());
  }

  @Test
  @DisplayName(
    "Grab event has been raised after the grab date is being recorded."
  )
  public void testGrabEvent() {
    DateUtilStubs.getDate(dateUtilService);
    grabTaskController.call(TaskTestData.ID_2);
    verify(eventEntityRepository)
        .save(EventEntityTestData.getIdZero());

  }

  @Test
  @DisplayName(
    "Task is being saved after the grab date is being recorded."
  )
  public void testTaskSaved() {
    DateUtilStubs.getInstant(dateUtilService);
    grabTaskController.call(TaskTestData.ID_2);
    final ArgumentCaptor<TaskEntity> entity =
        ArgumentCaptor.forClass(TaskEntity.class);

    verify(taskEntityRepository).save(entity.capture());
  }

}
