package se.lexicon.g51todoapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g51todoapi.domain.entity.Person;
import se.lexicon.g51todoapi.domain.entity.Task;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class TaskRepositoryTest {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PersonRepository personRepository;

    private Person person;

    @BeforeEach
    void setUp() {
        LocalDate today = LocalDate.now();
        person = personRepository.save(new Person("John"));

        taskRepository.save(new Task("Title 1", "task todo 1", today, person));
        taskRepository.save(new Task("Title 2", "task todo 2", today.plusDays(1), true, person));
        taskRepository.save(new Task("Title 3", "task todo 3", today.plusDays(2), person));
        taskRepository.save(new Task("Title 4", "task todo 4", today.minusDays(34)));

    }

    @Test
    public void testFindByTitleContains() {
        //Act
        List<Task> byTitleContains = taskRepository.findByTitleContains("Title");
        //Assert
        assertEquals(4, byTitleContains.size());

    }

    @Test
    void testFindByPerson_Id() {
        //Arrange
        Long id = person.getId();
        //Act
        List<Task> byPersonId = taskRepository.findByPerson_Id(id);
        //Assert
        assertEquals(3, byPersonId.size());
    }

    @Test
    void testFindByDone() {
        //Act
        List<Task> byDone = taskRepository.findByDone(false);

        //Assert
        assertEquals(3, byDone.size());
    }

    @Test
    void testSelectTasksBetweenDates() {
        //Arrange
        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.now().plusDays(1);

        //Act
        List<Task> tasks = taskRepository.selectTasksBetweenDates(from, to);

        //Assert
        assertEquals(2, tasks.size());
    }

    @Test
    void testFindByPersonIsNull() {

        List<Task> byPersonIsNull = taskRepository.findByPersonIsNull();

        assertEquals(1, byPersonIsNull.size());
        assertEquals("Title 4", byPersonIsNull.get(0).getTitle());
    }


    @Test
    void testSelectUnFinishedTasks() {

        //Act
        List<Task> tasks = taskRepository.selectUnFinishedTasks();

        //Assert
        assertEquals(3, tasks.size());
    }

    @Test
    public void testFindAllUnfinishedAndOverdue() {
        // Act
        List<Task> unfinishedAndOverdue = taskRepository.selectUnFinishedAndOverdueTasks();

        // Assert
        assertEquals(1, unfinishedAndOverdue.size());
    }
}