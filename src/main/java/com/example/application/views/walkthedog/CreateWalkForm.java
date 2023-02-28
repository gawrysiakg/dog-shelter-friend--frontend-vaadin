package com.example.application.views.walkthedog;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Create walk form")
@Route(value = "walks-layout-create", layout = MainLayout.class)
@PermitAll
public class CreateWalkForm  extends Div {




    public CreateWalkForm() {
        TextField dogName = new TextField("Dog name");
        TextField volunteerUsername = new TextField("Username");
        DatePicker date = new DatePicker("Day of planned walk:");


        FormLayout formLayout = new FormLayout();
        formLayout.add(dogName, volunteerUsername, date);
        formLayout.setColspan(dogName, 3);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 3));
        add(formLayout);
    }



}
