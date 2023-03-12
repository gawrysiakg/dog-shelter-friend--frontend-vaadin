package com.example.application.views.doginfo;

import com.example.application.data.entity.api.dog_ninja_info.DogInfo;
import com.example.application.data.service.DogInfoService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Dog info")
@Route(value = "info", layout = MainLayout.class)
@RolesAllowed({"USER", "ADMIN"})
public class DogInfoView extends VerticalLayout {

    private DogInfoService dogInfoService;
    private DogInfo dogInfo;
    private Button find = new Button("Find Info");
    private ComboBox<String> chooseBreed = new ComboBox<>("Choose dog breed");
    private String breed = "American Bulldog"; //default
    private VerticalLayout infoLayout;

    public DogInfoView(DogInfoService dogInfoService){
        this.dogInfoService=dogInfoService;

        chooseBreed.setItems("American Bulldog", "Boxer", "Bull Terrier", "Bulldog", "Chihuahua", "Chinook", "Chow Chow", "Cocker Spaniel", "Collie",
                "Dalmatian", "Doberman Pinscher", "English Foxhound", "German Pinscher", "Golden Retriever", "Hokkaido", "Icelandic Sheepdog", "Irish Setter",
                "Labrador Retriever", "Miniature Pinscher", "Miniature Schnauzer", "Pekingese");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setAlignItems(Alignment.CENTER);
        find.addClassName("button-layout");
        find.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        find.setHeight(60, Unit.PIXELS);
        find.setWidth(400, Unit.PIXELS);
        chooseBreed.addClassName("button-layout");
        chooseBreed.addThemeVariants(ComboBoxVariant.LUMO_ALIGN_CENTER);
        chooseBreed.setHeight(80, Unit.PIXELS);
        chooseBreed.setWidth(400, Unit.PIXELS);
        verticalLayout.add(chooseBreed, find);
        add(verticalLayout);
        infoLayout = new VerticalLayout();
        add(infoLayout);
        infoLayout.setVisible(false);

        find.addClickListener(buttonClickEvent -> {
            if (chooseBreed.isEmpty()){
                Notification.show("Choose breed", 1200, Notification.Position.MIDDLE);
            } else {
                infoLayout.removeAll();
                breed = chooseBreed.getValue();
                createInfo();
            }
        });


    }

    private void createInfo() {
        infoLayout.setAlignItems(Alignment.CENTER);
        dogInfo=dogInfoService.findDogInfoByBreed(chooseBreed.getValue());
        Image img = new Image(dogInfo.getImageLink(), "no photo");
        img.setWidth("500px");
        H4 dogName = new H4("Dog breed: "+ dogInfo.getName());
        H4 dogBarking = new H4("Barking level: "+ dogInfo.getBarking());
        H4 dogToChildren = new H4("Nice to children level: "+ dogInfo.getGoodWithChildren());
        H4 dogPlayfulness = new H4("Playfulness level: "+ dogInfo.getPlayfulness());
        H4 dogProtectiveness = new H4("Protectiveness level: "+ dogInfo.getProtectiveness());
        H4 dogTrainAbility = new H4("Train ability level: "+ dogInfo.getTrainability());
        H4 dogToOtherDogs = new H4("Good to other dogs level: "+ dogInfo.getGoodWithOtherDogs());
        H4 dogLiveLevel= new H4("Max live level: "+ dogInfo.getMaxLifeExpectancy());
        infoLayout.add(img, dogName, dogBarking, dogToChildren, dogPlayfulness, dogProtectiveness, dogTrainAbility, dogToOtherDogs, dogLiveLevel);
        infoLayout.setVisible(true);

    }

}
