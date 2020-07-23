package com.kodekonveyor.market.register;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;

import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.market.payment.LegalFormEntityTestData;
import com.kodekonveyor.market.payment.PaymentDetailEntityTestData;

@Generated("by zenta-tools")
public class MarketUserEntityTestData {

  public final static MarketUserEntity get() {
    final MarketUserEntity marketUserEntity = new MarketUserEntity();
    marketUserEntity.setId(MarketUserTestData.ID);
    marketUserEntity
        .setPaymentDetail(Set.of(PaymentDetailEntityTestData.get()));
    marketUserEntity.setIsTermsAccepted(MarketUserTestData.IS_TERMS_ACCEPTED);
    marketUserEntity.setEmail(MarketUserTestData.EMAIL);
    marketUserEntity.setLegalAddress(MarketUserTestData.LEGAL_ADDRESS);
    marketUserEntity.setLegalName(MarketUserTestData.LEGAL_NAME);
    marketUserEntity.setPersonalName(MarketUserTestData.PERSONAL_NAME);
    marketUserEntity.setUser(UserEntityTestData.get());
    marketUserEntity.setBalanceInCents(MarketUserTestData.BALANCE_IN_CENTS);
    marketUserEntity.setLegalForm(LegalFormEntityTestData.get());

    return marketUserEntity;
  }

  public static MarketUserEntity getAcceptedContractuser() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setIsTermsAccepted(true);
    return marketUserEntity;
  }

  public static MarketUserEntity getUnacceptedContractuser() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setId(MarketUserTestData.ID_CAN_BE_PAID);
    marketUserEntity.setUser(UserEntityTestData.getContractTermsNotAccepted());
    marketUserEntity.setIsTermsAccepted(false);
    return marketUserEntity;
  }

  public static MarketUserEntity getRoleCanBePaid() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setId(MarketUserTestData.ID_CAN_BE_PAID);
    marketUserEntity.setUser(UserEntityTestData.getRoleCanbePaid());
    return marketUserEntity;
  }

  public static MarketUserEntity getRoleKodeKonveyorContract() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setId(MarketUserTestData.ID_KODEKONVEYOR_CONTRACT);
    marketUserEntity.setUser(UserEntityTestData.getRoleKodekonveyorContract());
    return marketUserEntity;
  }

  public static MarketUserEntity getIsTerrmsAcceptedFalse() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setId(MarketUserTestData.ID_IS_TERMS_ACCEPTED_FALSE);
    marketUserEntity.setUser(UserEntityTestData.getContractTermsNotAccepted());
    marketUserEntity.setIsTermsAccepted(false);
    return marketUserEntity;
  }

  public static MarketUserEntity getIdNewlySaved() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setId(MarketUserTestData.ID_NOT_IN_DATABASE);
    marketUserEntity.setBalanceInCents(0L);
    marketUserEntity.setUser(UserEntityTestData.getIdNoMarketUser());
    marketUserEntity.setPaymentDetail(new HashSet<>());
    return marketUserEntity;
  };

  public static MarketUserEntity getIdLoadedFromDB() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setId(MarketUserTestData.ID_NOT_IN_DATABASE);
    marketUserEntity.setBalanceInCents(0L);
    marketUserEntity.setUser(UserEntityTestData.getIdNoMarketUser());
    marketUserEntity.setPaymentDetail(new HashSet<>());
    return marketUserEntity;
  }

  public static MarketUserEntity getIdInNullDatabase() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setId(MarketUserTestData.ID_NOT_IN_DATABASE);
    marketUserEntity.setUser(UserEntityTestData.getIdInNullDatabase());
    marketUserEntity.setPaymentDetail(null);
    return marketUserEntity;
  };

  public static MarketUserEntity getRoleManager() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setId(MarketUserTestData.ID_MANAGER);
    marketUserEntity.setUser(UserEntityTestData.getRoleProjectManager());
    return marketUserEntity;
  }

  public static MarketUserEntity getLessBalance() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setBalanceInCents(MarketUserTestData.LESS_BALANCE);
    return marketUserEntity;
  }

  public static MarketUserEntity getZeroBalance() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setBalanceInCents(MarketUserTestData.ZERO_BALANCE);
    return marketUserEntity;
  }

  public static MarketUserEntity getUpdatedUserBalance() {
    final MarketUserEntity marketUserEntity = getRoleManager();
    marketUserEntity.setBalanceInCents(
        marketUserEntity.getBalanceInCents() - MarketUserTestData.LESS_BALANCE
    );
    return marketUserEntity;
  }

  public static MarketUserEntity getRoleProjectManager() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setUser(UserEntityTestData.getRoleProjectManager());
    return marketUserEntity;
  }

  public static MarketUserEntity getBalanceUpdatedMarketUser() {
    final MarketUserEntity marketUserEntity = getRoleProjectManager();
    marketUserEntity
        .setBalanceInCents(MarketUserTestData.USER_BALANCE_AFTER_DEDUCTION);
    return marketUserEntity;
  }

  public static Object getRoleProjectManagerEqualBudget() {
    final MarketUserEntity marketUserEntity = getRoleProjectManager();
    marketUserEntity.setBalanceInCents(MarketUserTestData.BUDGET_IN_CENTS);
    return marketUserEntity;
  }

  public static Object getRoleProjectManagerMoreThanBudget() {
    final MarketUserEntity marketUserEntity = getRoleProjectManager();
    marketUserEntity
        .setBalanceInCents(MarketUserTestData.BALANCE_MORE_THAN_BUDGET);
    return marketUserEntity;
  }

  public static MarketUserEntity getPrivateProjectCoder() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setUser(UserEntityTestData.getPrivateProjectCoder());
    return marketUserEntity;
  }

  public static MarketUserEntity getRoleRegistered() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setId(MarketUserTestData.ID_REGISTERED);
    marketUserEntity.setUser(UserEntityTestData.getRoleRegistered());
    return marketUserEntity;
  }

}
