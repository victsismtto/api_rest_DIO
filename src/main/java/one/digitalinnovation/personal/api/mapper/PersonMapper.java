package one.digitalinnovation.personal.api.mapper;

import one.digitalinnovation.personal.api.dto.request.PersonDTO;
import one.digitalinnovation.personal.api.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yy")   //coverter date em dd-mm-yy para entidade
    Person toModel(PersonDTO personDTO);
    PersonDTO toDTO(Person person);

}
