package se.lexicon.g51todoapi.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import se.lexicon.g51todoapi.domain.entity.Person;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TaskDTOForm {

    private Long id;

    private String title;

    private String description;

    private LocalDate deadline;

    private boolean done;

    private PersonDTOForm person;

}