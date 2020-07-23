package com.kodekonveyor.authentication;

import static org.mockito.Mockito.doReturn;

import java.util.Optional;

public class RoleEntityRepositoryStubs {

  public static void
      behaviour(final RoleEntityRepository roleEntityRepository) {
    doReturn(Optional.of(RoleEntityTestData.get())).when(roleEntityRepository)
        .findById(RoleTestData.ID);
    doReturn(Optional.of(RoleEntityTestData.getNameCanbepaid()))
        .when(roleEntityRepository)
        .findById(RoleTestData.ID_CAN_BE_PAID);
    doReturn(Optional.of(RoleEntityTestData.getNameProjectManager()))
        .when(roleEntityRepository)
        .findById(RoleTestData.ID_PROJECT_MANAGER);
    doReturn(Optional.of(RoleEntityTestData.getRoleKodekonveyorContract()))
        .when(roleEntityRepository)
        .findById(RoleTestData.ID_KODEKONVEYOR_CONTRACT);

    doReturn(Optional.of(RoleEntityTestData.getNameProjectManager()))
        .when(roleEntityRepository)
        .findByName(RoleTestData.NAME_PROJECT_MANAGER);
    doReturn(Optional.of(RoleEntityTestData.getNameRegistered()))
        .when(roleEntityRepository)
        .findByName(RoleTestData.NAME_REGISTERED);

  }

}
