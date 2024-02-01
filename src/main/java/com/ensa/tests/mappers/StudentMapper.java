package com.ensa.tests.mappers;

import com.ensa.tests.dtos.StudentDto;
import com.ensa.tests.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

@Getter
@Setter
public class StudentMapper {
    private final ModelMapper modelMapper=new ModelMapper();

    public StudentDto getStudentDtoFromStudent(final Student student){
        return modelMapper.map(student, StudentDto.class);
    }

    public Student getStudentFromStudentDto(final StudentDto studentDto){
        return modelMapper.map(studentDto, Student.class);
    }

    public List<StudentDto> getStudentDtosFromStudents(final List<Student> students){
        return students.stream().map(this::getStudentDtoFromStudent).toList();
    }

    public List<Student> getStudentsFromStudentsDtos(final List<StudentDto> studentDtos){
        return studentDtos.stream().map(this::getStudentFromStudentDto).toList();
    }

}
