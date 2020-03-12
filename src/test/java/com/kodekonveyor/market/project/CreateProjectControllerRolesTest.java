package com.kodekonveyor.market.project;

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
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.UnauthorizedException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("roles")
@TestedService("CreateProjectController")
public class CreateProjectControllerRolesTest
    extends CreateProjectControllerTestBase {

  @Test
  @DisplayName(
    "if the user does not have Project manager role, a UnauthorizedException is thrown"
  )
  void test() {
    AuthenticatedUserStubs.authenticated(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> createProjectController.call(ProjectDTOTestData.get())
    ).assertException(UnauthorizedException.class);
  }

  @Test
  @DisplayName(
    "if the user does not have Project manager role, the error message is 'in create project'"
  )
  void test1() {
    AuthenticatedUserStubs.authenticated(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> createProjectController.call(ProjectDTOTestData.get())
    ).assertMessageIs(CreateProjectControllerTestData.IN_CREATE_PROJECT);
  }

  @Test
  @DisplayName("if the user has can_be_payed role, no exception is thrown")
  void test2() {
    AuthenticatedUserStubs.projectManager(authenticatedUserService);

    ThrowableTester.assertNoException(
        () -> createProjectController.call(ProjectDTOTestData.get())
    );
  }
}
