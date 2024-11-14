package io.bcn.springConference.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "speaker_id", nullable = false)
    private Speaker speaker;
}
