package com.kodekonveyor.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserEntityTestData {

  public static final Long ID = (long) 42;
  public static final long ID_BAD = -42;
  public static final Long ID_NO_MARKET = (long) 44;
  public static final String LOGIN = "gituser";
  public static final String LOGIN_BAD = "badlogin";
  public static final String LOGIN_NO_MARKET = "userNoMarketUser";

  public static final UserEntity get() {
    final UserEntity user = new UserEntity();
    user.setLogin(LOGIN);
    user.setRoles(Set.of(RoleEntityTestData.get()));
    user.setId(ID);
    return user;
  }

  public static final UserEntity getIdUninitialized() {
    final UserEntity userEntity = new UserEntity();
    userEntity.setLogin(LOGIN);
    return userEntity;
  }

  public static final UserEntity getLoginNoMarket() {
    final UserEntity user = new UserEntity();
    user.setId(ID_NO_MARKET);
    user.setLogin(LOGIN_NO_MARKET);
    return user;
  }

  public static Object getRoleCanbePayed() {
    final UserEntity userEntity = get();
    userEntity.setRoles(Set.of(RoleEntityTestData.getNameCanBePayed()));
    return userEntity;
  }

  public static final UserEntity getRoleExist() {
    final UserEntity userEntity = get();
    userEntity.setRoles(
        Set.of(
            RoleEntityTestData.getNameRoleExist(),
            RoleEntityTestData.getNameCanBePayed(),
            RoleEntityTestData.getNameRegistered()
        )
    );

    return userEntity;
  }

  public static final UserEntity getRoleKodekonveyorContract() {
    final UserEntity userEntity = get();
    userEntity.setLogin(null);
    userEntity.setRoles(
        Set.of(
            RoleEntityTestData.getNameCanBePayed(),
            RoleEntityTestData.getNameKodekonveyorContract()
        )
    );
    return userEntity;
  }

  public static UserEntity getRoleProject() {
    final UserEntity userEntity = get();
    userEntity.setRoles(Set.of(RoleEntityTestData.getNameProject()));
    return userEntity;
  }

  public static final UserEntity getRoleProjectName() {
    final UserEntity userEntity = get();
    userEntity.setRoles(
        Set.of(
            RoleEntityTestData.getNameProjectRole(),
            RoleEntityTestData.getNameCanBePayed(),
            RoleEntityTestData.getNameRegistered()

        )
    );
    return userEntity;
  }

  public static UserEntity getRoleRegistered() {
    final UserEntity userEntity = get();
    userEntity.setRoles(Set.of(RoleEntityTestData.getNameRegistered()));
    return userEntity;
  }

  public static final UserEntity getRoleSales() {
    final UserEntity userEntity = get();
    userEntity.setRoles(Set.of(RoleEntityTestData.getNameSales()));
    return userEntity;
  }

  public static final UserEntity getRoleUnregistered() {
    final UserEntity userEntity = get();
    userEntity.setLogin(null);
    userEntity.setRoles(Set.of(RoleEntityTestData.getNameCanBePayed()));
    return userEntity;
  }

  public static final List<UserEntity> list() {
    return List.of(get());
  }

  public static final List<UserEntity> listEmpty() {
    return new ArrayList<>();
  }

}
