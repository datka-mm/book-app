package com.example.bookapp.repositories;

import com.example.bookapp.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    //    "select p from Person p " +
    //         "join p.socialMediaList s join s.people sp where sp.id = p.id "
    @Query("select s from Student s join s.group g on s.id = g.id where g.id = :id")
    List<Student> getAllByGroupId(Long id);


    @Query("select s from Student s where concat(s.name, s.email) like concat('%', :word, '%') ")
    List<Student> searchStudents(@Param("word")String word);

}