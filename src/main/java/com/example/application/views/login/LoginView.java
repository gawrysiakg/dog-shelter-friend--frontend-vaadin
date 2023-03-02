package com.example.application.views.login;

import com.example.application.data.Role;
//import com.example.application.data.entity.LoginDto;
//import com.example.application.data.service.AuthService;
import com.example.application.data.service.VolunteerService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.dogs.MainView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.RouteUtil;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;






//@AnonymousAllowed
//@PageTitle("Login")
//@Route(value = "login")
//public class LoginView extends VerticalLayout {
//
//
//    public LoginView(AuthService authService) {
//        setId("login-view");
//
//        FormLayout loginForm = setLoginForm();
//
//        var username = new TextField("Username");
//        var password = new PasswordField("Password");
//
//        Button loginButton = new Button("Login", event -> {
//            String auth = authService.authenticate(username.getValue(), password.getValue());
//            if (auth.contains("Success")) {
//                Role role = VaadinSession.getCurrent().getAttribute(LoginDto.class).getRole();
//                if (role == Role.USER) UI.getCurrent().navigate("/sport/user");
//                if (role == Role.ADMIN) UI.getCurrent().navigate("/sport/admin");
//            } else {
//                Notification.show(auth);
//            }
//        });
//
//        loginButton.addClickShortcut(Key.ENTER);
//
//        Button registerButton = new Button("Register", event -> {
//            UI.getCurrent().navigate("/sport/registration");
//        });
//
//        add(
//                new H1("Please Log In to Your account!"),
//                loginForm,
//                username,
//                password,
//                loginButton,
//                registerButton
//        );
//    }
//
//
//    private FormLayout setLoginForm() {
//        FormLayout loginForm = new FormLayout();
//
//        Label headLabel = new Label("Login credentials if you are using a database from GitHub");
//        headLabel.getStyle().set("text-align", "center");
//        headLabel.getStyle().set("font-weight", "bold");
//        headLabel.getStyle().set("font-size", "20px");
//
//        Label userLabel = new Label("USER");
//        userLabel.getStyle().set("text-align", "center");
//        userLabel.getStyle().set("font-weight", "bold");
//
//        Label adminLabel = new Label("ADMIN");
//        adminLabel.getStyle().set("text-align", "center");
//        adminLabel.getStyle().set("font-weight", "bold");
//
//        Label userUser = new Label("Username: 1");
//        userUser.getStyle().set("text-align", "center");
//
//        Label adminInfo = new Label("Username: 2");
//        adminInfo.getStyle().set("text-align", "center");
//
//        Label userPass = new Label("Password: 1");
//        userPass.getStyle().set("text-align", "center");
//
//        Label adminPass = new Label("Password: 2");
//        adminPass.getStyle().set("text-align", "center");
//
//        loginForm.add(headLabel, userLabel, adminLabel, userUser, adminInfo,
//                userPass, adminPass);
//        loginForm.setResponsiveSteps(
//                new FormLayout.ResponsiveStep("0", 2),
//                new FormLayout.ResponsiveStep("500px", 2));
//        loginForm.setColspan(headLabel, 2);
//        loginForm.getStyle().set("border", "2px solid #0015e7");
//        loginForm.setWidthFull();
//
//        return loginForm;
//    }
//}
//
//
//
//


@AnonymousAllowed
@PageTitle("Login")
@Route(value = "login")
public class LoginView extends LoginOverlay implements BeforeEnterObserver {

    private final AuthenticatedUser authenticatedUser;

    public LoginView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("My Dog Shelter");
        i18n.getHeader().setDescription(" ");
        i18n.getForm().setForgotPassword("Sign Up" );
        i18n.setAdditionalInformation(null);
        setForgotPasswordButtonVisible(true);

        addForgotPasswordListener(event -> {
            UI.getCurrent().navigate("register");
        });
            close();//login.close();

        setI18n(i18n);
        setOpened(true);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
//        if (authenticatedUser.get().isPresent()) {
//            // Already logged in
//            setOpened(false);
//            event.forwardTo("dogs");
//        }

        setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }
}
