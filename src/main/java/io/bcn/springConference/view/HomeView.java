package io.bcn.springConference.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)  // Sets root URL to this view
public class HomeView extends VerticalLayout {

    public HomeView() {
        add(new Label("Welcome to the Spring Conference Management Application!"));
        add(new Label("Use the navigation links above to access Conferences and Speakers."));
    }
}
