package com.codecool.shop.controller;

import com.codecool.shop.dao.DataStorageFactory;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Cart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;


public abstract class ShopController {

    //
    protected static ProductDao productDataStore = DataStorageFactory.productDaoFactory();
    protected static ProductCategoryDao productCategoryDataStore = DataStorageFactory.productCategoryDaoFactory();
    protected static SupplierDao supplierDataStore = DataStorageFactory.supplierDaoFactory();


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
            params.put("products", productDataStore.getBy(supplierDataStore.find(Integer.parseInt(req.params(":supplier-id")))));
            params.put("supplier", supplierDataStore.find(Integer.parseInt(req.params(":supplier-id"))));
        }

        // get cart items for display
        if (req.session().attribute("order") != null) {
            Cart cart = req.session().attribute("order");
            params.put("lineitems", cart.sumLineItemQuantitiesInCart());
        }

        return new ModelAndView(params, "product/index");
    }

}