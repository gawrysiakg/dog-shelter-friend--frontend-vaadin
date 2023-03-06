package com.example.application.views.about;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("About us")
@Route(value = "about", layout = MainLayout.class)
@AnonymousAllowed
public class AboutUsView extends VerticalLayout {

    public AboutUsView() {
        setSpacing(false);

        Image img = new Image("icons/dog.png", "");
        img.setWidth("300px");
        add(img);

        H2 header = new H2("The dog shelter in Bialystok invites everyone willing to visit our dogs. If you want to become a volunteer" +
                " and help us take care of animals and take them for a walk - just sign up.");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("We are waiting for You!"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
