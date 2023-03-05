package com.example.application.views.walkthedog;

import com.example.application.data.client.api.RandomDogClient;
import com.example.application.data.entity.DogDto;
import com.example.application.data.entity.ImageDto;
import com.example.application.data.service.DogService;
import com.example.application.data.service.GalleryService;
import com.example.application.views.MainLayout;
import com.example.application.views.gallery.GalleryViewCard;
import com.example.application.views.newwalk.NewWalkView;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@PageTitle("Walk the dog")
@Route(value = "walks/create", layout = MainLayout.class)
//@PermitAll
@RolesAllowed({"ADMIN", "USER"})
public class WalkthedogView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;
    GalleryService galleryService;
    RandomDogClient randomDogClient;
    DogService dogService;

    public String SELECTED_DOG_NAME = "";



    public WalkthedogView(GalleryService galleryService, DogService dogService, RandomDogClient randomDogClient) {
        this.galleryService=galleryService;
        this.dogService=dogService;
        this.randomDogClient=randomDogClient;
        constructUI();




        List<DogDto> all = dogService.getAllDogs();
        all.stream().forEach(element -> {
            imageContainer.add(new WalkthedogViewCardInfo( element, randomDogClient));

        } );
    }

    private void constructUI() {
        addClassNames("walkthedog-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Choose the dog and take him for a walk");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph description = new Paragraph("Click Walk the dog button");
        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        headerContainer.add(header, description);



        imageContainer = new OrderedList();
        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        container.add(headerContainer);
        add(container, imageContainer);

    }
}
