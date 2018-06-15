package ru.kpfu.olympiad_center.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 29.04.2018
 *
 * @author Robert Bagramov.
 */
@Data
@Table(name = "faculty")
@Entity
@NoArgsConstructor
public class Faculty {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    public Faculty(String faculty) {
        this.name = faculty;
    }
}
