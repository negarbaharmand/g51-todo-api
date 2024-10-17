package se.lexicon.g51todoapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g51todoapi.domain.dto.TaskDTOForm;
import se.lexicon.g51todoapi.domain.dto.TaskDTOView;
import se.lexicon.g51todoapi.service.TaskService;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL
@RestController
@RequestMapping("/api/v1/tasks")
@Validated
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTOView> doCreateTask(@Valid @RequestBody TaskDTOForm dtoForm) {
        TaskDTOView createdTodoItem = taskService.create(dtoForm);
        return new ResponseEntity<>(createdTodoItem, HttpStatus.CREATED);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTOView> doGetTaskById(@PathVariable Long taskId) {
        TaskDTOView taskDTOView = taskService.findById(taskId);
        return new ResponseEntity<>(taskDTOView, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Void> doUpdateTodoItem(@Valid @RequestBody TaskDTOForm dtoForm
    ) {
        taskService.update(dtoForm);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> doDeleteTaskById(@PathVariable Long taskId) {
        taskService.delete(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<TaskDTOView>> doGetTaskByPersonId(
            @PathVariable Long personId
    ) {
        List<TaskDTOView> todoItems = taskService.findTasksByPersonId(personId);
        return new ResponseEntity<>(todoItems, HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<TaskDTOView>> doGetTasksBetweenDates(
            @RequestParam("start")
            @NotEmpty
            @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Start date must be in the format yyyy-MM-dd")
            @Past(message = "Start date must be in the past")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate start,

            @RequestParam("end")
            @NotEmpty
            @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "End date must be in the format yyyy-MM-dd")
            @Future(message = "End date must be in the future")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate end
    ) {
        List<TaskDTOView> todoItems = taskService.findTasksBetweenStartAndEndDate(start, end);
        return new ResponseEntity<>(todoItems, HttpStatus.OK);
    }

    @GetMapping("/unassigned")
    public ResponseEntity<List<TaskDTOView>> getAllUnassignedTodoItems() {
        List<TaskDTOView> todoItems = taskService.findAllUnassignedTodoItems();
        return new ResponseEntity<>(todoItems, HttpStatus.OK);
    }

    @GetMapping("/unfinished-overdue")
    public ResponseEntity<List<TaskDTOView>> getUnfinishedAndOverdueTodoItems() {
        List<TaskDTOView> todoItems = taskService.findAllUnfinishedAndOverdue();
        return new ResponseEntity<>(todoItems, HttpStatus.OK);
    }

}
