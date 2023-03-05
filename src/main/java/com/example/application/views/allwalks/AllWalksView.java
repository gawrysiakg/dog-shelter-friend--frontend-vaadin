package com.example.application.views.allwalks;

import com.example.application.data.entity.WalkDto;
import com.example.application.data.service.WalkService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;

//@Component
public class AllWalksView extends VerticalLayout {

    private TextField id = new TextField("id");

    private DatePicker walkDate = new DatePicker("walkDate");
    private TextField volunteerName = new TextField("volunteerName");
    private TextField dogName = new TextField("dogName");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button update = new Button("Update");
    private Binder<WalkDto> binder = new Binder<WalkDto>(WalkDto.class);
    private MainAllWalks mainAllWalks;
    private final WalkService walkService;

    public AllWalksView(MainAllWalks mainAllWalks, WalkService walkService) {
        this.mainAllWalks=mainAllWalks;
        this.walkService=walkService;
        HorizontalLayout buttons = new HorizontalLayout(save, delete, update);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(id, walkDate, volunteerName, dogName, buttons);
        binder.bindInstanceFields(this);

        save.addClickListener(event -> {
            if(validateEmpty()){
                save();
            } else { Notification.show("Please fill in all fields", 1500, Notification.Position.MIDDLE); }
        });

        delete.addClickListener(event -> {
            if(validateEmpty()){
                delete();
            } else { Notification.show("Please fill in all fields", 1500, Notification.Position.MIDDLE); }
        });

        update.addClickListener(event-> {
            if(validateEmpty()){
                update();
            } else { Notification.show("Please fill in all fields", 1200, Notification.Position.MIDDLE);}
        });

        binder.refreshFields();
    }

    private boolean validateEmpty(){
        return !(id.getValue().isEmpty())
                && (!(walkDate.isEmpty())
                && !(volunteerName.getValue().isEmpty())
                && !(dogName.getValue().isEmpty()));
    }

    private void save() {
        WalkDto walkDto = binder.getBean();
        walkService.saveWalk(walkDto);
        mainAllWalks.refresh();
        setWalk(null);
    }

    private void delete() {
        WalkDto walkDto = binder.getBean();
        walkService.deleteWalk(walkDto.getId());
        mainAllWalks.refresh();
        setWalk(null);
    }

    private void update() {
        WalkDto walkDto = binder.getBean();
        walkService.updateWalk(walkDto);
        mainAllWalks.refresh();
        setWalk(null);
    }

    public void setWalk(WalkDto walk) {
        binder.setBean(walk);

        if (walk == null) {
            setVisible(false);
        } else {
            setVisible(true);
            id.focus();
        }
    }
}
