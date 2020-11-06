package cp.controlers;

import cp.dto.PersonDto;
import cp.exceptions.PersonNotFoundException;
import cp.model.Person;
import cp.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping("/person")
    @ResponseBody
    public ResponseEntity add(@RequestBody Person person) {
        return ResponseEntity.ok(personService.add(person));
    }

    @PostMapping("/person/batch")
    @ResponseBody
    public ResponseEntity addBatch(@RequestBody List<Person> persons) {
        return ResponseEntity.ok(personService.addBatch(persons));
    }

    @GetMapping("/person/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable String id) {
        try {
            return ResponseEntity.ok(personService.get(id));
        } catch (PersonNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/person")
    @ResponseBody
    public ResponseEntity getAll() {
        return ResponseEntity.ok(personService.getAll());
    }

    @PutMapping("/person/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable String id, @RequestBody PersonDto personDto) {
        try {
            return ResponseEntity.ok(personService.update(id, personDto));
        } catch (PersonNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/person/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable String id) {
        try {
            return ResponseEntity.ok(personService.delete(id));
        } catch (PersonNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    @DeleteMapping("/personDeleteAll")
    @ResponseBody
    public ResponseEntity clearCollection() {
        return ResponseEntity.ok(personService.clearCollection());
    }
}
