package one.digitalinnovation.personal.api.services;

import one.digitalinnovation.personal.api.dto.request.PersonDTO;
import one.digitalinnovation.personal.api.dto.response.MessageResponseDTO;
import one.digitalinnovation.personal.api.entity.Person;
import one.digitalinnovation.personal.api.mapper.PersonMapper;
import one.digitalinnovation.personal.api.repository.PersonRepository;

import one.digitalinnovation.personal.api.service.PersonService;
import one.digitalinnovation.personal.api.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static one.digitalinnovation.personal.api.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)     //for mock tests
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSuccessSavedMessage() {
        PersonDTO personDTO = PersonUtils.createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);                     //valid the return just for the necessaries informations
        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson);
        MessageResponseDTO successMessage = personService.createPerson(personDTO);
        assertEquals(expectedSuccessMessage, successMessage);

    }

    private MessageResponseDTO createExpectedMessageResponse(Person expectedSavedPerson) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + expectedSavedPerson.getId())
                .build();
    }

}
