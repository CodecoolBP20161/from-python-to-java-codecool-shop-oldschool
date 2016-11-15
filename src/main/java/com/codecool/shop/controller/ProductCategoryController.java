package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ProductCategoryController {

    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        ProductDaoMem productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDaoMem productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDaoMem supplierDataStore = SupplierDaoMem.getInstance();


        Map params = new HashMap<>();
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(req.params(":id")))));
        params.put("category", productCategoryDataStore.find(Integer.parseInt(req.params(":id"))));
        params.put("categories", productCategoryDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

}
