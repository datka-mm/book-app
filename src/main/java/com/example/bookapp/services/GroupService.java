package com.example.bookapp.services;


import com.example.bookapp.entities.Group;
import com.example.bookapp.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository repository;

    public List<Group> findAll() {
        return repository.findAll();
    }

    public Group findByID(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NullPointerException(
                        "Course with id: " + id + " not found!"
                )
        );
    }

    public void save(Group group) {
        repository.save(group);
    }

    public void updateGroup(Long id, Group group) {
        repository.updateGroup(id, group.getName(), group.getDescription());
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
