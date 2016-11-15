package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class SupplierController {

    public static ModelAndView renderProductsBySupplier(Request req, Response res) {
        ProductDaoMem productDataStore = ProductDaoMem.getInstance();
        SupplierDaoMem supplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("products", productDataStore.getBy(supplierDataStore.find(Integer.parseInt(req.params(":id")))));
        params.put("suppliers", supplierDataStore.getAll());
        params.put("supplier", supplierDataStore.find(Integer.parseInt(req.params(":id"))));
        return new ModelAndView(params, "product/index");
    }

}
