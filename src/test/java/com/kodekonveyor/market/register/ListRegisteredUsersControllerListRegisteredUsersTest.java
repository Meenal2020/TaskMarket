package com.kodekonveyor.market.register;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
public class ListRegisteredUsersControllerListRegisteredUsersTest
    extends ListRegisteredUsersControllerTestBase {

  @BeforeEach
  void setUp() {
    RoleEntityRepositoryStubs.behaviour(roleEntityRepository);
    UserEntityRepositoryStubs.behaviour(userEntityRepository);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
  }

  @Test
  @DisplayName("Successuly returned details of registered market users")
  public void test() {
    assertEquals(
        List.of(MarketUserDTOTestData.getRoleRegistered()), listRegisteredUsersController.call()
    );
  }

  @Test
  @DisplayName("Empty list is returned if there are no registered market users")
  public void test1() {
    UserEntityRepositoryStubs.noRegisteredUser(userEntityRepository);
    MarketUserEntityRepositoryStubs.behaviour2(marketUserEntityRepository);
    assertEquals(
        List.of(), listRegisteredUsersController.call()
    );
  }

  @Test
  @DisplayName("Successuly returned Balance of registered market user")
  public void test2() {
    assertEquals(
        MarketUserDTOTestData.getRoleRegistered().getBalanceInCents(),
        listRegisteredUsersController.call().get(0).getBalanceInCents()
    );
  }

  @Test
  @DisplayName("Successuly returned email of registered market user")
  public void test3() {
    assertEquals(
        MarketUserDTOTestData.getRoleRegistered().getEmail(),
        listRegisteredUsersController.call().get(0).getEmail()
    );
  }

  @Test
  @DisplayName("Successuly returned Id of registered market user")
  public void test4() {
    assertEquals(
        MarketUserDTOTestData.getRoleRegistered().getId(),
        listRegisteredUsersController.call().get(0).getId()
    );
  }

  @Test
  @DisplayName(
    "Successuly returned is terms accepted of registered market user"
  )
  public void test5() {
    assertEquals(
        MarketUserDTOTestData.getRoleRegistered().getIsTermsAccepted(),
        listRegisteredUsersController.call().get(0).getIsTermsAccepted()
    );
  }

  @Test
  @DisplayName("Successuly returned legal address of registered market user")
  public void test6() {
    assertEquals(
        MarketUserDTOTestData.getRoleRegistered().getLegalAddress(),
        listRegisteredUsersController.call().get(0).getLegalAddress()
    );
  }

  @Test
  @DisplayName("Successuly returned legal form of registered market user")
  public void test7() {
    assertEquals(
        MarketUserDTOTestData.getRoleRegistered().getLegalForm(),
        listRegisteredUsersController.call().get(0).getLegalForm()
    );
  }
}
