package com.example.bookapp.services;

import com.example.bookapp.entities.Group;
import com.example.bookapp.entities.Student;
import com.example.bookapp.exceptions.MyException;
import com.example.bookapp.repositories.GroupRepository;
import com.example.bookapp.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final GroupRepository groupRepository;

    public List<Student> getAllStudentByGroupId(Long groupId) {
        return repository.getAllByGroupId(groupId);
    }

    public void save(Long groupId, Student student) {
        Group group = groupRepository.findById(groupId).orElseThrow(() ->
                new NullPointerException(
                        "Group with id: " + groupId + " not found!"
                )
        );

        student.setGroup(group);
        group.getStudents().add(student);
        repository.save(student);
    }

    public Student getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NullPointerException("Student with id: " + id + " not found!"
                )
        );
    }

    public void updateStudent(Long id, Student student) {
        Student student1 = repository.findById(id).orElseThrow(() ->
                new NullPointerException("Student with id: " + id + " not found!"
                )
        );

        student1.setName(student1.getName());
        student1.setEmail(student.getEmail());
        student1.setAge(student.getAge());
        repository.save(student1);
    }


    public void deleteStudentById(Long studentId) {

    }
}
