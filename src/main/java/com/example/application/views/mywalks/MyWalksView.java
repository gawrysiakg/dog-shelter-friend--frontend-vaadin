package com.example.application.views.mywalks;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import javax.annotation.security.PermitAll;

@PageTitle("My walks")
@Route(value = "walks/user", layout = MainLayout.class)
@PermitAll
public class MyWalksView extends VerticalLayout {

    public MyWalksView() {





    }

}
