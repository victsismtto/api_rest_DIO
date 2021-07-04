package one.digitalinnovation.personal.api.service;

import one.digitalinnovation.personal.api.dto.request.PersonDTO;
import one.digitalinnovation.personal.api.dto.response.MessageResponseDTO;
import one.digitalinnovation.personal.api.entity.Person;
import one.digitalinnovation.personal.api.exception.PersonNotFoundException;
import one.digitalinnovation.personal.api.mapper.PersonMapper;
import one.digitalinnovation.personal.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {               //RequestBody -> shows that a JSON object is coming from a request

        Person personSave = personMapper.toModel(personDTO);

    Person savedPerson = personRepository.save(personSave);                     //salve the 'person' inside of method
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)                                       //transform the entity to DTO
                .collect(Collectors.toList());                                  //after the change, retun the list
    }

    public PersonDTO findById(long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        //Optional<Person> optionalPerson = personRepository.findById(id);    Optional -> shos if a person exists (API)

        return personMapper.toDTO(person);
    }


    public void delete(Long id) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id , String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
