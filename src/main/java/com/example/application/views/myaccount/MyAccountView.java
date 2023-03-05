package com.example.application.views.myaccount;

import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.VolunteerService;
import com.example.application.views.MainLayout;
import com.example.application.views.home.MydogshelterView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.annotation.security.PermitAll;

@PageTitle("My account")
@Route(value = "volunteers/details", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class MyAccountView extends VerticalLayout{


    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField name = new TextField("username");
    private TextField password = new TextField("Password");
    private TextField email = new TextField("E-mail");
    private IntegerField phone = new IntegerField("Phone number");
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    VolunteerDto userLoggedIn;
    private  final VolunteerService volunteerService;



    public MyAccountView(VolunteerService volunteerService) {
       this.volunteerService=volunteerService;

        addClassName("myaccount-view");

        add(createTitle());
        add(firstName, lastName, name, password, phone, email);
        add(createFormLayout());
        add(createButtonLayout());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        updateForm(currentPrincipalName);

        cancel.addClickListener(e -> UI.getCurrent().navigate(MydogshelterView.class));
        save.addClickListener(e -> {
            save();
            updateForm(currentPrincipalName);

            Notification.show(" Volunteer details stored.", 1500, Notification.Position.MIDDLE);

        });

    }


    private void save() {
        userLoggedIn.setFirstName(firstName.getValue());
        userLoggedIn.setLastName(lastName.getValue());
        userLoggedIn.setName(name.getValue());
        userLoggedIn.setPassword(password.getValue());
        userLoggedIn.setEmail(email.getValue());
        userLoggedIn.setPhone(phone.getValue());

        volunteerService.updateUser(userLoggedIn);
    }



    private void updateForm(String currentPrincipalName){
        userLoggedIn = volunteerService.fetchByUsername(currentPrincipalName);
        name.setValue(userLoggedIn.getName());
        password.setValue(userLoggedIn.getPassword());
        firstName.setValue(userLoggedIn.getFirstName());
        lastName.setValue(userLoggedIn.getLastName());
        email.setValue(userLoggedIn.getEmail());
        phone.setValue(userLoggedIn.getPhone());
    }

    private Component createTitle() {
        return new H3("Here You can edit your personal information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(firstName, lastName, name, password, phone, email);
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
