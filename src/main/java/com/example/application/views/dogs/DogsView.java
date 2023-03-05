package com.example.application.views.dogs;

import com.example.application.data.entity.DogDto;
import com.example.application.data.service.DogService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.stereotype.Component;

//@Component
public class DogsView extends FormLayout {

    private TextField id = new TextField("id");
    private TextField name = new TextField("name");
    private TextField breed = new TextField("breed");
    private TextField inShelter = new TextField("inShelter");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button update = new Button("Update");
    private Binder<DogDto> binder = new Binder<DogDto>(DogDto.class);
    private DogService service;
    private MainDogsView mainDogsView;


        public DogsView(MainDogsView mainDogsView, DogService service){
            this.mainDogsView = mainDogsView;
            this.service = service;

        HorizontalLayout buttons = new HorizontalLayout(save, delete, update);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(id, name, breed, inShelter, buttons);
        binder.bindInstanceFields(this);

        save.addClickListener(event -> {
            if(!(id.getValue().isEmpty())&&(!(name.getValue().isEmpty())&&!(breed.getValue().isEmpty())&&!(inShelter.getValue().isEmpty()))){
                save();
            } else { Notification.show("Please fill in all fields", 1200, Notification.Position.MIDDLE); }
            UI.getCurrent().navigate(MainDogsView.class);
        });

            delete.addClickListener(event -> {
            if(!(id.getValue().isEmpty())&&(!(name.getValue().isEmpty())&&!(breed.getValue().isEmpty())&&!(inShelter.getValue().isEmpty()))){
                delete();
            } else { Notification.show("Please fill in all fields", 1200, Notification.Position.MIDDLE); }
        });

        update.addClickListener(event-> {
            if(!(id.getValue().isEmpty())&&(!(name.getValue().isEmpty())&&!(breed.getValue().isEmpty())&&!(inShelter.getValue().isEmpty()))){
                update();
            } else { Notification.show("Please fill in all fields", 1200, Notification.Position.MIDDLE);}
        });
        binder.refreshFields();
    }




    private void save() {
        DogDto dog = binder.getBean();
        service.addDog(dog);
        mainDogsView.refresh();
        setDog(null);
    }

    private void delete() {
        DogDto dog = binder.getBean();
        service.deleteDog(dog.getId());
        mainDogsView.refresh();
        setDog(null);
    }

    private void update() {
        DogDto dog = binder.getBean();
        service.update(dog);
        mainDogsView.refresh();
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
