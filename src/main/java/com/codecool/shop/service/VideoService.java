package com.codecool.shop.service;

public class VideoService {
    public static VideoService INSTANCE;

    private VideoService() {
    }

    public static VideoService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VideoService();
        }
        return INSTANCE;
    }
    // request video embed codes for the inserted 'product' argument
}
