package com.example.application.views.gallery;

import com.example.application.data.entity.ImageDto;
import com.example.application.data.service.GalleryService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
import javax.annotation.security.RolesAllowed;
import java.util.List;

@PageTitle("Gallery")
@Route(value = "gallery", layout = MainLayout.class)
@RolesAllowed({"USER", "ADMIN"})
public class GalleryView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;
    private GalleryService galleryService;


    public GalleryView(GalleryService galleryService) {
        this.galleryService=galleryService;
        constructUI();
        List<ImageDto> all = galleryService.fetchImages();

        all.stream().forEach(element -> {
            imageContainer.add(new GalleryViewCard("", element.getUrl()));
            addClickListener( e-> {
            });
        } );

    }

    private void constructUI() {
        addClassNames("gallery-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Photos");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph description = new Paragraph("Dog can be the best friend, come and see for yourself");
        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        headerContainer.add(header, description);

        imageContainer = new OrderedList();
        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        container.add(headerContainer);
        add(container, imageContainer);

    }
}
