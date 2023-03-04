package com.example.application.views.home;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("My dog shelter")
@Route(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class MydogshelterView extends VerticalLayout {

    public MydogshelterView() {
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
