package one.digitalinnovation.personal.api.service;

import one.digitalinnovation.personal.api.dto.request.PersonDTO;
import one.digitalinnovation.personal.api.dto.response.MessageResponseDTO;
import one.digitalinnovation.personal.api.entity.Person;
import one.digitalinnovation.personal.api.mapper.PersonMapper;
import one.digitalinnovation.personal.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {        //RequestBody -> indica que o objeto (JSON) esta vindo de uma requisição

        Person personSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personSave);                     //salvar a 'person' dentro do método
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())       //mensagem antes de 'buildar'
                .build();
    }
}
