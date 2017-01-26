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
        // TODO: null if not found, or exception if parseInt fails
        Product product = productDataStore.find(Integer.parseInt(req.params(":product-id")));
        List<String> embedCodes = VideoService.getInstance().getEmbedCodes(product.getName());

        if (embedCodes == null || embedCodes.size() < 1) {
            // TODO: what if no embed code is returned
            return "<iframe width=\\\"560\\\" height=\\\"315\\\" src=\\\"https://www.youtube.com/embed/bbvVof_bkmc\\\" frameborder=\\\"0\\\" alshortfullscreen></iframe>";
        }
        return embedCodes.get(1);
    }
}