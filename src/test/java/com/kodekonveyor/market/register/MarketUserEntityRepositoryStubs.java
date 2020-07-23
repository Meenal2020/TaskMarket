package com.kodekonveyor.market.register;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.kodekonveyor.authentication.UserEntityTestData;

public class MarketUserEntityRepositoryStubs {

  public static void
      behaviour(
          final MarketUserEntityRepository marketUserEntityRepository
      ) {
    doReturn(Optional.of(MarketUserEntityTestData.get()))
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.get());
    doReturn(Optional.of(MarketUserEntityTestData.getUnacceptedContractuser()))
        .when(marketUserEntityRepository)
        .findById(MarketUserTestData.ID_IS_TERMS_ACCEPTED_FALSE);
    doReturn(Optional.of(MarketUserEntityTestData.get()))
        .when(marketUserEntityRepository).findById(MarketUserTestData.ID);

    doReturn(Optional.of(MarketUserEntityTestData.getRoleCanBePaid()))
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getRoleCanbePaid());
    doReturn(Optional.of(MarketUserEntityTestData.getRoleCanBePaid()))
        .when(marketUserEntityRepository)
        .findById(MarketUserTestData.ID_CAN_BE_PAID);
    doReturn(Optional.of(MarketUserEntityTestData.getRoleProjectManager()))
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getRoleProjectManager());

    doReturn(Optional.of(MarketUserEntityTestData.getIdInNullDatabase()))
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getIdInNullDatabase());

    doReturn(
        Optional.of(MarketUserEntityTestData.getIsTerrmsAcceptedFalse())
    )
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getContractTermsNotAccepted());

    doReturn(
        Optional.of(MarketUserEntityTestData.getRoleKodeKonveyorContract())
    )
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getRoleKodekonveyorContract());

    doReturn(
        Optional.of(MarketUserEntityTestData.getRoleManager())
    )
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getRoleProjectManager());

    doReturn(
        Optional.of(MarketUserEntityTestData.getRoleRegistered())
    )
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getRoleRegistered());

    doAnswer(new Answer<Void>() {

      @Override
      public Void answer(final InvocationOnMock invocation) throws Throwable {
        final Object[] arguments = invocation.getArguments();
        final MarketUserEntity user = (MarketUserEntity) arguments[0];
        if (user.getId() == null)
          user.setId(MarketUserTestData.ID_NOT_IN_DATABASE);
        return null;
      }
    }).

        when(
            marketUserEntityRepository
        ).save(Mockito.any(MarketUserEntity.class));

  }

  public static void contractTermsAccepted(
      final MarketUserEntityRepository marketUserEntityRepository,
      final MarketUserDTOTestData registerTestData
  ) {
    doReturn(Optional.of(MarketUserEntityTestData.getAcceptedContractuser()))
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getRoleKodekonveyorContract());
  }

  public static void
      contractTermsNotAccepted(
          final MarketUserEntityRepository marketUserEntityRepository,
          final MarketUserDTOTestData registerTestData
      ) {
    doReturn(Optional.of(MarketUserEntityTestData.getUnacceptedContractuser()))
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getRoleKodekonveyorContract());
  }

  public static void
      behaviour2(
          final MarketUserEntityRepository marketUserEntityRepository
      ) {
    doReturn(Optional.of(MarketUserEntityTestData.getRoleManager()))
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getRoleProjectManager());
    doReturn(Optional.of(MarketUserEntityTestData.getLessBalance()))
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.get());

    doReturn(Optional.of(MarketUserEntityTestData.getZeroBalance()))
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getIdForZeroBalanceForProjectManager());
    doReturn(
        Optional.empty()
    )
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getRoleRegistered());
  }

  public static void userBalanceEqualToBudget(
      final MarketUserEntityRepository marketUserEntityRepository
  ) {
    doReturn(
        Optional.of(MarketUserEntityTestData.getRoleProjectManagerEqualBudget())
    )
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getRoleProjectManager());

  }

  public static void userBalanceMoreThanBudget(
      final MarketUserEntityRepository marketUserEntityRepository
  ) {
    doReturn(
        Optional
            .of(MarketUserEntityTestData.getRoleProjectManagerMoreThanBudget())
    )
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getRoleProjectManager());

  }

  public static void memberOfPrivateProject(
      final MarketUserEntityRepository marketUserEntityRepository
  ) {
    doReturn(Optional.of(MarketUserEntityTestData.getPrivateProjectCoder()))
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getPrivateProjectCoder());
  }

  public static void
      nonMarketUser(final MarketUserEntityRepository marketUserEntityRepository) {
    doReturn(Optional.empty())
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.get());
  }

  public static void userNewlyRegistered(
      final MarketUserEntityRepository marketUserEntityRepository
  ) {
    doReturn(Optional.of(MarketUserEntityTestData.getIdNewlySaved()))
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getIdNoMarketUser());

  }

}
