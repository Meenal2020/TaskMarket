package com.kodekonveyor.authentication;

import static org.mockito.Mockito.*;//NOPMD

import java.util.Optional;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class UserEntityRepositoryStubs {

  public static void behaviour(
      final UserEntityRepository userEntityRepository
  ) {
    reset(userEntityRepository);
    doReturn(Optional.of(UserEntityTestData.get())).when(userEntityRepository)
        .findByLogin(UserTestData.LOGIN);
    doReturn(Optional.of(UserEntityTestData.getRoleCanbePaid()))
        .when(userEntityRepository)
        .findByLogin(UserTestData.LOGIN_REGISTERED);
    doReturn(Optional.of(UserEntityTestData.getRoleKodekonveyorContract()))
        .when(userEntityRepository)
        .findByLogin(UserTestData.LOGIN_CONTRACT);
    doReturn(Optional.of(UserEntityTestData.getRoleProjectManager()))
        .when(userEntityRepository)
        .findByLogin(UserTestData.LOGIN_PROJECTMANAGER);
    doReturn(Optional.empty()).when(userEntityRepository)
        .findByLogin(UserTestData.LOGIN_BAD);
    doReturn(Optional.of(UserEntityTestData.getIdNoMarketUser())).when(userEntityRepository)
            .findByLogin(UserTestData.LOGIN_NO_MARKET_USER);

    final Answer<UserEntity> answer = new Answer<>() {

      @Override
      public UserEntity answer(final InvocationOnMock invocation) {
        final Object[] args = invocation.getArguments();
        final UserEntity user = (UserEntity) args[0];
        user.setId(UserTestData.ID_BAD);
        return null;
      }
    };
    when(userEntityRepository.save(UserEntityTestData.getIdUninitialized()))
        .then(answer);

  }
}
