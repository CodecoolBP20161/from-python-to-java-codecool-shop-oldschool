package com.codecool.shop.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class VideoService {
    public static VideoService INSTANCE;
    private String API_URL = "https:0.0.0.0:60000/apivideos";
    private String PARAM_KEY = "search";

    private VideoService() {
    }

    public static VideoService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VideoService();
        }
        return INSTANCE;
    }

    public List getEmbedCodes(String productName) throws URISyntaxException, IOException, JSONException {
        List embedCodes = new ArrayList();
        URIBuilder uriBuilder = new URIBuilder(API_URL);
        uriBuilder.addParameter(PARAM_KEY, productName);
        String response = Request.Get(uriBuilder.build()).execute().returnContent().asString();
        JSONArray videos = new JSONArray(response);
        for (int i = 0; i < videos.length(); i++) {
            JSONObject video = (JSONObject) videos.get(i);
            embedCodes.add(video.get("embed code").toString());
        }
        return embedCodes;
    }
}
