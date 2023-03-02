package com.example.application.views.volunteers.edit;

import com.example.application.data.Role;
import com.example.application.data.entity.DogDto;
import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.DogService;
import com.example.application.data.service.VolunteerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.stereotype.Component;

@Component
public class VolunteersEditView extends FormLayout {

    private TextField id = new TextField("id");
    private TextField firstName = new TextField("firstName");
    private TextField lastName = new TextField("lastName");
    private TextField name = new TextField("name");
    private TextField password = new TextField("password");
    private TextField email = new TextField("email");
    private ComboBox<Role> role = new ComboBox<Role>("role");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Binder<VolunteerDto> binder = new Binder<VolunteerDto>(VolunteerDto.class);
    //Jak działa binder? W ogólnym ujęciu mapuje on zmienne z formularza ze zmiennymi w obiekcie typu Book.
    // Jeśli nazwy zmiennych nie są takie same w dwóch obiektach, możemy użyć adnotacji @PropertyId nad polem ]
    // w formularzu – należy wskazać nazwę zmiennej z klasy, do której chcemy zmapować dane pole.

    private VolunteerService service;
    private MainVolunteersView mainVolunteersView;


    public VolunteersEditView(MainVolunteersView mainVolunteersView, VolunteerService service){
        this.mainVolunteersView = mainVolunteersView;
        this.service = service;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(id,firstName, lastName, name, password, email, role, buttons);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        binder.refreshFields();
    }




    private void save() {
        VolunteerDto dto =binder.getBean();
        service.createVolunteer(dto);
        mainVolunteersView.refresh();
        setVolunteer(null);
    }

    private void delete() {
        VolunteerDto dto = binder.getBean();
        service.deleteUser(dto.getId());
        mainVolunteersView.refresh();
        setVolunteer(null);
    }

    public void setVolunteer(VolunteerDto dto) {
        binder.setBean(dto);
        //service.updateUser(dto);
        if (dto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            id.focus();
        }
    }
}
