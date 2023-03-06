package com.example.application.views.home;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Dog Shelter Friend")
@Route(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class MydogshelterView extends VerticalLayout {//VerticalLayout

    public MydogshelterView() {
        addClassName("home");
        Image img1 = new Image("images/dsf3.png", "placeholder plant");
        img1.setClassName("img1");

        add(img1);
        //todo
        Div div = new Div ();
        div.setClassName("home");
        div.setSizeFull();
        div.setClassName("temperatureDiv");
        H3 conditions = new H3("Current Conditions");
        H3 temperature = new H3("Temperature");
        H3 wind = new H3("Wind Speed");
        div.add(conditions, temperature, wind);

        add(div);
        setSpacing(false);

        Image img = new Image("images/dogs-background.png", "placeholder plant");
        img.setWidth("800px");
        add(img);

        H2 header = new H2("Take us for a walk!");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("We will all have fun:)"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
