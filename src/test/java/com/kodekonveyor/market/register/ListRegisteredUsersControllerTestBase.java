package com.kodekonveyor.market.register;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.RoleEntityRepository;
import com.kodekonveyor.authentication.UserEntityRepository;

public class ListRegisteredUsersControllerTestBase {

  @InjectMocks
  ListRegisteredUsersController listRegisteredUsersController;

  @Mock
  RoleEntityRepository roleEntityRepository;

  @Mock
  UserEntityRepository userEntityRepository;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

}
