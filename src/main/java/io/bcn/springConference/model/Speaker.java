package io.bcn.springConference.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "speaker", cascade = CascadeType.ALL)
    private List<Conference> conferences = new ArrayList<>();
}