package com.example.application.views.newwalk;

import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.entity.WalkDto;
import com.example.application.data.service.DogService;
import com.example.application.data.service.VolunteerService;
import com.example.application.data.service.WalkService;
import com.example.application.views.MainLayout;
import com.example.application.views.walkthedog.WalkthedogView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;

@PageTitle("New walk form")
@Route(value = "register-walks", layout = MainLayout.class)
@RolesAllowed({"ADMIN", "USER"})
@Uses(Icon.class)
public class NewWalkView extends VerticalLayout{


    private DatePicker dateOfWalk = new DatePicker("Choose day of walk");
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private VolunteerService volunteerService;
    private DogService dogService;
    private WalkService walkService;
    private VolunteerDto activeVolunteer;


    public static String dogForWalk = "";

    public NewWalkView(VolunteerService volunteerService, DogService dogService, WalkService walkService){
        this.volunteerService = volunteerService;
        this.dogService = dogService;
        this.walkService = walkService;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInVolunteer = authentication.getName();
        activeVolunteer = volunteerService.fetchByUsername(loggedInVolunteer);
        addClassName("newwalk-view");
        dateOfWalk.setMin(LocalDate.now());
        add(createFormLayout());
        add(createButtonLayout());
        add(createTitle());


        cancel.addClickListener(e -> UI.getCurrent().navigate(WalkthedogView.class));

        save.addClickListener(e -> {
            if(!dateOfWalk.isEmpty()){
                WalkDto walkDto = new WalkDto();
                walkDto.setDogName(dogForWalk);
                walkDto.setVolunteerName(activeVolunteer.getName());
                walkDto.setWalkDate(dateOfWalk.getValue());
                walkService.saveWalk(walkDto);
                Notification.show("Thank You!  Visit us "+dateOfWalk.getValue(),3000, Notification.Position.MIDDLE);
                UI.getCurrent().navigate(WalkthedogView.class);
            } else {
                Notification.show("Enter date of walk", 2000, Notification.Position.MIDDLE);
            }
        });
    }


    private Component createTitle() {
        VerticalLayout verticalLayout = new VerticalLayout();
        H3 a = new H3("Creating new walk for You: ");
        H3 b = new H3("Dog name: "+ dogForWalk);
        H3 c = new H3("Username: "+ activeVolunteer.getName());
        H3 d = new H3( "Details will be send to: "+activeVolunteer.getEmail());
        verticalLayout.add(a, b, c, d);
        return verticalLayout;
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(dateOfWalk);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

    public void setDogForWalk(String dogForWalk) {
        this.dogForWalk = dogForWalk;
    }
}
