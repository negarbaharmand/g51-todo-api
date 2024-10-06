package se.lexicon.g51todoapi.service;

import se.lexicon.g51todoapi.domain.dto.TaskDTOForm;
import se.lexicon.g51todoapi.domain.dto.TaskDTOView;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    TaskDTOView create(TaskDTOForm taskDTOForm);

    TaskDTOView findById(Long taskId);

    void update(TaskDTOForm taskDTOForm);

    void delete(Long id);

    List<TaskDTOView> findTasksByPersonId(Long personId);

    List<TaskDTOView> findTasksBetweenStartAndEndDate(LocalDate start, LocalDate end);

    List<TaskDTOView> findAllUnassignedTodoItems();

    List<TaskDTOView> findAllUnfinishedAndOverdue();

    TaskDTOView addTaskToPerson(Long personId, TaskDTOForm taskDTOForm);
}
