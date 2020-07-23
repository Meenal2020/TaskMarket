package com.kodekonveyor.market.register;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
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
import com.kodekonveyor.authentication.RoleEntityRepositoryStubs;
import com.kodekonveyor.authentication.UserEntityRepositoryStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("List registered users")
@TestedService("ListRegisteredUsersController")
public class ListRegisteredUsersControllerListRegisteredUsers2Test
    extends ListRegisteredUsersControllerTestBase {

  @BeforeEach
  void setUp() {
    RoleEntityRepositoryStubs.behaviour(roleEntityRepository);
    UserEntityRepositoryStubs.behaviour(userEntityRepository);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
  }

  @Test
  @DisplayName("Successuly returned legal name of registered market user")
  public void test8() {
    assertEquals(
        MarketUserDTOTestData.getRoleRegistered().getLegalName(),
        listRegisteredUsersController.call().get(0).getLegalName()
    );
  }

  @Test
  @DisplayName("Successuly returned login of registered market user")
  public void test9() {
    assertEquals(
        MarketUserDTOTestData.getRoleRegistered().getLogin(),
        listRegisteredUsersController.call().get(0).getLogin()
    );
  }

  @Test
  @DisplayName("Successuly returned payment details of registered market user")
  public void test10() {
    assertEquals(
        MarketUserDTOTestData.getRoleRegistered().getPaymentDetail(),
        listRegisteredUsersController.call().get(0).getPaymentDetail()
    );
  }

  @Test
  @DisplayName("Successuly returned personal name of registered market user")
  public void test11() {
    assertEquals(
        MarketUserDTOTestData.getRoleRegistered().getPersonalName(),
        listRegisteredUsersController.call().get(0).getPersonalName()
    );
  }

  @Test
  @DisplayName("Successuly returned user details ID of registered market user")
  public void test12() {
    assertEquals(
        MarketUserDTOTestData.getRoleRegistered().getUser(),
        listRegisteredUsersController.call().get(0).getUser()
    );
  }

}
