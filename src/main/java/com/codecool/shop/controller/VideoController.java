package com.codecool.shop.controller;

import com.codecool.shop.model.Product;
import com.codecool.shop.service.VideoService;
import org.json.JSONException;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static com.codecool.shop.controller.ShopController.productDataStore;

public class VideoController {

    public static String getEmbedCodes(Request req, Response res) throws JSONException, IOException, URISyntaxException {
        Product product = productDataStore.find(Integer.parseInt(req.params(":product-id")));
        List<String> embedCodes = VideoService.getInstance().getEmbedCodes(product.getName());

        return embedCodes.get(1);
    }
}
