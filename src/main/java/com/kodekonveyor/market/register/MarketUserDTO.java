package com.kodekonveyor.market.register;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MarketUserDTO {

  private String auth0id;
  private String login;

}