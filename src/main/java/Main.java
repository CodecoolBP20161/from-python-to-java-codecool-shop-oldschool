import com.codecool.shop.controller.OrderController;
import com.codecool.shop.controller.ProductCategoryController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.controller.SupplierController;
import com.codecool.shop.example.ExampleData;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {
    //TODO: interface for controller IFilter
    //TODO: session class handle session in one place
    //TODO: stream
    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // Test data for the memory storage
        ExampleData.populateData();

        // Route for adding products to cart
        post("/add-to-cart/:product-id", OrderController::renderOrder, new ThymeleafTemplateEngine());

        // Store route to be able redirect after adding an order
        before("/product-category/:category-id", (req, res) -> {
            req.session().attribute("path",req.pathInfo());
        });

        // Route for products by product category
        get("/product-category/:category-id", ProductCategoryController::renderProductsByCategory, new ThymeleafTemplateEngine());

        // Store route to be able redirect after adding an order
        before("/supplier/:supplier-id", (req, res) -> {
            req.session().attribute("path",req.pathInfo());
        });

        // Route for products by supplier
        get("/supplier/:supplier-id", SupplierController::renderProductsBySupplier, new ThymeleafTemplateEngine());

        // Store route to be able redirect after adding an order
        before("/", (req, res) -> {
            req.session().attribute("path",req.pathInfo());
        });

        // Route for shopping cart page
        get("/cart", OrderController::renderShoppingCart, new ThymeleafTemplateEngine());

        // Route for main index page
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());

        enableDebugScreen();
    }


}
