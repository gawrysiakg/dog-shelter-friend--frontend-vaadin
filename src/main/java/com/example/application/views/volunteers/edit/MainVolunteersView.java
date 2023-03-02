package com.example.application.views.volunteers.edit;

import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.VolunteerService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@Component
@Route(value = "volunteers/edit", layout = MainLayout.class)
//@RolesAllowed("ADMIN")
@PermitAll
public class MainVolunteersView extends VerticalLayout {

    private final VolunteerService volunteerService;
    private Grid<VolunteerDto> grid = new Grid<>(VolunteerDto.class);

    private TextField filter = new TextField();

    VolunteersEditView volunteersEditView;

    private Button addNewDog = new Button("Add new volunteer");

    public MainVolunteersView(VolunteerService volunteerService){
        this.volunteerService = volunteerService;
        volunteersEditView = new VolunteersEditView(this, volunteerService);

        // this.dogService=dogService;
        filter.setPlaceholder("Filter by title...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("id", "firstName", "lastName", "name", "password", "email", "phone", "role");
//chwilowo
//        addNewDog.addClickListener(e -> {
//            grid.asSingleSelect().clear(); //"czyścimy" zaznaczenie
//            volunteersEditView.setVolunteer(new VolunteerDto());    //dodajemy nowy obiekt do formularza
//        });
//        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewDog);
        HorizontalLayout mainContent = new HorizontalLayout(grid, volunteersEditView);
        mainContent.setSizeFull();
        grid.setSizeFull();
        volunteersEditView.setVolunteer(null);
      //  add(toolbar, mainContent);
 add( mainContent);
        setSizeFull();

        refresh();
        grid.asSingleSelect().addValueChangeListener(event -> volunteersEditView.setVolunteer(grid.asSingleSelect().getValue()));
    }


    public void refresh() {
        grid.setItems(volunteerService.fetchVolunteers());
    }

    private void update() {
        grid.setItems(volunteerService.fetchByUsername(filter.getValue()));
    }
}
