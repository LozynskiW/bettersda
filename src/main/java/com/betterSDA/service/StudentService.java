package com.betterSDA.service;

import com.betterSDA.model.dto.Team;
import com.betterSDA.model.dto.Student;
import com.betterSDA.model.entity.TeamEntity;
import com.betterSDA.model.entity.StudentEntity;

import com.betterSDA.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepo studentRepo;

    public void addOrUpdateStudent(Student student) {

        StudentEntity studentEntity = dtoToEntity(student);
        studentRepo.save(studentEntity);

    }

    public void deleteStudentById(Long id) {

        this.studentRepo.deleteById(id);

    }

    public Student getStudentById(Long id) {

        Optional<StudentEntity> studentEntity = studentRepo.findById(id);

        if (studentEntity.isPresent()) {
            return entityToDto(studentEntity.get());
        }

        throw new NoSuchElementException("No student found in data base");
    }

    public List<Student> getAllStudents() {
        List<StudentEntity> studentEntityList = studentRepo.findAll();

        return studentEntityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    private StudentEntity dtoToEntity(Student student) {
        return new StudentEntity.Builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .groupEntity(this.toEntity(student.getTeam()))
                .phoneNumber(student.getPhoneNumber())
                .build();
    }

    private Student entityToDto(StudentEntity studentEntity) {
        return new Student.Builder()
                .id(studentEntity.getId())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .email(studentEntity.getEmail())
                .phoneNumber(studentEntity.getPhoneNumber())
                .build();
    }

    private TeamEntity toEntity(Team team){
        return TeamEntity.builder()
                .name(team.getName())
                .build();
    }



}
