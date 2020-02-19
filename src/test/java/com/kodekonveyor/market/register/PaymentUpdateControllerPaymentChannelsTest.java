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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("payment channels")
@TestedService("PaymentUpdateController")
public class PaymentUpdateControllerPaymentChannelsTest
    extends PaymentUpdateControllerTestBase {

  MarketUserDTOTestData registerTestData;

  @Test
  @DisplayName("If paypal payment details are incorrect, we throw exception")
  public void test1() {

    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(RegisterTestData.INVALID_PAYPAL_PAYMENT_DETAILS)
    )
        .assertMessageIs(
            PaymentUpdateControllerTestData.INVALID_PAYMENT_DETAILS_EXCEPTION
        );
  }

  @Test
  @DisplayName("If sepa payment details are incorrect, we throw exception")
  public void test2() {

    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(RegisterTestData.INVALID_SEPA_PAYMENT_DETAILS)
    )
        .assertMessageIs(
            PaymentUpdateControllerTestData.INVALID_PAYMENT_DETAILS_EXCEPTION
        );
  }

  @Test
  @DisplayName(
    "If transferwise payment details are incorrect, we throw exception"
  )
  public void test3() {

    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(RegisterTestData.INVALID_TRANSFERWISE_PAYMENT_DETAILS)
    )
        .assertMessageIs(
            PaymentUpdateControllerTestData.INVALID_PAYMENT_DETAILS_EXCEPTION
        );
  }

  @Test
  @DisplayName(
    "if sepa payment details are correct, no exception is thrown"
  )
  void test4() {
    AuthenticatedUserStubs.kodekonveyorContract(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> paymentUpdateController
            .call(RegisterTestData.PAYMENT_DETAILS_SEPA)
    );

  }

  @Test
  @DisplayName(
    "if transferwise payment details are correct, no exception is thrown"
  )
  void test5() {
    AuthenticatedUserStubs.kodekonveyorContract(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> paymentUpdateController
            .call(RegisterTestData.PAYMENT_DETAILS_TRANSFERWISE)
    );
  }

  @Test
  @DisplayName("If payment channel is incorrect, we throw exception")
  public void test6() {

    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(RegisterTestData.INVALID_PAYMENT_CHANNEL)
    )
        .assertMessageIs(
            PaymentUpdateControllerTestData.INVALID_PAYMENT_DETAILS_EXCEPTION
        );
  }

  @Test
  @DisplayName(
    "if paypal payment details are correct, no exception is thrown"
  )
  void test8() {
    AuthenticatedUserStubs.kodekonveyorContract(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> paymentUpdateController.call(RegisterTestData.PAYMENT_DETAILS)
    );
  }

  @Test
  @DisplayName(
    "if both details are incorrect, exception is thrown"
  )
  void test9() {

    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(RegisterTestData.INVALID_PAYMENT_DETAILS1)
    )
        .assertMessageIs(
            PaymentUpdateControllerTestData.INVALID_PAYMENT_DETAILS_EXCEPTION
        );
  }

}
