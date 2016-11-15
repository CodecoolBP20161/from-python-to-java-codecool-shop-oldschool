package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public abstract class Controller {

    protected static ProductDaoMem productDataStore = ProductDaoMem.getInstance();
    protected static ProductCategoryDaoMem productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    protected static SupplierDaoMem supplierDataStore = SupplierDaoMem.getInstance();
//    protected static Map params = new HashMap<>();

    public static ModelAndView render(Request req, Response res){
        Map params = new HashMap<>();
        params.put("products", productDataStore.getAll());
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());

        if (req.params(":id")!=null) {
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(req.params(":id")))));
            params.put("category", productCategoryDataStore.find(Integer.parseInt(req.params(":id"))));
            params.put("supplier", supplierDataStore.find(Integer.parseInt(req.params(":id"))));
        }
        return new ModelAndView(params, "product/index");
    }
}