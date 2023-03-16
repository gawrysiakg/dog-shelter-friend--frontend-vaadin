package com.example.application.views;

import com.example.application.components.appnav.AppNav;
import com.example.application.components.appnav.AppNavItem;
import com.example.application.data.Role;
import com.example.application.data.entity.DogDto;
import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.DogService;
import com.example.application.data.service.VolunteerService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.allwalks.MainAllWalks;
import com.example.application.views.doginfo.DogInfoView;
import com.example.application.views.gallery.UploadView;
import com.example.application.views.mywalks.MainMyWalks;
import com.example.application.views.volunteers.AddNewVolunteerByAdmin;
import com.example.application.views.allwalks.AllWalksView;
import com.example.application.views.dogs.MainDogsView;
import com.example.application.views.gallery.GalleryView;
import com.example.application.views.myaccount.MyAccountView;
import com.example.application.views.home.MydogshelterView;
import com.example.application.views.volunteers.edit.MainVolunteersView;
import com.example.application.views.about.AboutUsView;
import com.example.application.views.walkthedog.WalkthedogView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.util.Optional;

/**
 * The main view is a top-level placeholder for other views.
 *
 *
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;
    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;
    private VolunteerService volunteerService;


    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker, VolunteerService volunteerService) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;
        this.volunteerService = volunteerService;
        if (volunteerService.fetchVolunteers().isEmpty()){
        volunteerService.createVolunteer( new VolunteerDto("Admin Name", "Admin Surname", "ADMIN", "ADMIN", "admin@dsf.pl", 987654321, Role.ADMIN));
        volunteerService.createVolunteer( new VolunteerDto("Volunteer Name", "Volunteer Surname", "USER", "USER", "user@dsf.pl", 654321987, Role.USER));
    }

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");
        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Dog Shelter Friend");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);
        Scroller scroller = new Scroller(createNavigation());
        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {

        AppNav nav = new AppNav();

        if (accessChecker.hasAccess(MydogshelterView.class)) {
            nav.addItem(new AppNavItem("Home", MydogshelterView.class, "la la-info"));

        }
        if (accessChecker.hasAccess(DogInfoView.class)) {
            nav.addItem(new AppNavItem("Dog Info", DogInfoView.class, "la la-tablet"));

        }
        if (accessChecker.hasAccess(GalleryView.class)) {
            nav.addItem(new AppNavItem("Gallery", GalleryView.class, "la la-tablet"));

        }
        if (accessChecker.hasAccess(UploadView.class)) {
            nav.addItem(new AppNavItem("Add photo", UploadView.class, "la la-tablet"));
        }
        if (accessChecker.hasAccess(WalkthedogView.class)) {
            nav.addItem(new AppNavItem("Walk the dog", WalkthedogView.class, "la la-walking"));
        }

        if (accessChecker.hasAccess(MainDogsView.class)) {
            nav.addItem(new AppNavItem("Dogs", MainDogsView.class, "la la-filter"));
        }

        if (accessChecker.hasAccess(MainVolunteersView.class)) {
            nav.addItem(new AppNavItem("Volunteers", MainVolunteersView.class, "la la-align-justify"));
        }
        if (accessChecker.hasAccess(AddNewVolunteerByAdmin.class)) {
            nav.addItem(new AppNavItem("Add new volunteer", AddNewVolunteerByAdmin.class, "la la-user"));
        }
        if (accessChecker.hasAccess(MainAllWalks.class)) {
            nav.addItem(new AppNavItem("Walks", MainAllWalks.class, "la la-align-justify"));

        }
        if (accessChecker.hasAccess(AllWalksView.class)) {
            nav.addItem(new AppNavItem("All walks", AllWalksView.class, "la la-align-justify"));

        }
        if (accessChecker.hasAccess(MainMyWalks.class)) {
            nav.addItem(new AppNavItem("My walks", MainMyWalks.class, "la la-align-justify"));

        }
        if (accessChecker.hasAccess(MyAccountView.class)) {
            nav.addItem(new AppNavItem("My account", MyAccountView.class, "la la-user"));

        }

        if (accessChecker.hasAccess(AboutUsView.class)) {
            nav.addItem(new AppNavItem("About us", AboutUsView.class, "la la-file"));

        }


        return nav;
    }


    private Footer createFooter() {
        Footer layout = new Footer();

        if(volunteerService.fetchVolunteers().size()>0) {

            Optional<VolunteerDto> maybeUser = authenticatedUser.get();
            if (maybeUser.isPresent()) {
                VolunteerDto volunteerDto = maybeUser.get();

                Avatar avatar = new Avatar(volunteerDto.getName());
                avatar.setImage("icons/dog.png");
                avatar.setThemeName("xsmall");
                avatar.getElement().setAttribute("tabindex", "-1");

                MenuBar userMenu = new MenuBar();
                userMenu.setThemeName("tertiary-inline contrast");

                MenuItem userName = userMenu.addItem("");
                Div div = new Div();
                div.add(avatar);
                div.add(volunteerDto.getName());
                div.add(new Icon("lumo", "dropdown"));
                div.getElement().getStyle().set("display", "flex");
                div.getElement().getStyle().set("align-items", "center");
                div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
                userName.add(div);
                userName.getSubMenu().addItem("Sign out", e -> {
                    authenticatedUser.logout();
                });

                layout.add(userMenu);
            } else {
                UI.getCurrent().navigate("register");
                Anchor loginLink = new Anchor("login", "Sign in");
                layout.add(loginLink);

            }
        } else {
          UI.getCurrent().navigate("login");
        }
        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }



}
