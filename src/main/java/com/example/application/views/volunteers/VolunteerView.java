package com.example.application.views.volunteers;

import com.example.application.data.entity.SampleBook;
import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.VolunteerService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Optional;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("All volunteers")
//@Route(value = "volunteers/add/:sampleBookID?/:action?(edit)", layout = MainLayout.class)
@Route(value = "volunteers", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class VolunteerView extends Div implements BeforeEnterObserver {

    private final String VOLUNTEER_ID = "VOLUNTEER ID";
    private final String SAMPLEBOOK_EDIT_ROUTE_TEMPLATE = "volunteers/add/%s/edit";

    private final Grid<VolunteerDto> grid = new Grid<>(VolunteerDto.class, false);

    private Upload image;
    private Image imagePreview;
    private TextField id;
    private TextField name;
    private TextField password;
    private DatePicker date;
    private TextField email;
    private TextField role;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private final BeanValidationBinder<VolunteerDto> binder;

    private VolunteerDto volunteerDto;

    private final VolunteerService volunteerService;

    public VolunteerView(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
        addClassNames("addnewvolunteer-view"); //addnewdog-view

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
//        LitRenderer<VolunteerDto> imageRenderer = LitRenderer
//                .<SampleBook>of("<img style='height: 64px' src=${item.image} />").withProperty("image", item -> {
//                    if (item != null && item.getImage() != null) {
//                        return "data:image;base64," + Base64.getEncoder().encodeToString(item.getImage());
//                    } else {
//                        return "";
//                    }
//                });
      //  grid.addColumn(imageRenderer).setHeader("Image").setWidth("68px").setFlexGrow(0);

        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("name").setAutoWidth(true);
        grid.addColumn("password").setAutoWidth(true);
        grid.addColumn("email").setAutoWidth(true);
        grid.addColumn("role").setAutoWidth(true);
//        grid.setItems(query -> sampleBookService.list(
//                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
//                .stream());

        grid.setItems(volunteerService.fetchVolunteers());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                //UI.getCurrent().navigate(String.format(SAMPLEBOOK_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
               // UI.getCurrent().navigate(VolunteerView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(VolunteerDto.class);

        // Bind fields. This is where you'd define e.g. validation rules
       // binder.forField(pages).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("pages");

        binder.bindInstanceFields(this);

       // attachImageUpload(image, imagePreview);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.volunteerDto == null) {
                    this.volunteerDto = new VolunteerDto();
                }
                binder.writeBean(this.volunteerDto);
                volunteerService.updateUser(this.volunteerDto);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(VolunteerView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } catch (ValidationException validationException) {
                Notification.show("Failed to update the data. Check again that all values are valid");
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> sampleVolunteerId = event.getRouteParameters().get(VOLUNTEER_ID).map(Long::parseLong);
        if (sampleVolunteerId.isPresent()) {
            VolunteerDto volunteerFromBackend = volunteerService.fetchById(sampleVolunteerId.get());
            // Optional<VolunteerDto> volunteerFromBackend = volunteerService.fetchById(sampleVolunteerId.get());
            if (sampleVolunteerId.isPresent()) {
                populateForm(volunteerFromBackend);
            } else {
                Notification.show(String.format("The requested sampleBook was not found, ID = %s", sampleVolunteerId.get()),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(VolunteerView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();


        id = new TextField("id");
        name = new TextField("name");
        date = new DatePicker("date");
        password = new TextField("password");
        email = new TextField("email");
        role = new TextField("role");


        formLayout.add(id, name, password, email, role);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

//    private void attachImageUpload(Upload upload, Image preview) {
//        ByteArrayOutputStream uploadBuffer = new ByteArrayOutputStream();
//        upload.setAcceptedFileTypes("image/*");
//        upload.setReceiver((fileName, mimeType) -> {
//            uploadBuffer.reset();
//            return uploadBuffer;
//        });
//        upload.addSucceededListener(e -> {
//            StreamResource resource = new StreamResource(e.getFileName(),
//                    () -> new ByteArrayInputStream(uploadBuffer.toByteArray()));
//            preview.setSrc(resource);
//            preview.setVisible(true);
//            if (this.volunteerDto == null) {
//                this.volunteerDto = new VolunteerDto();
//            }
//            this.volunteerDto.setImage(uploadBuffer.toByteArray());
//        });
//        preview.setVisible(false);
//    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(VolunteerDto value) {
        this.volunteerDto = value;
        binder.readBean(this.volunteerDto);
        this.imagePreview.setVisible(value != null);
        if (value == null) {
            this.image.clearFileList();
            this.imagePreview.setSrc("");
        } else {
           // this.imagePreview.setSrc("data:image;base64," + Base64.getEncoder().encodeToString(value.getImage()));
        }

    }

}
