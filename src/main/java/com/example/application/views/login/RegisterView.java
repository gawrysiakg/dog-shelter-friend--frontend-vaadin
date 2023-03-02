package com.example.application.views.login;

import com.example.application.data.Role;
import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.VolunteerService;
import com.example.application.views.MainLayout;
import com.example.application.views.newwalk.NewwalkView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import javax.annotation.security.RolesAllowed;

@PageTitle("Register")
@Route(value = "register", layout = MainLayout.class)
@AnonymousAllowed
public class RegisterView extends Div{//Composite Vertical layout


    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField name = new TextField("Username");
    private TextField password = new TextField("Password");
    private EmailField email = new EmailField("Email address");
   // private DatePicker dateOfBirth = new DatePicker("Birthday");
    private RegisterView.PhoneNumberField phone = new RegisterView.PhoneNumberField("Phone number");


    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<VolunteerDto> binder = new Binder<>(VolunteerDto.class);



    public RegisterView(VolunteerService volunteerService) {
        addClassName("newwalk-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
           // volunteerService.createVolunteer(binder.getBean());

            VolunteerDto volunteerDto = new VolunteerDto(firstName.getValue(), lastName.getValue(), name.getValue()
                    , password.getValue(), email.getValue(), binder.getBean().getPhone(), Role.USER);
            if(volunteerService.fetchVolunteers().size()==0){
                volunteerDto.setRole(Role.ADMIN);
            }
//            else {
//                volunteerDto.setRole(Role.USER);
//            }
            volunteerService.createVolunteer(volunteerDto);
//            VolunteerDto fromRepo = volunteerService.fetchByUsername(volunteerDto.getName());
//
//           volunteerService.createVolunteer(fromRepo);
            Notification.show(binder.getBean().getClass().getSimpleName() + " Registered. Now log in");
            clearForm();
            UI.getCurrent().navigate("login");
        });
    }

    private void clearForm() {
        binder.setBean(new VolunteerDto());
    }

    private com.vaadin.flow.component.Component createTitle() {
        return new H3("New volunteer form");
    }

    private com.vaadin.flow.component.Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(firstName, lastName, name, password, email, phone);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

    private static class PhoneNumberField extends CustomField<String> {
        private ComboBox<String> countryCode = new ComboBox<>();
        private TextField number = new TextField();

        public PhoneNumberField(String label) {
            setLabel(label);
            countryCode.setWidth("120px");
            countryCode.setPlaceholder("Country");
            countryCode.setAllowedCharPattern("[\\+\\d]");
            countryCode.setItems("+48", "+47", "+45", "+98", "+964", "+353", "+44", "+972", "+39", "+225");
            countryCode.addCustomValueSetListener(e -> countryCode.setValue(e.getDetail()));
            number.setAllowedCharPattern("\\d");
            HorizontalLayout layout = new HorizontalLayout(countryCode, number);
            layout.setFlexGrow(1.0, number);
            add(layout);
        }

        @Override
        protected String generateModelValue() {
            if (countryCode.getValue() != null && number.getValue() != null) {
                String s = countryCode.getValue() + " " + number.getValue();
                return s;
            }
            return "";
        }

        @Override
        protected void setPresentationValue(String phoneNumber) {
            String[] parts = phoneNumber != null ? phoneNumber.split(" ", 2) : new String[0];
            if (parts.length == 1) {
                countryCode.clear();
                number.setValue(parts[0]);
            } else if (parts.length == 2) {
                countryCode.setValue(parts[0]);
                number.setValue(parts[1]);
            } else {
                countryCode.clear();
                number.clear();
            }
        }
    }
}
