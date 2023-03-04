package com.example.application.views.allwalks;

import com.example.application.data.entity.DogDto;
import com.example.application.data.entity.WalkDto;
import com.example.application.data.service.WalkService;
import com.example.application.views.MainLayout;
import com.example.application.views.walkthedog.WalkthedogView;
import com.vaadin.flow.component.UI;
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
@Route(value = "walks/all", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class MainAllWalks extends VerticalLayout {



    private Grid<WalkDto> grid = new Grid<>(WalkDto.class);
    private TextField filter = new TextField();
    private WalkService walkService;
    private AllWalksView allWalksView;
    private Button createNewWalk = new Button("Create new walk");


    public MainAllWalks(WalkService walkService){
        this.walkService=walkService;

        allWalksView = new AllWalksView(this, walkService);

        filter.setPlaceholder("Filter by Dog name..");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
       // filter.addValueChangeListener(e -> update());
        grid.setColumns("id", "walkDate", "volunteerName", "dogName");

        createNewWalk.addClickListener(e -> {
            UI.getCurrent().navigate(WalkthedogView.class);
        });


        HorizontalLayout toolbar = new HorizontalLayout(filter, createNewWalk);
        HorizontalLayout mainContent = new HorizontalLayout(grid, allWalksView);
        mainContent.setSizeFull();
        grid.setSizeFull();
        allWalksView.setWalk(null);
        add(toolbar, mainContent);
        setSizeFull();

        refresh();
        grid.asSingleSelect().addValueChangeListener(event -> allWalksView.setWalk(grid.asSingleSelect().getValue()));

    }

    public void refresh() {
        grid.setItems(walkService.fetchPlannedWalks());
    }

    private void update() {
       // grid.setItems(walkService.getByName(filter.getValue()));
    }


}
