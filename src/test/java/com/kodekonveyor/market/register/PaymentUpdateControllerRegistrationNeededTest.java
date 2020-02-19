package com.kodekonveyor.market.register;

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
@TestedBehaviour("registration needed")
@TestedService("PaymentUpdateController")
public class PaymentUpdateControllerRegistrationNeededTest
    extends PaymentUpdateControllerTestBase {

  @Test
  @DisplayName(
    "if the user does not have can_be_payed role, a NotLoggedInException is thrown"
  )
  void test() {
    AuthenticatedUserStubs.authenticated(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController.call(RegisterTestData.PAYMENT_DETAILS)
    ).assertException(UnauthorizedException.class);
  }

  @Test
  @DisplayName(
    "if the user does not have can_be_payed role, a NotLoggedInException is thrown"
  )
  void test1() {
    AuthenticatedUserStubs.authenticated(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController.call(RegisterTestData.PAYMENT_DETAILS)
    ).assertMessageIs(PaymentUpdateControllerTestData.IN_PAYMENT_UPDATE);
  }

  @Test
  @DisplayName("if the user has can_be_played role, no exception is thrown")
  void test2() {
    AuthenticatedUserStubs.kodekonveyorContract(authenticatedUserService);

    ThrowableTester.assertNoException(
        () -> paymentUpdateController.call(RegisterTestData.PAYMENT_DETAILS)
    );
  }

}
