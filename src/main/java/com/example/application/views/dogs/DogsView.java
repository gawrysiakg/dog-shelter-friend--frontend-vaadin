package com.example.application.views.dogs;

import com.example.application.data.entity.DogDto;
import com.example.application.data.service.DogService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;

@Component
public class DogsView extends FormLayout {

    private TextField id = new TextField("id");
    private TextField name = new TextField("name");
    private TextField breed = new TextField("breed");
    private TextField inShelter = new TextField("inShelter");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Binder<DogDto> binder = new Binder<DogDto>(DogDto.class);
    //Jak działa binder? W ogólnym ujęciu mapuje on zmienne z formularza ze zmiennymi w obiekcie typu Book.
    // Jeśli nazwy zmiennych nie są takie same w dwóch obiektach, możemy użyć adnotacji @PropertyId nad polem ]
    // w formularzu – należy wskazać nazwę zmiennej z klasy, do której chcemy zmapować dane pole.

    private DogService service;
    private MainView mainView;


        public DogsView(MainView mainView, DogService service){
            this.mainView = mainView;
            this.service = service;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(id, name, breed, inShelter, buttons); //type,
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        binder.refreshFields();
    }




    private void save() {
        DogDto dog = binder.getBean();
        service.addDog(dog);
        mainView.refresh();
        setDog(null);
    }

    private void delete() {
        DogDto dog = binder.getBean();
        service.deleteDog(dog.getId());
        //grid.setItems(dogService.getAllDogs());
        mainView.refresh();
        setDog(null);
    }

    public void setDog(DogDto dog) {
        binder.setBean(dog);

        if (dog == null) {
            setVisible(false);
        } else {
            setVisible(true);
            id.focus();
        }
    }
}
