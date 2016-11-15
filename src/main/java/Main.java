import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.ProductCategoryController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.controller.SupplierController;
import com.codecool.shop.example.ExampleData;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // Test data for the memory storage
        ExampleData.populateDate();

        // Route for products by product category
        get("/product-category/:id", ProductCategoryController::renderProductsByCategory, new ThymeleafTemplateEngine());

        // Route for products by supplier
        get("/supplier/:id", SupplierController::renderProductsBySupplier, new ThymeleafTemplateEngine());

        // Route for main index page
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());

        enableDebugScreen();
    }




}
