package com.example.application.views.mywalks;

import com.example.application.data.entity.WalkDto;
import com.example.application.data.service.WalkService;
import com.example.application.views.MainLayout;
import com.example.application.views.walkthedog.WalkthedogView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.annotation.security.RolesAllowed;

@Route(value = "walks/my", layout = MainLayout.class)
@RolesAllowed({"ADMIN", "USER"})
public class MainMyWalks extends VerticalLayout {

    private Grid<WalkDto> grid = new Grid<>(WalkDto.class);
    private WalkService walkService;
    private Button createNewWalk = new Button("Create new walk");
    private Button cancelWalk = new Button("Cancel this walk");
    private String currentPrincipalName;
    Authentication authentication;

    public MainMyWalks(WalkService walkService) {
        this.walkService = walkService;
        authentication = SecurityContextHolder.getContext().getAuthentication();
        currentPrincipalName = authentication.getName();
        grid.setColumns("id", "walkDate", "volunteerName", "dogName");
        createNewWalk.addClickListener(e -> {
                    UI.getCurrent().navigate(WalkthedogView.class);
                 });
        HorizontalLayout toolbar = new HorizontalLayout(createNewWalk, cancelWalk);
        HorizontalLayout mainContent = new HorizontalLayout(grid);
        cancelWalk.setVisible(false);
        mainContent.setSizeFull();
        grid.setSizeFull();
        add(grid);
        setSizeFull();
        add(toolbar);
        grid.addSelectionListener(event ->{
                    cancelWalk.setVisible(true);
                });

        cancelWalk.addClickListener(e -> {
                    walkService.deleteWalk( grid.asSingleSelect().getValue().getId());
                    refresh();
                });
            refresh();
    }
    private void refresh() {
        grid.setItems(walkService.fetchPlannedWalksForVolunteer(currentPrincipalName));
    }

}
