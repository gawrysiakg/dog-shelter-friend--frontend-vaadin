package com.example.application.views.volunteers.edit;

import com.example.application.data.Role;
import com.example.application.data.entity.DogDto;
import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.DogService;
import com.example.application.data.service.VolunteerService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;

@Route(value = "volunteers/edit", layout = MainLayout.class)
@Component
@RolesAllowed("ADMIN")
public class VolunteersEditView extends FormLayout {

    private TextField id = new TextField("id");
    private TextField firstName = new TextField("firstName");
    private TextField lastName = new TextField("lastName");
    private TextField name = new TextField("name");
    private TextField password = new TextField("password");
    private TextField email = new TextField("email");
    private IntegerField phone = new IntegerField("phone");
    private ComboBox<Role> role = new ComboBox<Role>("role");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private VolunteerService service;
    private MainVolunteersView mainVolunteersView;


    public VolunteersEditView(MainVolunteersView mainVolunteersView, VolunteerService service){
        this.mainVolunteersView = mainVolunteersView;
        this.service = service;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        role.setItems(Role.ADMIN, Role.USER);

        add(id, firstName, lastName, name, password, email, phone, role, buttons);


       // binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
       // binder.refreshFields();
    }



    private VolunteerDto volunteerDtoFromFields(){
        VolunteerDto dto =new VolunteerDto();
        dto.setId(Long.valueOf(id.getValue()));
        dto.setFirstName(firstName.getValue());
        dto.setLastName(lastName.getValue());
        dto.setName(name.getValue());
        dto.setPassword(password.getValue());
        dto.setEmail(email.getValue());
        dto.setPhone(phone.getValue());
        dto.setRole(role.getValue());
        return dto;
    }
    private void save() {
//        VolunteerDto dto =binder.getBean();
//        service.createVolunteer(dto);
//        mainVolunteersView.refresh();
//        setVolunteer(null);

        service.createVolunteer(volunteerDtoFromFields());
        mainVolunteersView.refresh();
        setVolunteer(null);

    }

    private void delete() {
       // VolunteerDto dto = binder.getBean();
        service.deleteUser(volunteerDtoFromFields().getId());
        mainVolunteersView.refresh();
        setVolunteer(null);
    }

    public void setVolunteer(VolunteerDto dto) {
       // binder.setBean(dto);
        if (dto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            id.focus();
        }
    }







}
