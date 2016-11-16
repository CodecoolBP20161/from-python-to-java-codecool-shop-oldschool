package com.codecool.shop.controller;

import com.codecool.shop.model.Order;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;


public abstract class ShopController {

    protected static ProductDaoMem productDataStore = ProductDaoMem.getInstance();
    protected static ProductCategoryDaoMem productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    protected static SupplierDaoMem supplierDataStore = SupplierDaoMem.getInstance();

    protected void path(Request req){
        req.session().attribute("path",req.pathInfo());
    }

    public static ModelAndView render(Request req, Response res) {


        Map params = new HashMap<>();
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

        if (req.session().attribute("order") != null) {
            Order order = req.session().attribute("order");
            params.put("lineitems", order.getNumberOfLineItemsInCart());
        }

        return new ModelAndView(params, "product/index");
    }

}