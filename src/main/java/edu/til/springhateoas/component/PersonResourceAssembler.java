package edu.til.springhateoas.component;

import edu.til.springhateoas.controller.PersonController;
import edu.til.springhateoas.model.Person;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Component;

@Component
public class PersonResourceAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {

    @Override
    public EntityModel<Person> toModel(Person personDto) {
        return EntityModel.of(personDto,
                linkTo(methodOn(PersonController.class).getOnePerson(personDto.getId())).withSelfRel(),
                linkTo(methodOn(PersonController.class).getAllPersons()).withRel("list"));
    }
}
