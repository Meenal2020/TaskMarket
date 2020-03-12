package com.kodekonveyor.authentication;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.kodekonveyor.annotations.InterfaceClass;

@InterfaceClass
public class RemoteAuthentication implements Authentication {

  private static final long serialVersionUID = 1L;
  private final UserEntity user;

  public RemoteAuthentication(final UserEntity user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public Object getCredentials() {
    return user.getLogin();
  }

  @Override
  public Object getDetails() {
    return user;
  }

  @Override
  public String getName() {
    return user.getLogin();
  }

  @Override
  public Object getPrincipal() {
    return user.getLogin();
  }

  @Override
  public boolean isAuthenticated() {
    return true;
  }

  @Override
  public void setAuthenticated(final boolean isAuthenticated) {
    throw new IllegalArgumentException();
  }
}
