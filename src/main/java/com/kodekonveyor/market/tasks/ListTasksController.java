package com.kodekonveyor.market.tasks;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.register.MarketUserEntity;
import com.kodekonveyor.market.register.MarketUserEntityRepository;

@RestController
public class ListTasksController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @Autowired
  TaskRepository taskRepository;

  @GetMapping(UrlMapConstants.LIST_TASK_PATH)
  public List<TaskDTO> call() {
    final UserEntity user = authenticatedUserService.call();
    final List<MarketUserEntity> marketUserEntities =
        marketUserEntityRepository.findByLogin(user);
    MarketUserEntity marketUserEntity = new MarketUserEntity();
    if (!marketUserEntities.isEmpty())
      marketUserEntity = marketUserEntities.get(0);
    final List<TaskDTO> ret = List
        .of(
            getInProgressOrClosedTask(
                marketUserEntity, TaskStatusEnum.IN_PROGRESS
            ),
            getClosedUpForGrabTask(marketUserEntity),
            getOpenUpForGrabTask(),
            getInProgressOrClosedTask(marketUserEntity, TaskStatusEnum.DONE)
        ).stream()
        .flatMap(List::stream).map(this::convertTaskEntityToDTO).collect(Collectors.toList());
    return ret;
  }

  private TaskDTO
      convertTaskEntityToDTO(final TaskEntity taskEntity) {
    final TaskDTO taskDTO = createTaskDTO();
    taskDTO.setGithubId(taskEntity.getGithubId());
    taskDTO.setName(taskEntity.getName());
    taskDTO.setProject(taskEntity.getProject());
    taskDTO.setResponsible(taskEntity.getResponsible());
    taskDTO.setStatus(taskEntity.getStatus());
    return taskDTO;
  }

  private TaskDTO createTaskDTO() {
    return new TaskDTO();
  }

  private List<TaskEntity> getClosedUpForGrabTask(
      final MarketUserEntity marketUserEntity
  ) {
    return taskRepository.findByStatusAndResponsibleAndProjectIsPublic(
        TaskStatusEnum.UP_FOR_GRAB, marketUserEntity, false
    );
  }

  private List<TaskEntity> getInProgressOrClosedTask(
      final MarketUserEntity marketUserEntity, final TaskStatusEnum status
  ) {
    return taskRepository.findByStatusAndResponsible(
        status, marketUserEntity
    );
  }

  private List<TaskEntity> getOpenUpForGrabTask() {
    return taskRepository
        .findByStatusAndProjectIsPublic(TaskStatusEnum.UP_FOR_GRAB, true);
  }

}
