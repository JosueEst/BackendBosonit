package com.bosonit.formacion.block7crudvalidation.application;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.professor.ProfessorFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.professor.ProfessorInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.professor.ProfessorOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Person;
import com.bosonit.formacion.block7crudvalidation.domain.Professor;
import com.bosonit.formacion.block7crudvalidation.repository.PersonRepository;
import com.bosonit.formacion.block7crudvalidation.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
@Service
public class ProfessorServiceImpl implements ProfessorService{
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    PersonRepository personRepository;
    @Override
    public ProfessorOutputDto addProfessorToPerson(ProfessorInputDto professorInputDto) throws SQLIntegrityConstraintViolationException {
        Person person = personRepository.findById(professorInputDto.getId_person()).orElseThrow(EntityNotFoundException::new);
        if(person.getStudent() == null && person.getProfessor() == null){
            Professor professor = new Professor(professorInputDto);
            //person.setProfessor(professor);
            professor.setPerson(person);
            return professorRepository.save(professor).professorToProfessorOutputDto();
        }else{
            throw new SQLIntegrityConstraintViolationException ();
        }
    }

    @Override
    public ProfessorOutputDto getProfessorById(int id) {
        try{
            return professorRepository.findById(id).orElseThrow(EntityNotFoundException::new).professorToProfessorOutputDto();
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException();
        }
    }

    @Override
    public ProfessorFullOutputDto getProfessorByIdLikeNativeFull(int id) {
        return professorRepository.getProfessorByIdLikeNativeFull(id).professorToProfessorFullOutputDto();
    }

    //When we update an object Professor we must update the object Person because of the relation OneToOne; the same as
    // updateStudentById
    @Override
    public ProfessorOutputDto updateProfessorById(int id, ProfessorInputDto professorInputDto) {
        Person person = personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        professorInputDto.setId_professor(id);
        Professor professor = new Professor(professorInputDto);
        person.setProfessor(professor);
        professor.setPerson(person);
        return professorRepository.save(professor).professorToProfessorOutputDto();
    }

    @Override
    public void deleteProfessorById(int id) {
        professorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        professorRepository.deleteById(id);
    }

    @Override
    public List<ProfessorOutputDto> getAllProfessors(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return professorRepository.findAll(pageRequest)
                .getContent().stream().map(Professor::professorToProfessorOutputDto).toList();
    }
}
