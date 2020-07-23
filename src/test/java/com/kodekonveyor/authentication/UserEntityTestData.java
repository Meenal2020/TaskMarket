package com.kodekonveyor.authentication;

import java.util.Set;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class UserEntityTestData {

  public final static UserEntity get() {
    final UserEntity userEntity = new UserEntity();
    userEntity.setId(UserTestData.ID);
    userEntity.setLogin(UserTestData.LOGIN);
    userEntity
        .setRole(Set.of(RoleEntityTestData.get()));
    return userEntity;
  }

  public final static UserEntity getWithRoles(final Set<RoleEntity> roles) {
    final UserEntity userEntity = new UserEntity();
    userEntity.setId(UserTestData.ID);
    userEntity.setRole(Set.of(RoleEntityTestData.get()));
    userEntity.setLogin(UserTestData.LOGIN);

    return userEntity;
  }

  public static UserEntity getRoleCanbePaid() {
    final UserEntity userEntity = get();
    userEntity.setId(UserTestData.ID_REGISTERED);
    userEntity.setLogin(UserTestData.LOGIN_REGISTERED);
    userEntity.setRole(Set.of(RoleEntityTestData.getNameCanbepaid()));
    return userEntity;
  }

  public static UserEntity getRoleKodekonveyorContract() {
    final UserEntity userEntity = get();
    userEntity.setId(UserTestData.ID_CONTRACT);
    userEntity.setLogin(UserTestData.LOGIN_CONTRACT);
    userEntity
        .setRole(
            Set.of(
                RoleEntityTestData.getRoleKodekonveyorContract(),
                RoleEntityTestData.getNameCanbepaid()
            )
        );
    return userEntity;
  }

  public static UserEntity getRoleProjectManager() {
    final UserEntity userEntity = get();
    userEntity.setId(UserTestData.ID_PROJECTMANAGER);
    userEntity.setLogin(UserTestData.LOGIN_PROJECTMANAGER);
    userEntity.setRole(
        Set.of(
            RoleEntityTestData.getNameProjectManager(),
            RoleEntityTestData.getNameCanbepaid()
        )
    );
    return userEntity;
  }

  public static UserEntity getRoleSales() {
    final UserEntity userEntity = get();
    userEntity.setRole(Set.of(RoleEntityTestData.getRoleSales()));
    return userEntity;
  }

  public static UserEntity getIdUninitialized() {
    final UserEntity userEntity = get();
    userEntity.setId(null);
    return userEntity;
  }

  public static UserEntity getIdNoMarketUser() {
    final UserEntity userEntity = get();
    userEntity.setId(UserTestData.ID_NO_MARKET_USER);
    userEntity.setLogin(UserTestData.LOGIN_NO_MARKET_USER);
    return userEntity;
  }

  public static UserEntity getContractTermsNotAccepted() {
    final UserEntity userEntity = get();
    userEntity.setId(UserTestData.ID_NO_CONTRACT_TERMS_ACCEPTED);
    userEntity.setLogin(UserTestData.LOGIN_NO_CONTRACT_TERMS_ACCEPTED);
    userEntity.setRole(Set.of(RoleEntityTestData.getNameCanbepaid()));
    return userEntity;
  }

  public static UserEntity getIdInNullDatabase() {
    final UserEntity userEntity = get();
    userEntity.setId(UserTestData.ID_IN_NULL_DATABASE);
    userEntity.setLogin(UserTestData.LOGIN_IN_NULL_DATABASE);
    return userEntity;
  };

  public static UserEntity getIdForZeroBalanceForProjectManager() {
    final UserEntity userEntity = getRoleProjectManager();
    userEntity.setId(UserTestData.ID_FOR_ZERO_BALANCE);
    return userEntity;

  }

  public static UserEntity getPrivateProjectCoder() {
    final UserEntity userEntity = get();
    userEntity.setRole(
        Set.of(
            RoleEntityTestData.getNameCanbepaid(),
            RoleEntityTestData.getNamePrivateProjectCoder()
        )
    );
    return userEntity;
  }

  public static UserEntity getRoleTechnical() {
    final UserEntity userEntity = get();
    userEntity.setId(UserTestData.ID_TECHNICAL);
    userEntity.setLogin(UserTestData.LOGIN_TECHNICAL);
    userEntity.setRole(Set.of(RoleEntityTestData.getNameTechnical()));
    return userEntity;
  }

  public static UserEntity getRoleRegistered() {
    final UserEntity userEntity = get();
    userEntity.setId(UserTestData.ID_REGISTERED);
    userEntity.setRole(Set.of(RoleEntityTestData.getNameRegistered()));
    return userEntity;
  }

}
