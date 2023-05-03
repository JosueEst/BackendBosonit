package com.bosonit.formacion.block7crudvalidation.domain;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.person.PersonFullProfessorOutputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.person.PersonFullStudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.person.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.person.PersonOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person personInputDtoToPerson  (PersonInputDto personOutputDto);

    PersonOutputDto personToPersonOutputDto (Person person);
    PersonFullStudentOutputDto personToPersonFullStudentOutputDto (Person person);
    PersonFullProfessorOutputDto personToPersonFullProfessorOutputDto (Person person);

}
