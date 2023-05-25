package com.example.bookapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(
            generator = "group_gen",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "group_gen",
            sequenceName = "group_seq",
            allocationSize = 1)
    private Long id;
    private String name;
    private String description;

    @OneToMany(cascade = {ALL},
            mappedBy = "group")
    private List<Student> students;
}
