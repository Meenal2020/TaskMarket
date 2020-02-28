package com.kodekonveyor.market.register;

import static org.junit.Assert.assertEquals;

import java.util.Set;

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
import com.kodekonveyor.authentication.RoleEntityTestData;
import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.exception.ThrowableTester;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("one role once")
@TestedService("AddToRoleController")
public class AddToRoleControllerOneRoleOnceTest
    extends AddToRoleControllerTestBase {

  @Test
  @DisplayName(
    "When the user can be payed & registered , it is added to the 'project' role"
  )
  void test() {
    AuthenticatedUserStubs.registered(authenticatedUserService);

    assertEquals(

        UserEntityTestData.getRoleProjectName().getRoles(),
        Set.of(
            RoleEntityTestData.getNameProjectRole(),
            RoleEntityTestData.getNameCanBePayed(),
            RoleEntityTestData.getNameRegistered()

        )
    );
  }

  @Test
  @DisplayName(
    "if the user has project role, no exception is thrown"
  )
  void test2() {
    AuthenticatedUserStubs.roleExist(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> addToRoleController
            .call(RegisterTestData.PROJECTNAME, RegisterTestData.PROJECTROLE)
    );
  }

}
