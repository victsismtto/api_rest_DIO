package one.digitalinnovation.personal.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(long id) {
        super("Person not found with id = " + id);      //resposta para a procura de um id nao existente no BD
    }
}
