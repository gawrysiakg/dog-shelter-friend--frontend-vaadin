package com.example.application.views.walkthedog;

import com.example.application.data.client.api.RandomDogClient;
import com.example.application.data.entity.DogDto;
//import com.example.application.views.volunteers.VolunteerView;
import com.example.application.views.newwalk.NewWalkView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.theme.lumo.LumoUtility.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;

@Component
@RequiredArgsConstructor
@RolesAllowed({"ADMIN", "USER"})
public class WalkthedogViewCardInfo extends ListItem {

    DogDto dogDto;
    RandomDogClient randomDogClient;


    public WalkthedogViewCardInfo(DogDto dogDto, RandomDogClient randomDogClient) {
        this.dogDto=dogDto;
        this.randomDogClient=randomDogClient;
        addClassNames(Background.CONTRAST_5, Display.FLEX, FlexDirection.COLUMN, AlignItems.START, Padding.MEDIUM,
                BorderRadius.LARGE);

        Div div = new Div();
        div.addClassNames(Background.CONTRAST, Display.FLEX, AlignItems.CENTER, JustifyContent.CENTER,
                Margin.Bottom.MEDIUM, Overflow.HIDDEN, BorderRadius.MEDIUM, Width.FULL);
        div.setHeight("160px");

        Image image = new Image("images/dog.png", "images/dogs.png");
        image.setWidth("100%");
//        RandomDog randomDog=randomDogClient.getRandomDogByBreed(dogDto.getBreed());
       // image.setSrc("dog");
        //   image.setAlt(text);

        div.add(image);

        Span header = new Span();
        header.addClassNames(FontSize.MEDIUM, FontWeight.SEMIBOLD);
        header.setText("Take me...");

        Span subtitle = new Span();
        subtitle.addClassNames(FontSize.SMALL, TextColor.SECONDARY);
        subtitle.setText("");

        Paragraph description = new Paragraph(
                "Dog ID:   "+ dogDto.getId());


        Paragraph description2 = new Paragraph(
                     "Name:  "+dogDto.getName());


        Paragraph description3 = new Paragraph(
                        "Breed:  "+dogDto.getBreed());

        Paragraph description4 = new Paragraph(
                "Is available:  "+dogDto.isInShelter());


        description.addClassName(Margin.Vertical.MEDIUM);
        description2.addClassName(Margin.Vertical.MEDIUM);
        description3.addClassName(Margin.Vertical.MEDIUM);
        description4.addClassName(Margin.Vertical.MEDIUM);

        Span badge = new Span();
        badge.getElement().setAttribute("theme", "badge");
        badge.setText("Walk the dog");
        badge.addClickListener(spanClickEvent -> {
                WalkthedogView.SELECTED_DOG_NAME=dogDto.getName();

                UI.getCurrent().navigate(NewWalkView.class);//CreateWalkForm
           // UI.getCurrent().navigate("register-walk");
            UI.getCurrent().getNavigationListeners( NewWalkView.class);
          //  UI.getCurrent().getNavigator().navigateTo(MAINVIEW);
        });

        add(div, header, subtitle, description, description2, description3, description4, badge);

    }
}
