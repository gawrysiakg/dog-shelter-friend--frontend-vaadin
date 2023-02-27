package com.example.application.views.walkthedog;

import com.example.application.data.entity.ImageDto;
import com.example.application.data.service.GalleryService;
import com.example.application.views.MainLayout;
import com.example.application.views.gallery.GalleryViewCard;
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
import javax.annotation.security.PermitAll;
import java.util.List;

@PageTitle("Walk the dog")
@Route(value = "walks/create", layout = MainLayout.class)
@PermitAll
public class WalkthedogView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;

    private GalleryService galleryService;



    public WalkthedogView(GalleryService galleryService) {
        this.galleryService=galleryService;
        constructUI();



        List<ImageDto> all = galleryService.fetchImages();
        all.stream().forEach(element -> {
            imageContainer.add(new GalleryViewCard("", element.getUrl()));
//            Image image=new Image(element.getUrl(), "no url");
//            // com.vaadin.flow.component.html.Image image =
//            //new com.vaadin.flow.component.html.Image(element.getUrl(), "no url");
//            image.setMaxHeight(40, Unit.PERCENTAGE);
//            image.setMaxWidth(40, Unit.PERCENTAGE);
            //setHorizontalComponentAlignment(Alignment.CENTER);
            //setAlignItems(FlexComponent.Alignment.CENTER);
            // add(image);
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
        Paragraph description = new Paragraph("Just click CREATE WALK button");
        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        headerContainer.add(header, description);

        Select<String> sortBy = new Select<>();
        sortBy.setLabel("Sort by");
        sortBy.setItems("Popularity", "Newest first", "Oldest first");
        sortBy.setValue("Popularity");

        imageContainer = new OrderedList();
        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        container.add(headerContainer, sortBy);
        add(container, imageContainer);

    }
}
