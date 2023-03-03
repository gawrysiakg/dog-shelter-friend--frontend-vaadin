package com.example.application.views.dogs;

import com.example.application.data.entity.DogDto;
import com.example.application.data.service.DogService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;

@Component
@Route(value = "dogs", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class MainDogsView extends VerticalLayout {

    private final DogService dogService;
    private Grid<DogDto> grid = new Grid<>(DogDto.class);

    private TextField filter = new TextField();

    DogsView dogsViewForm;

    private Button addNewDog = new Button("Add new dog");

    public MainDogsView(DogService dogService){
        this.dogService = dogService;
        dogsViewForm = new DogsView(this, dogService);

       // this.dogService=dogService;
        filter.setPlaceholder("Filter by title...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("id", "name", "breed", "inShelter");

        addNewDog.addClickListener(e -> {
            grid.asSingleSelect().clear(); //"czyścimy" zaznaczenie
            dogsViewForm.setDog(new DogDto());    //dodajemy nowy obiekt do formularza
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewDog);
        HorizontalLayout mainContent = new HorizontalLayout(grid, dogsViewForm);
        mainContent.setSizeFull();
        grid.setSizeFull();
        dogsViewForm.setDog(null);
        add(toolbar, mainContent);
        setSizeFull();

        refresh();
        grid.asSingleSelect().addValueChangeListener(event -> dogsViewForm.setDog(grid.asSingleSelect().getValue()));
    }


    public void refresh() {
        grid.setItems(dogService.getAllDogs());
    }

    private void update() {
        grid.setItems(dogService.getByName(filter.getValue()));
    }

}
