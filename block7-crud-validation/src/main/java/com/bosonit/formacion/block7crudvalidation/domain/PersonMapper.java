package com.bosonit.formacion.block7crudvalidation.domain;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.PersonOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person personOutputDtoToPerson  (PersonOutputDto personOutputDto);
    Person personInputDtoToPerson  (PersonInputDto personOutputDto);

    PersonOutputDto personToPersonOutputDto (Person person);
}
