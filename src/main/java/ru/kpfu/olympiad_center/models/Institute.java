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
@Table(name = "institute")
@Entity
@NoArgsConstructor
public class Institute {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "institute")
    private List<Student> students;

    public Institute(String institute) {
        this.name = institute;
    }
}
