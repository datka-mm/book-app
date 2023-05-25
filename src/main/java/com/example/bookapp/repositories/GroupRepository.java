package com.example.bookapp.repositories;

import com.example.bookapp.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("update Group set name = :name, description = :description where id = :id")
    void updateGroup(@Param("id") Long id, @Param("name") String name, @Param("description") String description);

    @Query("delete from Group g where g.id = :id")
    void deleteGroupById(Long id);

    @Query("select g from Group g order by g.name")
    List<Group> getAll();
}