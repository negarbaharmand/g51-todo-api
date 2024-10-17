package se.lexicon.g51todoapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g51todoapi.domain.dto.PersonDTOForm;
import se.lexicon.g51todoapi.domain.dto.PersonDTOView;
import se.lexicon.g51todoapi.service.PersonService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL
@RestController
@RequestMapping("/api/v1/persons")
@Validated
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonDTOView> createPerson(@Valid @RequestBody PersonDTOForm personDtoForm) {
        PersonDTOView createdPerson = personService.create(personDtoForm);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTOView> getPerson(@PathVariable Long id) {
        PersonDTOView person = personService.findById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonDTOView>> getAllPersons() {
        List<PersonDTOView> persons = personService.findAll();
        return ResponseEntity.ok(persons);
    }

    @PutMapping
    public ResponseEntity<PersonDTOView> updatePerson(@Valid @RequestBody PersonDTOForm personDtoForm) {
        PersonDTOView updatedPerson = personService.update(personDtoForm);
        if (updatedPerson != null) {
            return ResponseEntity.ok(updatedPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
