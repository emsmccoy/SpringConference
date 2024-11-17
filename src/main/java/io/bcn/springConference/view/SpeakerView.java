package io.bcn.springConference.view;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import io.bcn.springConference.model.Speaker;
import io.bcn.springConference.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "speakers", layout = MainLayout.class)
public class SpeakerView extends VerticalLayout {

    private final SpeakerRepository speakerRepository;
    private Grid<Speaker> grid = new Grid<>(Speaker.class);
    private Binder<Speaker> binder = new Binder<>(Speaker.class);

    private TextField nameField;
    private Button saveButton;

    @Autowired
    public SpeakerView(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;

        setupForm();
        setupGrid();
        add(grid, nameField, saveButton);
        updateGrid();
    }

    private void setupForm() {
        nameField = new TextField("Speaker Name");
        saveButton = new Button("Save", e -> saveSpeaker());

        binder.bind(nameField, Speaker::getName, Speaker::setName);
    }

    private void setupGrid() {
        grid.addComponentColumn(speaker -> {
            Avatar avatar = new Avatar(speaker.getName(), "Avatar");
            avatar.setWidth("50px");
            avatar.setHeight("50px");
            return avatar;
        }).setHeader("Avatar");
        grid.setColumns("name");
    }

    private void saveSpeaker() {
        Speaker speaker = new Speaker();
        binder.writeBeanIfValid(speaker);
        speakerRepository.save(speaker);
        updateGrid();
        binder.readBean(null);
    }

    private void updateGrid() {
        grid.setItems(speakerRepository.findAll());
    }
}
