package com.example.application.views.myaccount;

import com.example.application.data.Role;
import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.VolunteerService;
import com.example.application.security.UserDetailsServiceImpl;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinContext;
import com.vaadin.flow.server.VaadinSession;

import javax.annotation.security.PermitAll;

@PageTitle("My account")
@Route(value = "volunteers/details", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class MyaccountView extends FormLayout {

  //  private TextField id = new TextField("id");

    private TextField firstName = new TextField("firstName");
    private TextField lastName = new TextField("lastName");
    private TextField name = new TextField("name");
    private TextField password = new TextField("password");
    private TextField email = new TextField("email");
    private IntegerField phone = new IntegerField("phone");
    private ComboBox<Role> role = new ComboBox<Role>("role");

    private VolunteerDto volunteerDto;
    private VolunteerService volunteerService;


//    public void setVolunteerDto(VolunteerDto volunteerDto) {
//        this.volunteerDto = volunteerDto;
//        binder.readBean(volunteerDto);
//    }
    private Binder<VolunteerDto> binder;//= new Binder<>(VolunteerDto.class);

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");



    public MyaccountView(VolunteerService volunteerService, VolunteerDto volunteerDto) {
        this.volunteerDto=volunteerDto;
        binder= new Binder<>(VolunteerDto.class);
       // binder.bindInstanceFields(volunteerService.fetchByUsername(VaadinSession.getCurrent().getAttribute()));

//        binder.forField(id)
//                .withConverter(
//                        new StringToLongConverter("Must enter a number"))
//                .bind(VolunteerDto::getId, VolunteerDto::setId);
//        binder.forField(phone)
//                .withConverter(
//                        new StringToLongConverter("Must enter a number"))
//                .bind(VolunteerDto::getPhone, VolunteerDto::setPhone);
//


        binder.bindInstanceFields(this);

//
//        binder.forField(id)
//                .bind(VolunteerDto::getId, Person::setFirstName);
//        binder.forField(lastName)
//                .bind(Person::getLastName, Person::setLastName);
//        binder.forField(gender)
//                .bind(Person::getGender, Person::setGender);
//
//


        addClassName("myaccount-view");
        binder.setBean(volunteerDto);
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
      //  binder.setBean(binder.getBean());
       // binder.bindInstanceFields(this);
       // clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            volunteerService.updateUser(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
        });

        binder.refreshFields();











//
//        //
//        //
//        this.volunteerService=volunteerService;
//        this.volunteerDto=volunteerDto;
//        addClassName("myaccount-view");
//        binder = new Binder<>(VolunteerDto.class);
//        setVolunteerDto(volunteerDto);
//        add(createTitle());
//        add(createFormLayout());
//        add(createButtonLayout());
//
//        //String username = (String) VaadinSession.getCurrent().getAttribute("name");
//        //volunteerService.fetchByUsername(username);
//      //  binder = new BeanValidationBinder<>(VolunteerDto.class); //added
//       binder.bindInstanceFields(this);
//
//      //  VolunteerDto fromForm = binder.getBean();
//
//       // clearForm();
//        binder.refreshFields();
//      //  VolunteerDto fromRepo = volunteerService.fetchByUsername(username);
//
//
//
//
//
//        cancel.addClickListener(e -> clearForm());
//        save.addClickListener(e -> {
//          //  VolunteerDto toUpdate = binder.getBean();
//         //   toUpdate.setRole(UI.getCurrent().getSession().getAttribute(Role.class));
//         //   volunteerService.updateUser(toUpdate);
//
////
////            if(!firstName.getValue().isEmpty()){
////                fromRepo.setFirstName(firstName.getValue());
////            }
////            if(!lastName.getValue().isEmpty()){
////                fromRepo.setLastName(lastName.getValue());
////            }
////            if(!email.getValue().isEmpty()) {
////                fromRepo.setEmail(email.getValue());
////            }
////            if(!name.getValue().isEmpty()) {
////                fromRepo.setName(name.getValue());
////            }
////            if(!password.getValue().isEmpty()) {
////                fromRepo.setPassword(password.getValue());
////            }
////            if(phone.getValue()>100000000) {
////                fromRepo.setPhone(phone.getValue());
////            }
////
////            volunteerService.updateUser(fromRepo);
//
//
//
//
//
//
//           // Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
//            clearForm();
//        });
    }


    private void updateCurrent(){


    }

    private void clearForm() {

        binder.setBean(new VolunteerDto());
    }

    private Component createTitle() {
        return new H3("Personal information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
       // binder.setBean(binder.getBean());
        formLayout.add( firstName, lastName, name, password, phone, email, role);
        //binder.setBean(binder.getBean());
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
            countryCode.setItems("+354", "+91", "+62", "+98", "+964", "+353", "+44", "+972", "+39", "+225");
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
