package com.example.application.data.service;

import com.example.application.data.client.GalleryClient;
import com.example.application.data.entity.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryClient galleryClient;


    public List<ImageDto> fetchImages() {
        return galleryClient.getImages();
    }


    public ImageDto uploadImage(String link) {
        return galleryClient.uploadImage(link);
    }


}
