//package com.example.application.views.volunteers;
//
//import com.example.application.data.Role;
//import com.example.application.data.entity.VolunteerDto;
//import com.example.application.data.service.VolunteerService;
//import com.example.application.views.MainLayout;
//import com.vaadin.flow.component.UI;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.button.ButtonVariant;
//import com.vaadin.flow.component.combobox.ComboBox;
//import com.vaadin.flow.component.datepicker.DatePicker;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.grid.GridVariant;
//import com.vaadin.flow.component.html.Div;
//import com.vaadin.flow.component.html.Image;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.notification.Notification.Position;
//import com.vaadin.flow.component.notification.NotificationVariant;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.splitlayout.SplitLayout;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.component.upload.Upload;
//import com.vaadin.flow.data.binder.BeanValidationBinder;
//import com.vaadin.flow.data.binder.ValidationException;
//import com.vaadin.flow.router.BeforeEnterEvent;
//import com.vaadin.flow.router.BeforeEnterObserver;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//import java.util.Optional;
//import javax.annotation.security.RolesAllowed;
//
//import org.springframework.orm.ObjectOptimisticLockingFailureException;
//
//@PageTitle("All volunteers")
////@Route(value = "volunteers/add/:sampleBookID?/:action?(edit)", layout = MainLayout.class)
//@Route(value = "volunteers", layout = MainLayout.class)
//@RolesAllowed("ADMIN")
//public class VolunteerView extends Div implements BeforeEnterObserver {
//
//    private final String VOLUNTEER_ID = "VOLUNTEER ID";
//  //  private final String SAMPLEBOOK_EDIT_ROUTE_TEMPLATE = "volunteers/add/%s/edit";
//  private final String VOLUNTEERS_EDIT_ROUTE_TEMPLATE = "volunteers/add/edit";
//
//    private final Grid<VolunteerDto> grid = new Grid<>(VolunteerDto.class, false);
//
//    private TextField id;
//    private TextField fistName;
//    private TextField lastName;
//    private TextField name;
//    private TextField password;
//    private TextField email;
//    private TextField phone;
//    private ComboBox<Role> role;
//
//    private final Button cancel = new Button("Cancel");
//    private final Button save = new Button("Save");
//
//    private final BeanValidationBinder<VolunteerDto> binder;
//
//    private VolunteerDto volunteerDto;
//
//    private final VolunteerService volunteerService;
//
//    public VolunteerView(VolunteerService volunteerService) {
//        this.volunteerService = volunteerService;
//        addClassNames("addnewvolunteer-view"); //addnewdog-view
//
//        // Create UI
//        SplitLayout splitLayout = new SplitLayout();
//
//        createGridLayout(splitLayout);
//        createEditorLayout(splitLayout);
//
//        add(splitLayout);
//
//
//        grid.addColumn("id").setAutoWidth(true);
//        grid.addColumn("firstName").setAutoWidth(true);
//        grid.addColumn("lastName").setAutoWidth(true);
//        grid.addColumn("name").setAutoWidth(true);
//        grid.addColumn("password").setAutoWidth(true);
//        grid.addColumn("email").setAutoWidth(true);
//        grid.addColumn("phone").setAutoWidth(true);
//        grid.addColumn("role").setAutoWidth(true);
//
//        grid.setItems(volunteerService.fetchVolunteers());
//        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
//
//        // when a row is selected or deselected, populate form
//        grid.asSingleSelect().addValueChangeListener(event -> {
//            if (event.getValue() != null) {
//                UI.getCurrent().navigate(String.format(VOLUNTEERS_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
//            } else {
//                clearForm();
//               // UI.getCurrent().navigate(VolunteerView.class);
//            }
//        });
//
//        // Configure Form
//        binder = new BeanValidationBinder<>(VolunteerDto.class);
//        binder.bindInstanceFields(this);
//
//
//        cancel.addClickListener(e -> {
//            clearForm();
//            refreshGrid();
//        });
//
//        save.addClickListener(e -> {
//            try {
//                if (this.volunteerDto == null) {
//                    this.volunteerDto = new VolunteerDto();
//                }
//                binder.writeBean(this.volunteerDto);
//                volunteerService.updateUser(this.volunteerDto);
//                clearForm();
//                refreshGrid();
//                Notification.show("Data updated");
//                UI.getCurrent().navigate(VolunteerView.class);
//            } catch (ObjectOptimisticLockingFailureException exception) {
//                Notification n = Notification.show(
//                        "Error updating the data. Somebody else has updated the record while you were making changes.");
//                n.setPosition(Position.MIDDLE);
//                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
//            } catch (ValidationException validationException) {
//                Notification.show("Failed to update the data. Check again that all values are valid");
//            }
//        });
//    }
//
//    @Override
//    public void beforeEnter(BeforeEnterEvent event) {//VOLUNTEER_ID
//        Optional<Long> sampleVolunteerId = event.getRouteParameters().get(id.getValue()).map(Long::parseLong);
//        if (sampleVolunteerId.isPresent()) {
//            VolunteerDto volunteerFromBackend = volunteerService.fetchById(sampleVolunteerId.get());
//
//        if (sampleVolunteerId.isPresent()) {
//                populateForm(volunteerFromBackend);
//        } else {
//             Notification.show(String.format("The requested was not found, ID = %s", sampleVolunteerId.get()),
//                        3000, Notification.Position.BOTTOM_START);
//                // when a row is selected but the data is no longer available,
//             refreshGrid();
//             event.forwardTo(VolunteerView.class);
//            }
//        }
//    }
//
//    private void createEditorLayout(SplitLayout splitLayout) {
//        Div editorLayoutDiv = new Div();
//        editorLayoutDiv.setClassName("editor-layout");
//
//        Div editorDiv = new Div();
//        editorDiv.setClassName("editor");
//        editorLayoutDiv.add(editorDiv);
//
//        FormLayout formLayout = new FormLayout();
//
//
//        id = new TextField("id");
//        fistName = new TextField("firstName");
//        lastName = new TextField("lastName");
//        name = new TextField("name");
//        password = new TextField("password");
//        email = new TextField("email");
//        phone = new TextField("phone");
//        role = new ComboBox<Role>("role");
//
//
//        formLayout.add(id,fistName,  name, lastName, name, password, email, phone, role);
//
//        editorDiv.add(formLayout);
//        createButtonLayout(editorLayoutDiv);
//
//        splitLayout.addToSecondary(editorLayoutDiv);
//    }
//
//    private void createButtonLayout(Div editorLayoutDiv) {
//        HorizontalLayout buttonLayout = new HorizontalLayout();
//        buttonLayout.setClassName("button-layout");
//        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
//        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        buttonLayout.add(save, cancel);
//        editorLayoutDiv.add(buttonLayout);
//    }
//
//    private void createGridLayout(SplitLayout splitLayout) {
//        Div wrapper = new Div();
//        wrapper.setClassName("grid-wrapper");
//        splitLayout.addToPrimary(wrapper);
//        wrapper.add(grid);
//    }
//
//    private void refreshGrid() {
//        grid.select(null);
//        grid.getDataProvider().refreshAll();
//    }
//
//    private void clearForm() {
//        populateForm(null);
//    }
//
//    private void populateForm(VolunteerDto value) {
//        this.volunteerDto = value;
//        binder.readBean(this.volunteerDto);
//        }
//
//    }
//
//
