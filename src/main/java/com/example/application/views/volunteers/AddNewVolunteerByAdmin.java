package com.example.application.views.volunteers;

import com.example.application.data.Role;
import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.VolunteerService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;


@PageTitle("Add new volunteer with role")
@Route(value = "volunteers/add/setrole", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@Uses(Icon.class)
public class AddNewVolunteerByAdmin extends VerticalLayout {

    private TextField id = new TextField("id");
    private TextField firstName = new TextField("firstName");
    private TextField lastName = new TextField("lastName");
    private TextField name = new TextField("name");
    private TextField password = new TextField("password");
    private TextField email = new TextField("email");
    private IntegerField phone = new IntegerField("phone");
    private ComboBox<Role> role = new ComboBox<Role>("role");
   // private VolunteerDto volunteerDto;
    private  final VolunteerService volunteerService;
   // private Binder<VolunteerDto> binder= new Binder<>(VolunteerDto.class);

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");



    public AddNewVolunteerByAdmin(VolunteerService volunteerService) {
        this.volunteerService=volunteerService;
       // this.volunteerDto=volunteerDto;
        addClassName("myaccount-view");
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        role.setItems(Role.ADMIN, Role.USER);
        add(id, firstName, lastName, name, password, phone, email, role);

        cancel.addClickListener(e -> clearForm());

        save.addClickListener(e -> {
            save();
            Notification.show( " Volunteer details stored.");
            clearForm();
        });
    }


    private void save() {
        VolunteerDto dto =new VolunteerDto();
        dto.setId(Long.valueOf(id.getValue()));
        dto.setFirstName(firstName.getValue());
        dto.setLastName(lastName.getValue());
        dto.setName(name.getValue());
        dto.setPassword(password.getValue());
        dto.setEmail(email.getValue());
        dto.setPhone(phone.getValue());
        dto.setRole(role.getValue());
        volunteerService.createVolunteer(dto);
    }

    private void clearForm() {

    }

    private Component createTitle() {
        return new H3("Create new volunteer");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(id, firstName, lastName, name, password, phone, email, role);
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
