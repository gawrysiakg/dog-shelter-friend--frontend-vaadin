package com.example.application.views.home;

import com.example.application.data.entity.api.open_meteo.Weather;
import com.example.application.data.service.WeatherService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.time.LocalDate;


@PageTitle("Dog Shelter Friend")
@Route(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class MydogshelterView extends VerticalLayout {

    private WeatherService weatherService;

    private Weather weather;

    public MydogshelterView(WeatherService weatherService) {
        this.weatherService=weatherService;
        weather = weatherService.getWeather();
        addClassName("home");
        Image img1 = new Image("images/sad3.png", "placeholder plant");
        img1.setClassName("img1");
        add(img1);

        weatherLayout();

        setSpacing(false);

        Image img = new Image("images/dogs-background.png", "placeholder plant");
        img.setWidth("700px");
        add(img);

        H2 header = new H2("Take us for a walk!");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("We will all have fun:)"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    private void weatherLayout(){
        Div div = new Div ();
        div.setClassName("home");
        div.setSizeFull();
        div.setClassName("temperatureDiv");
        H4 date = new H4("Today is "+ LocalDate.now().getDayOfWeek()+ ", "+LocalDate.now()+" Current weather in shelter: ");
        FormLayout weather1 = new FormLayout();
        H4 temperatureMax = new H4("Max temperature: "+weather.getDaily().getTemperature2mMax().get(0)+weather.getDailyUnits().getTemperature2mMax());
        H4 temperatureMin = new H4("Min temperature: "+weather.getDaily().getTemperature2mMin().get(0)+weather.getDailyUnits().getTemperature2mMin());

        H4 wind = new H4("Wind Speed: " + weather.getDaily().getWindspeed10mMax().get(0)+weather.getDailyUnits().getWindspeed10mMax());
        H4 rain = new H4("Rain Probability: "+weather.getDaily().getPrecipitationProbabilityMax().get(0)+weather.getDailyUnits().getPrecipitationProbabilityMax());
        weather1.add(temperatureMin, temperatureMax, wind, rain);

        div.add(date, weather1);
        add(div);
    }

}
