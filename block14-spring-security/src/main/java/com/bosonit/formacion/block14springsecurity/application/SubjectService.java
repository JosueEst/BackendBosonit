package com.bosonit.formacion.block14springsecurity.application;

import com.bosonit.formacion.block14springsecurity.controller.Dtos.subject.SubjectInputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.subject.SubjectOutputDto;

import java.util.List;

public interface SubjectService {

    SubjectOutputDto addSubject (SubjectInputDto subjectsInputDto);
    SubjectOutputDto getSubjectById (int id);
    SubjectOutputDto updateSubjectById (int id, SubjectInputDto subjectsInputDto);

    void deleteSubjectById (int id);
    List<SubjectOutputDto> getAllSubjects ();

    List<SubjectOutputDto> getAllStudentSubjects(int id_student);

    void addSubjectToStudent(int id_student, int id_subject);

    void addManySubjectsToStudent(int id_student, List<Integer> id_subjects);

    void deleteSubjectToStudent (int id, List<Integer> id_subjects);
    SubjectOutputDto addSubjectToProfessor (SubjectInputDto subjectInputDto);
}
