package com.kodekonveyor.market.project;

import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.register.MarketUserEntity;
import com.kodekonveyor.market.register.MarketUserEntityRepository;

@RestController
public class AddFundsToProjectController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  ProjectEntityRepository projectEntityRepository;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @Autowired
  Logger logger;

  @PutMapping(UrlMapConstants.PROJECT_BUDGET_PATH)
  public ProjectDTO call(final long projectId, final long budgetInCents) {
    logger.info(
        LoggingMarkerConstants.PROJECT, String.valueOf(projectId)
    );

    inputValidation(projectId);

    final ProjectEntity project =
        projectEntityRepository
            .findById(projectId).get();
    final UserEntity user = authenticatedUserService.call();
    final ProjectDTO projectDTO = getProjectDTO(project);
    validateBalance(user, project, budgetInCents);
    addFundsToProject(user, project, budgetInCents, projectDTO);

    logger.debug(
        LoggingMarkerConstants.PROJECT,
        ProjectConstants.PROJECT_DTO_RETURNED_SUCCESSFULLY +
            projectDTO.getId().toString()
    );
    return projectDTO;
  }

  private ProjectDTO getProjectDTO(final ProjectEntity project) {
    final ProjectDTO projectDTO = new ProjectDTO();
    projectDTO.setDescription(project.getDescription());
    projectDTO.setId(project.getId());
    projectDTO.setIsPublic(project.getIsPublic());
    projectDTO.setUrl(project.getUrl());
    projectDTO.setName(project.getName());
    projectDTO.setProjectId(project.getProjectId());
    projectDTO
        .setMilestone(project.getMilestone().stream().map(MilestoneEntity::getId).collect(Collectors.toSet()));
    projectDTO
        .setPullRequest(
            project.getPullRequest().stream().map(PullRequestEntity::getId).collect(Collectors.toSet())
        );
    projectDTO.setRole(
        project.getRole().stream().map(RoleEntity::getId).collect(Collectors.toSet())
    );
    return projectDTO;
  }

  private void inputValidation(final Long projectId) {
    if (projectId <= MarketConstants.ZERO)
      throw new ValidationException(
          MarketConstants.PROJECT_ID_NON_POSITIVE_EXCEPTION
      );

  }

  private void
      validateBalance(
          final UserEntity user, final ProjectEntity project,
          final long budgetInCents
      ) {
    final Optional<MarketUserEntity> marketUserEntity =
        marketUserEntityRepository.findByUser(user);
    final long userBalance = marketUserEntity.get().getBalanceInCents();
    if (
      userBalance - budgetInCents < 0
    )
      throw new ValidationException(
          ProjectConstants.USER_BALANCE_IS_LESS_THAN_THE_BUDGET
      );
    if (
      project.getBudgetInCents() + budgetInCents < 0
    )
      throw new ValidationException(
          ProjectConstants.BALANCE_IS_NEGATIVE
      );
  }

  private void addFundsToProject(
      final UserEntity user,
      final ProjectEntity project, final long budgetInCents,
      final ProjectDTO projectDTO
  ) {
    final Optional<MarketUserEntity> marketUserEntity =
        marketUserEntityRepository.findByUser(user);
    final long userBalance = marketUserEntity.get().getBalanceInCents();
    long updatedUserBalance;
    long updatedProjectBudget;

    updatedUserBalance = userBalance - budgetInCents;
    updatedProjectBudget =
        project.getBudgetInCents() + budgetInCents;

    marketUserEntity.get()
        .setBalanceInCents(updatedUserBalance);
    marketUserEntityRepository.save(marketUserEntity.get());
    project.setBudgetInCents(updatedProjectBudget);
    projectDTO.setBudgetInCents(updatedProjectBudget);
    projectEntityRepository.save(project);
  }
}
