package com.kodekonveyor.market.project;

import static com.kodekonveyor.market.MarketConstants.MANAGER;
import static com.kodekonveyor.market.MarketConstants.UNAUTHORIZED_PROJECT_MODIFICATION;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;
import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.register.MarketUserEntityRepository;
import com.kodekonveyor.market.tasks.TaskDTO;
import com.kodekonveyor.market.tasks.TaskEntity;
import com.kodekonveyor.market.tasks.TaskEntityRepository;
import com.kodekonveyor.market.tasks.UpdateTasksService;

@RestController
public class UpdateProjectModelController {

  @Autowired
  ProjectEntityRepository projectEntityRepository;
  @Autowired
  MilestoneEntityRepository milestoneEntityRepository;
  @Autowired
  AuthenticatedUserService authenticatedUserService;
  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;
  @Autowired
  UpdateTasksService updateTasksService;
  @Autowired
  TaskEntityRepository taskEntityRepository;

  @Autowired
  Logger logger;

  @PutMapping(UrlMapConstants.UPDATE_PROJECT_MODEL_PATH)
  public ProjectDTO
      call(final ProjectModelDTO projectModelDTO, final String projectName) {
    logger.info(LoggingMarkerConstants.PROJECT, projectName);

    final ProjectEntity project = projectEntityRepository
        .findByName(projectName).get();

    validateAuthoization(project);

    final Set<MilestoneDTO> milestones = projectModelDTO.getMilestone();
    final Set<Long> milestonIds = new HashSet<>();
    milestones.forEach((milestone) -> milestonIds.add(milestone.getId()));
    project.setMilestone(
        Sets.newHashSet(
            milestoneEntityRepository
                .findAllById(milestonIds)
        )
    );

    updateTasks(projectModelDTO);

    final ProjectEntity projectEntityUpdated =
        projectEntityRepository.save(project);

    logger.debug(
        LoggingMarkerConstants.PROJECT,
        ProjectConstants.PROJECT_DTO_RETURNED_SUCCESSFULLY + project.getId()
    );

    return getProjectDTO(projectEntityUpdated);

  }

  private void updateTasks(
      final ProjectModelDTO projectModelDTO
  ) {
    final Set<TaskDTO> projectDTOTasks = projectModelDTO.getTask();
    final List<TaskEntity> tasks = convertTaskDTOs(projectDTOTasks);
    for (final TaskEntity task : tasks) {
      final TaskEntity updatedTask = updateTasksService.call(task);
      taskEntityRepository.save(updatedTask);
    }
  }

  private List<TaskEntity> convertTaskDTOs(final Set<TaskDTO> projectDTOTasks) {
    final TaskEntity task = new TaskEntity();
    final List<TaskEntity> taskEntities = new ArrayList<>();
    for (final TaskDTO taskDTO : projectDTOTasks) {
      task.setId(taskDTO.getId());
      task.setBehaviour(taskDTO.getBehaviour());
      task.setDescription(taskDTO.getDescription());
      task.setGithubId(taskDTO.getGithubId());
      task.setService(taskDTO.getService());
      task.setMarketUser(
          marketUserEntityRepository.findById(taskDTO.getMarketUser()).get()
      );
      task.setStatus(taskDTO.getStatus());
      taskEntities.add(task);
    }
    return taskEntities;
  }

  private void validateAuthoization(final ProjectEntity projectEntity) {
    final UserEntity sessionUser = authenticatedUserService.call();

    if (!CheckRoleUtil.hasRole(sessionUser, projectEntity, MANAGER)) {
      logger.warn(
          LoggingMarkerConstants.PROJECT,
          ProjectConstants.USER_NOT_MANAGER + sessionUser.getId()
      );
      throw new UnauthorizedException(UNAUTHORIZED_PROJECT_MODIFICATION);
    }

  }

  private ProjectDTO getProjectDTO(final ProjectEntity project) {
    final ProjectDTO projectDTO = new ProjectDTO();
    projectDTO.setBudgetInCents(project.getBudgetInCents());
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
}
