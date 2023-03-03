package com.example.application.views;

import com.example.application.components.appnav.AppNav;
import com.example.application.components.appnav.AppNavItem;
import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.VolunteerService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.volunteers.AddNewVolunteerByAdmin;
//import com.example.application.views.volunteers.VolunteerView;
import com.example.application.views.allwalks.AllwalksView;
import com.example.application.views.dogs.MainDogsView;
import com.example.application.views.gallery.GalleryView;
import com.example.application.views.myaccount.MyaccountView;
import com.example.application.views.mydogshelter.MydogshelterView;
import com.example.application.views.mywalks.MywalksView;
import com.example.application.views.newwalk.NewwalkView;
import com.example.application.views.volunteers.edit.MainVolunteersView;
import com.example.application.views.walksadd.WalksaddView;
import com.example.application.views.walkthedog.WalkthedogView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.Optional;

/**
 * The main view is a top-level placeholder for other views.
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
        H1 appName = new H1("My Dog Shelter");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        if (accessChecker.hasAccess(MydogshelterView.class)) {
            nav.addItem(new AppNavItem("My dog shelter", MydogshelterView.class, "la la-info"));

        }
        if (accessChecker.hasAccess(GalleryView.class)) {
            nav.addItem(new AppNavItem("Gallery", GalleryView.class, "la la-tablet"));

        }
        if (accessChecker.hasAccess(UploadView.class)) {
            nav.addItem(new AppNavItem("Add photo", UploadView.class, "la la-align-justify"));

        }
        if (accessChecker.hasAccess(WalkthedogView.class)) {
            nav.addItem(new AppNavItem("Walk the dog", WalkthedogView.class, "la la-walking"));

        }
        if (accessChecker.hasAccess(MyaccountView.class)) {
            nav.addItem(new AppNavItem("My account", MyaccountView.class, "la la-user"));

        }

        if (accessChecker.hasAccess(AddNewVolunteerByAdmin.class)) {
            nav.addItem(new AppNavItem("Add new volunteer", AddNewVolunteerByAdmin.class, "la la-user"));

        }
        if (accessChecker.hasAccess(MainDogsView.class)) {
            nav.addItem(new AppNavItem("Dogs", MainDogsView.class, "la la-filter"));

        }
//        if (accessChecker.hasAccess(VolunteerView.class)) {
//            nav.addItem(new AppNavItem("Volunteers", VolunteerView.class, "la la-align-justify"));
//
//        }
        if (accessChecker.hasAccess(MainVolunteersView.class)) {
            nav.addItem(new AppNavItem("Volunteers edit", MainVolunteersView.class, "la la-align-justify"));

        }
        if (accessChecker.hasAccess(AllwalksView.class)) {
            nav.addItem(new AppNavItem("All walks", AllwalksView.class, "la la-align-justify"));

        }
        if (accessChecker.hasAccess(MywalksView.class)) {
            nav.addItem(new AppNavItem("My walks", MywalksView.class, "la la-align-justify"));

        }
        if (accessChecker.hasAccess(NewwalkView.class)) {
            nav.addItem(new AppNavItem("New walk", NewwalkView.class, "la la-align-justify"));

        }

        if (accessChecker.hasAccess(WalksaddView.class)) {
            nav.addItem(new AppNavItem("Walks/add", WalksaddView.class, "la la-file"));

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
                Anchor loginLink = new Anchor("login", "Sign in");
                layout.add(loginLink);
            }

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
