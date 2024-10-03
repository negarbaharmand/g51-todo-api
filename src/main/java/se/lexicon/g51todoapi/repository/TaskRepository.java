package se.lexicon.g51todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.g51todoapi.domain.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    //todo: select tasks contain title
    // select tasks by person's id
    // select tasks by status
    // select tasks by date between start and end
    // select all unassigned tasks
    // select all unfinished tasks
    // select all unfinished and overdue tasks

}
