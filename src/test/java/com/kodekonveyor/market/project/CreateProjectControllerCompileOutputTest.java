package com.kodekonveyor.market.project;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.AuthenticatedUserStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("compile output")
@TestedService("CreateProjectController")
public class CreateProjectControllerCompileOutputTest
    extends CreateProjectControllerTestBase {

  @Test
  @DisplayName("The project id is returned successfully")
  public void test() {
    AuthenticatedUserStubs.projectManager(authenticatedUserService);
    createProjectController.call(ProjectDTOTestData.get());
    assertEquals(
        ProjectDTOTestData.ID, createProjectController
            .call(ProjectDTOTestData.get()).getId()
    );
  }

  @Test
  @DisplayName("The project name is returned successfully")
  public void test1() {
    AuthenticatedUserStubs.projectManager(authenticatedUserService);
    createProjectController.call(ProjectDTOTestData.get());
    assertEquals(
        ProjectEntityTestData.get().getName(), ProjectDTOTestData.NAME
    );
  }

  @Test
  @DisplayName("The controller returns project successfully")
  void test3() {
    AuthenticatedUserStubs.projectManager(authenticatedUserService);
    final ProjectDTO ret =
        createProjectController.call(ProjectDTOTestData.get());
    assertEquals(

        ret, ProjectDTOTestData.get()
    );
  }
}
