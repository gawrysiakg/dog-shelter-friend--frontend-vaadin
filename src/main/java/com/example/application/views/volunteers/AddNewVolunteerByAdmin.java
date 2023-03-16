package com.example.application.views.volunteers;

import com.example.application.data.Role;
import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.VolunteerService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
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
    private  final VolunteerService volunteerService;
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");



    public AddNewVolunteerByAdmin(VolunteerService volunteerService) {
        this.volunteerService=volunteerService;
        addClassName("myaccount-view");
        add(createFormLayout());
        role.setItems(Role.ADMIN, Role.USER);
        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            save();
            clearForm();
        });
    }


    private void save() {
        if(firstName.getValue().isEmpty()||lastName.getValue().isEmpty()||name.getValue().isEmpty()||password.getValue().isEmpty()
                ||email.getValue().isEmpty()||phone.isEmpty() ){
            Notification.show("Fill all positions", 1000, Notification.Position.MIDDLE);
        } else {
            VolunteerDto dto = new VolunteerDto();
            dto.setId(Long.valueOf(id.getValue()));
            dto.setFirstName(firstName.getValue());
            dto.setLastName(lastName.getValue());
            dto.setName(name.getValue());
            dto.setPassword(password.getValue());
            dto.setEmail(email.getValue());
            dto.setPhone(phone.getValue());
            dto.setRole(role.getValue());
            volunteerService.createVolunteer(dto);
            Notification.show( " Volunteer details stored.");
        }
    }

    private void clearForm() {
    }

    private Component createFormLayout() {
        H3 h3=new H3("Create new volunteer");
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.setAlignItems(Alignment.CENTER);
        email.setErrorMessage("Please enter a valid email address");
        id.setWidth(350, Unit.PIXELS);
        firstName.setWidth(350, Unit.PIXELS);
        lastName.setWidth(350, Unit.PIXELS);
        name.setWidth(350, Unit.PIXELS);
        password.setWidth(350, Unit.PIXELS);
        phone.setWidth(350, Unit.PIXELS);
        email.setWidth(350, Unit.PIXELS);
        role.setWidth(350, Unit.PIXELS);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.setWidth(170, Unit.PIXELS);
        cancel.setWidth(170, Unit.PIXELS);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        formLayout.add(h3, id, firstName, lastName, name, password, phone, email, role, buttonLayout);
        return formLayout;
    }
}
