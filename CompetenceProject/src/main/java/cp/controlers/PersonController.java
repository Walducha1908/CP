package cp.controlers;

import cp.dto.PersonDto;
import cp.exceptions.PersonNotFoundException;
import cp.model.Person;
import cp.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping("/person")
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping("/person/{id}")
    @ResponseBody
    public ResponseEntity getPerson(@PathVariable String id) {
        try {
            return ResponseEntity.ok(personService.getPerson(id));
        } catch (PersonNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/person/{id}")
    @ResponseBody
    public ResponseEntity getPerson(@PathVariable String id, @RequestBody PersonDto personDto) {
        try {
            return ResponseEntity.ok(personService.updatePerson(id, personDto));
        } catch (PersonNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/person/{id}")
    @ResponseBody
    public ResponseEntity deletePerson(@PathVariable String id) {
        try {
            return ResponseEntity.ok(personService.deletePerson(id));
        } catch (PersonNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
