package se.lexicon.g51todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.g51todoapi.domain.entity.Task;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitleContains(String title);

    List<Task> findByPerson_Id(Long personId);


    List<Task> findByDone(boolean done);


    @Query("select t from Task t where t.deadline between :from and :to")
    List<Task> selectTasksBetweenDates(@Param("from") LocalDate from, @Param("to") LocalDate to);

    List<Task> findByDeadlineBetween(LocalDate from, LocalDate to);


    List<Task> findByPersonIsNull();


    @Query("select t from Task t where t.done = false")
    List<Task> selectUnFinishedTasks();


    @Query("select t from Task t where t.done = false and current_date > t.deadline ")
    List<Task> selectUnFinishedAndOverdueTasks();

}
