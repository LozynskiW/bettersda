package com.betterSDA.service;

import com.betterSDA.model.dto.Group;
import com.betterSDA.model.dto.Teacher;
import com.betterSDA.model.entity.GroupEntity;
import com.betterSDA.model.entity.TeacherEntity;
import com.betterSDA.repo.TeacherRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepo teacherRepo;

    public void addOrUpdateTeacher(Teacher teacher) {

        TeacherEntity teacherEntity = dtoToEntity(teacher);
        teacherRepo.save(teacherEntity);

    }

    public void deleteTeacherById(Long id) {

        this.teacherRepo.deleteById(id);

    }

    public Teacher getTeacherById(Long id) {

        Optional<TeacherEntity> teacherEntity = teacherRepo.findById(id);

        if (teacherEntity.isPresent()) {
            return entityToDto(teacherEntity.get());
        }

        throw new NoSuchElementException("No student found in data base");
    }

    public List<Teacher> getAllStudents() {
        List<TeacherEntity> teacherEntityList = teacherRepo.findAll();

        return teacherEntityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    private TeacherEntity dtoToEntity(Teacher teacher) {
        return new TeacherEntity.Builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .email(teacher.getEmail())
                .phoneNumber(teacher.getPhoneNumber())
                .build();
    }

    private Teacher entityToDto(TeacherEntity teacherEntity) {
        return new Teacher.Builder()
                .id(teacherEntity.getId())
                .firstName(teacherEntity.getFirstName())
                .lastName(teacherEntity.getLastName())
                .email(teacherEntity.getEmail())
                .phoneNumber(teacherEntity.getPhoneNumber())
                .build();
    }

    private GroupEntity toEntity(Group group){
        return GroupEntity.builder()
                .name(group.getName())
                .build();
    }
}

