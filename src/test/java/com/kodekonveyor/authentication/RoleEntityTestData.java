package com.kodekonveyor.authentication;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class RoleEntityTestData {

  public final static RoleEntity get() {
    final RoleEntity roleEntity = new RoleEntity();
    roleEntity.setId(RoleTestData.ID);
    roleEntity.setName(RoleTestData.NAME);
    return roleEntity;
  }

  public static RoleEntity getNameCanbepaid() {
    final RoleEntity roleEntity = get();
    roleEntity.setName(RoleTestData.NAME_CAN_BE_PAID);
    roleEntity.setId(RoleTestData.ID_CAN_BE_PAID);
    return roleEntity;
  }

  public static RoleEntity getNameProjectManager() {
    final RoleEntity roleEntity = new RoleEntity();
    roleEntity.setId(RoleTestData.ID_PROJECT_MANAGER);
    roleEntity.setName(RoleTestData.NAME_PROJECT_MANAGER);
    return roleEntity;
  }

  public static RoleEntity getRoleKodekonveyorContract() {
    final RoleEntity roleEntity = new RoleEntity();
    roleEntity.setId(RoleTestData.ID_KODEKONVEYOR_CONTRACT);
    roleEntity.setName(RoleTestData.NAME_KODEKONVEYOR_CONTRACT);
    return roleEntity;
  }

  public static RoleEntity getRoleSales() {
    final RoleEntity roleEntity = new RoleEntity();
    roleEntity.setId(RoleTestData.ID_KODEKONVEYOR_SALES);
    roleEntity.setName(RoleTestData.NAME_KODEKONVEYOR_SALES);
    return roleEntity;
  }

  public static RoleEntity getNamePrivateProjectCoder() {
    final RoleEntity roleEntity = new RoleEntity();
    roleEntity.setId(RoleTestData.ID_CODER);
    roleEntity.setName(RoleTestData.NAME_PRIVATE_PROJECT_CODER);
    return roleEntity;
  }

  public static RoleEntity getNameTechnical() {
    final RoleEntity roleEntity = new RoleEntity();
    roleEntity.setId(RoleTestData.ID_TECHNICAL_ROLE);
    roleEntity.setName(RoleTestData.TECHNICAL_ROLE);
    return roleEntity;
  }

  public static RoleEntity getNameRegistered() {
    final RoleEntity roleEntity = new RoleEntity();
    roleEntity.setId(RoleTestData.ID_REGISTERED_ROLE);
    roleEntity.setName(RoleTestData.NAME_REGISTERED);
    return roleEntity;
  }

}
