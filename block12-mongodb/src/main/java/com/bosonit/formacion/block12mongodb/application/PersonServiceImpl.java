package com.bosonit.formacion.block12mongodb.application;

import com.bosonit.formacion.block12mongodb.controller.PersonInputDto;
import com.bosonit.formacion.block12mongodb.controller.PersonOutputDto;
import com.bosonit.formacion.block12mongodb.domain.Persona;
import com.bosonit.formacion.block12mongodb.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block12mongodb.exceptions.UnprocessableEntityException;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    MongoTemplate mongoTemplate;

    @PostConstruct
    public void borrarRegistros() {
        mongoTemplate.dropCollection(Persona.class);
    }

    @Override
    public PersonOutputDto getPersonById (String id){
        ObjectId objectId = new ObjectId(id);
        Persona persona = mongoTemplate.findById(objectId, Persona.class);
        if(persona == null){
            throw new EntityNotFoundException("El usuario con id: '"+id+"' no se encuentra en la base de datos");
        }
        return persona.personToPersonOutputDto();
    }
    @Override
    public PersonOutputDto getPersonByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        Persona persona = mongoTemplate.findOne(query, Persona.class);
        if(persona == null){
            throw new EntityNotFoundException("El usuario con nombre: '"+name+"' no se encuentra en la base de datos");
        }
        return  persona.personToPersonOutputDto();
    }

    //Return all persons paginated
    @Override
    public List<PersonOutputDto> getAllPersons(int pageNumber, int pageSize){
        Query query = new Query();
        query.skip((long) pageNumber * pageSize);
        query.limit(pageSize);
        List<Persona> personas = mongoTemplate.find(query, Persona.class);

        return personas.stream().map(Persona::personToPersonOutputDto).toList();
    }
    @Override
    public PersonOutputDto addPerson (PersonInputDto personInputDto) throws UnprocessableEntityException {
        if(personInputDto.getName().isBlank()){
            throw new UnprocessableEntityException("Debe insertar un nombre");
        }
        if(personInputDto.getSurname().isBlank()){
            throw new UnprocessableEntityException("Debe insertar un apellido");
        }
        if(personInputDto.getAge().isBlank()){
            throw new UnprocessableEntityException("Debe insertar una edad");
        }
        if(personInputDto.getLocation().isBlank()){
            throw new UnprocessableEntityException("Debe introducir una localización");
        }


        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(personInputDto.getName())
                .andOperator(Criteria.where("surname").is(personInputDto.getSurname())
                        .andOperator(Criteria.where("age").is(personInputDto.getAge()))
                        ));

        Persona persona = new Persona(personInputDto);
        Persona buscarPersona = mongoTemplate.findOne(query, Persona.class);
        if(buscarPersona != null){
                throw new UnprocessableEntityException("Ya existe una persona con esas propiedades en la base de datos");
        }else{
            return mongoTemplate.save(persona).personToPersonOutputDto();
        }

    }

    @Override
    public PersonOutputDto updatePersonById(String id, PersonInputDto personInputDto) throws UnprocessableEntityException, EntityNotFoundException {
        try{
            ObjectId objectId = new ObjectId(id);
            Persona persona = mongoTemplate.findById(objectId, Persona.class);
            if(persona == null){
                throw new EntityNotFoundException("La persona no existe en la base de datos");
            }else{
                if(personInputDto.getName().isBlank()){
                    throw new UnprocessableEntityException("Debe insertar un nombre");
                }
                if(personInputDto.getSurname().isBlank()){
                    throw new UnprocessableEntityException("Debe insertar un apellido");
                }
                if(personInputDto.getAge().isBlank()){
                    throw new UnprocessableEntityException("Debe insertar una edad");
                }
                if(personInputDto.getLocation().isBlank()){
                    throw new UnprocessableEntityException("Debe introducir una localización");
                }
                persona.setName(personInputDto.getName());
                persona.setSurname(personInputDto.getSurname());
                persona.setAge(personInputDto.getAge());
                persona.setLocation(personInputDto.getLocation());

                return mongoTemplate.save(persona).personToPersonOutputDto();
            }
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException ("Usuario no encontrado");
        } catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException();
        }
    }
    @Override
    public void deletePersonById(String id) throws EntityNotFoundException{
        try{
            ObjectId objectId = new ObjectId(id);
            Persona persona = mongoTemplate.findById(objectId, Persona.class);
            if(persona == null){
                throw new EntityNotFoundException("La persona no existe en la base de datos");
            }else{
                mongoTemplate.remove(persona);
            }
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException ("Usuario no encontrado");
        }
    }


}
