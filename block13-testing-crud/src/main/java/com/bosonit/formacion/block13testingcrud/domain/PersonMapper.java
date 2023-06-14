package com.bosonit.formacion.block13testingcrud.domain;

import com.bosonit.formacion.block13testingcrud.controller.Dtos.PersonInputDto;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.PersonOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person personOutputDtoToPerson  (PersonOutputDto personOutputDto);
    Person personInputDtoToPerson  (PersonInputDto personOutputDto);

    PersonOutputDto personToPersonOutputDto (Person person);
    PersonOutputDto updatePersonById (int id, PersonInputDto personInputDto);
}
