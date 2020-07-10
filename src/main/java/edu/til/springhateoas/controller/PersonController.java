package edu.til.springhateoas.controller;

import edu.til.springhateoas.component.PersonResourceAssembler;
import edu.til.springhateoas.model.Person;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private List<Person> personList = new ArrayList<>();
    private PersonResourceAssembler personResourceAssembler;

    public PersonController(PersonResourceAssembler personResourceAssembler) {
        this.personResourceAssembler = personResourceAssembler;
        personList.add(Person.builder().id(1).name("Jack").age(37).build());
        personList.add(Person.builder().id(2).name("Hugo").age(34).build());
        personList.add(Person.builder().id(3).name("Kate").age(33).build());
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Person>>> getAllPersons() {

        return ResponseEntity.ok(personResourceAssembler.toCollectionModel(personList));
    }

    @GetMapping(value = "/{personId}")
    public ResponseEntity<EntityModel<Person>> getOnePerson(@PathVariable int personId) {
        EntityModel<Person> entityModel = personResourceAssembler.toModel(personList.get(personId - 1));
        return ResponseEntity.ok(entityModel);
    }

}
