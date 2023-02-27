//package com.example.application.views.dogs;
//
//import com.example.application.views.MainLayout;
//import com.vaadin.flow.component.dependency.Uses;
//import com.vaadin.flow.component.icon.Icon;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//
//import javax.annotation.security.RolesAllowed;
//
//@PageTitle("Dogs")
//@Route(value = "dogs", layout = MainLayout.class)
//@RolesAllowed("ADMIN")
//@Uses(Icon.class)
//public class DogsListAndForm extends VerticalLayout {
//
//
//   // private DogsList dogsList;
//
//
//    public DogsListAndForm(){
//    DogsList dogsViewForm = new DogsList(this);
//    MainView mainView = new MainView(this);
//
//    HorizontalLayout horizontal=new HorizontalLayout();
//    horizontal.add(dogsViewForm);
//    horizontal.setVisible(true);
//
//
//
//
//    add(horizontal);
//    }
//
//
//
//
//}
