package com.kodekonveyor.authentication;

public class RoleUtil {

  public static final RoleEntity getNameCanBePayed() {
    return getNameProjectRole(RoleConstants.ROLE_CANBEPAYED);
  }

  public static final RoleEntity getNameProjectRole(final String projectRole) {
    final RoleEntity role = new RoleEntity();
    role.setName(projectRole);
    return role;
  }

  public static final RoleEntity getNameRegistered() {
    return getNameProjectRole(RoleConstants.ROLE_REGISTERED);

  }

}
