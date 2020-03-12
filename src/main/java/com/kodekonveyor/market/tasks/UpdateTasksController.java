package com.kodekonveyor.market.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.register.RegisterConstants;

@RestController
public class UpdateTasksController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @PutMapping(UrlMapConstants.TASK_UPDATE_PATH)
  public Object call() {
    final UserEntity user = authenticatedUserService.call();
    checkRole(user);
    return null;
  }

  private void checkRole(final UserEntity user) {
    if (!CheckRoleUtil.hasRole(user, MarketConstants.KODEKONVEYOR_CONTRACT))
      throw new UnauthorizedException(
          RegisterConstants.UNAUTHORIZED_NOT_ENOUGH_RIGHTS
      );
  }
}
