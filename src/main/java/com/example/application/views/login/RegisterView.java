package com.example.application.views.login;

import com.example.application.data.Role;
import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.VolunteerService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Register")
@Route(value = "register", layout = MainLayout.class)
@AnonymousAllowed
public class RegisterView extends Div{//Composite Vertical layout

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField name = new TextField("Username");
    private TextField password = new TextField("Password");
    private EmailField email = new EmailField("Email address");
    private IntegerField phone = new IntegerField("phone");
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private Binder<VolunteerDto> binder = new Binder<>(VolunteerDto.class);



    public RegisterView(VolunteerService volunteerService) {
        addClassName("newwalk-view");
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            VolunteerDto volunteerDto = new VolunteerDto();
            volunteerDto.setFirstName(firstName.getValue());
            volunteerDto.setLastName(lastName.getValue());
            volunteerDto.setName(name.getValue());
            volunteerDto.setPassword(password.getValue());
            volunteerDto.setEmail(email.getValue());
            volunteerDto.setPhone(phone.getValue());
            volunteerDto.setRole(Role.USER);

            if(volunteerService.fetchVolunteers().size()==0){
                volunteerDto.setRole(Role.ADMIN);
            }

            volunteerService.createVolunteer(volunteerDto);
            VolunteerDto fromRepo = volunteerService.fetchByUsername(volunteerDto.getName());
            if(fromRepo==null){
                Notification.show("Not registered", 1500, Notification.Position.MIDDLE);
            } else {
                Notification.show(fromRepo.getName()+ " registered. Now log in");
            }

            clearForm();
            UI.getCurrent().navigate("login");
        });
    }

    private void clearForm() {
        binder.setBean(new VolunteerDto());
    }

    private com.vaadin.flow.component.Component createTitle() {
        return new H3("New volunteer form");
    }

    private com.vaadin.flow.component.Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(firstName, lastName, name, password, email, phone);
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


}
