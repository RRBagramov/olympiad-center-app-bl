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
@Table(name = "city")
@Entity
@NoArgsConstructor
public class City {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "city")
    private List<Olympiad> olympiads;

    public City(String name) {
        this.name = name;
    }
}


