package com.example.application.views;

import com.example.application.data.entity.ImageDto;
import com.example.application.data.service.GalleryService;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.security.RolesAllowed;
@PageTitle("Add photo")
@Route(value = "upload", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class UploadView extends VerticalLayout {

    private GalleryService galleryService;

    @Autowired
    public UploadView(GalleryService galleryService) {
        this.galleryService = galleryService;

        setAlignItems(Alignment.AUTO);
        Label label = new Label();
        label.addClassName("button-layout");
        TextField textField = new TextField();
        Button button = new Button("Upload");
        button.addClassName("button-layout");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        textField.setTitle("Enter image url, for example C:\\files\\dog1.jpg");
        textField.setHeight(80, Unit.PIXELS);
        textField.setWidth(400, Unit.PIXELS);
        button.setHeight(80, Unit.PIXELS);
        button.setWidth(400, Unit.PIXELS);
        button.addClickListener(clickEvent ->
        {
            ImageDto uploadedImage = galleryService.uploadImage(textField.getValue());
            Image image = new Image(uploadedImage.getUrl(), "no photo");
            label.setText("Uploaded!");
            add(label);
            add(image);
        });

        add(textField);
        add(button);
        setAlignItems(Alignment.CENTER);
    }
}
