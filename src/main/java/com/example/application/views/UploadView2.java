package com.example.application.views;

import com.example.application.data.entity.ImageDto;
import com.example.application.data.service.GalleryService;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.open.FileUtil;

import javax.annotation.security.RolesAllowed;
import javax.swing.plaf.FileChooserUI;

@PageTitle("Add photo")
@Route(value = "upload2", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class UploadView2 extends VerticalLayout {

    private GalleryService galleryService;
    Upload upload = new Upload();

    public UploadView2(GalleryService galleryService) {
        this.galleryService = galleryService;
        add(upload);

        upload.setHeight(200, Unit.PIXELS);


        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();


            ImageDto uploadedImage = galleryService.uploadImage(fileName);
            Image image = new Image(uploadedImage.getUrl(), "no photo");
           // label.setText("Uploaded!");
            Notification.show("Uploaded!", 1200, Notification.Position.MIDDLE);
           // add(label);
            add(image);

        });


    }




}
