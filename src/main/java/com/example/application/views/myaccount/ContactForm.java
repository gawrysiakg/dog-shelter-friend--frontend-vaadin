//package com.example.application.views.myaccount;
//
//import com.example.application.data.entity.VolunteerDto;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.data.binder.BeanValidationBinder;
//import com.vaadin.flow.data.binder.Binder;
//
//public class ContactForm extends FormLayout {
//    private VolunteerDto volunteerDto ;
//
//    // other methods and fields omitted
//
//    public void setVolunteerDto(VolunteerDto volunteerDto) {
//        this.volunteerDto = volunteerDto;
//        binder.readBean(volunteerDto);
//    }
//
//
//    Binder<VolunteerDto> binder = new BeanValidationBinder<>(VolunteerDto.class);
//
//    public ContactForm(List<Company> companies, List<Status> statuses) {
//        addClassName("contact-form");
//        binder.bindInstanceFields(this);
//        // Rest of constructor omitted
//    }
//
//}
