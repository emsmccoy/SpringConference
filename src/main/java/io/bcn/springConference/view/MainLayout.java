package io.bcn.springConference.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
    }

    private void createHeader() {
        HorizontalLayout header = new HorizontalLayout();

        RouterLink conferenceLink = new RouterLink("Conferences", ConferenceView.class);
        RouterLink speakerLink = new RouterLink("Speakers", SpeakerView.class);

        header.add(conferenceLink, speakerLink);
        addToNavbar(header);
    }
}

