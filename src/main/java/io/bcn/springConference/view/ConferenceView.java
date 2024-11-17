package io.bcn.springConference.view;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import io.bcn.springConference.model.Book;
import io.bcn.springConference.model.Conference;
import io.bcn.springConference.model.Speaker;
import io.bcn.springConference.repository.BookRepository;
import io.bcn.springConference.repository.ConferenceRepository;
import io.bcn.springConference.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "conferences", layout = MainLayout.class)
public class ConferenceView extends VerticalLayout {

    private final ConferenceRepository conferenceRepository;
    private final BookRepository bookRepository;
    private final SpeakerRepository speakerRepository;

    private Grid<Conference> grid = new Grid<>(Conference.class);
    private Binder<Conference> binder = new Binder<>(Conference.class);

    private ComboBox<Book> bookComboBox;
    private ComboBox<Speaker> speakerComboBox;
    private DatePicker datePicker;
    private Button saveButton;

    @Autowired
    public ConferenceView(ConferenceRepository conferenceRepository, BookRepository bookRepository, SpeakerRepository speakerRepository) {
        this.conferenceRepository = conferenceRepository;
        this.bookRepository = bookRepository;
        this.speakerRepository = speakerRepository;

        setupForm();
        setupGrid();
        add(grid, bookComboBox, speakerComboBox, datePicker, saveButton);
        updateGrid();
    }

    private void setupForm() {
        bookComboBox = new ComboBox<>("Select Book");
        bookComboBox.setItems(bookRepository.findAll());
        bookComboBox.setItemLabelGenerator(Book::getTitle);

        speakerComboBox = new ComboBox<>("Select Speaker");
        speakerComboBox.setItems(speakerRepository.findAll());
        speakerComboBox.setItemLabelGenerator(Speaker::getName);

        datePicker = new DatePicker("Date");

        saveButton = new Button("Save", e -> saveConference());

        binder.bind(bookComboBox, Conference::getBook, Conference::setBook);
        binder.bind(speakerComboBox, Conference::getSpeaker, Conference::setSpeaker);
        binder.bind(datePicker, Conference::getDate, Conference::setDate);
    }

    private void setupGrid() {
        grid.setColumns("name", "date");
        grid.addComponentColumn(conference -> {
            Speaker speaker = conference.getSpeaker();
            Avatar avatar = new Avatar(speaker.getName());
            avatar.setWidth("50px");
            avatar.setHeight("50px");
            return avatar;
        }).setHeader("Speaker Avatar");
    }

    private void saveConference() {
        Conference conference = new Conference();
        binder.writeBeanIfValid(conference);
        conferenceRepository.save(conference);
        updateGrid();
        binder.readBean(null);
    }

    private void updateGrid() {
        grid.setItems(conferenceRepository.findAll());
    }
}

