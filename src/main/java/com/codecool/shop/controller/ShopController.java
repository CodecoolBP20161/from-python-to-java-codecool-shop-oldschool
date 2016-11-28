package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;
import com.codecool.shop.model.Order;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;


public abstract class ShopController {

    //get singleton instance to handle data in memory
    protected static ProductDaoMem productDataStore = ProductDaoMem.getInstance();
    protected static ProductCategoryDaoMem productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    protected static SupplierDaoMem supplierDataStore = SupplierDaoMem.getInstance();


    public static ModelAndView render(Request req, Response res) {


        Map params = new HashMap<>();
        //products and product filters
        params.put("products", productDataStore.getAll());
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());

        if (req.params(":category-id") != null) {
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(req.params(":category-id")))));
            params.put("category", productCategoryDataStore.find(Integer.parseInt(req.params(":category-id"))));
        }

        if (req.params(":supplier-id") != null) {
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(req.params(":supplier-id")))));
            params.put("supplier", supplierDataStore.find(Integer.parseInt(req.params(":supplier-id"))));
        }

        // get cart items for display
        if (req.session().attribute("order") != null) {
            Order order = req.session().attribute("order");
            params.put("lineitems", order.sumLineItemQuantitiesInCart());
        }

        return new ModelAndView(params, "product/index");
    }

}