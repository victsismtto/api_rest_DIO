package one.digitalinnovation.personal.api.service;

import one.digitalinnovation.personal.api.dto.response.MessageResponseDTO;
import one.digitalinnovation.personal.api.entity.Person;
import one.digitalinnovation.personal.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person) {        //RequestBody -> indica que o objeto (JSON) esta vindo de uma requisição
        Person savedPerson = personRepository.save(person);                     //salvar a 'person' dentro do método
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())       //mensagem antes de 'buildar'
                .build();
    }
}
