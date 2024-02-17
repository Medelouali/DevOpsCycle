package com.ensa.tests.mappers;

import com.ensa.tests.dtos.StudentDto;
import com.ensa.tests.entities.Client;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

@Getter
@Setter
public class StudentMapper {
    private final ModelMapper modelMapper=new ModelMapper();

    public StudentDto getStudentDtoFromStudent(final Client client){
        return modelMapper.map(client, StudentDto.class);
    }

    public Client getStudentFromStudentDto(final StudentDto studentDto){
        return modelMapper.map(studentDto, Client.class);
    }

    public List<StudentDto> getStudentDtosFromStudents(final List<Client> clients){
        return clients.stream().map(this::getStudentDtoFromStudent).toList();
    }

    public List<Client> getStudentsFromStudentsDtos(final List<StudentDto> studentDtos){
        return studentDtos.stream().map(this::getStudentFromStudentDto).toList();
    }

}
