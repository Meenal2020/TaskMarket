package com.kodekonveyor.market.register;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleUtil;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.lead.CheckRoleUtil;

@RestController
public class AddToRoleController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  UserEntityRepository userEntityRepository;

  @PutMapping(UrlMapConstants.ADDPROJECTROLE_PATH)
  public Object call(final String projectname, final String projectrole) {
    final UserEntity user = authenticatedUserService.call();

    registrationNeeded(user);

    registeringUser(user, projectrole);
    return null;

  }

  private void
      registeringUser(final UserEntity user, final String projectrole) {
    if (null != user.getLogin()) {

      user.setRoles(
          Set.of(
              RoleUtil.getNameRegistered(), RoleUtil.getNameCanBePayed(),
              RoleUtil.getNameProjectRole(projectrole)
          )
      );
      userEntityRepository.save(user);
    }

    if (!CheckRoleUtil.hasRole(user, MarketConstants.REGISTERED_ROLE))
      throw new UnauthorizedException(RegisterConstants.UNREGISTERED);
  }

  private void registrationNeeded(final UserEntity user) {

    if (!CheckRoleUtil.hasRole(user, MarketConstants.CAN_BE_PAID_ROLE))
      throw new UnauthorizedException(RegisterConstants.IN_ADD_TO_ROLE);

  }

}
