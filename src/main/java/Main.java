import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.ProductCategoryController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.factory.ProductFactory;
import com.codecool.shop.model.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Always add generic routes to the end
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());

        get("/product-category/:id", ProductCategoryController::renderProductsByCategory, new ThymeleafTemplateEngine());

        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        //setting up a new supplier
        Supplier amazon = ProductFactory.getInstance().supplier("Amazon", "Digital content and services");
        Supplier lenovo = ProductFactory.getInstance().supplier("Lenovo", "Computers");
        Supplier codecool = ProductFactory.getInstance().supplier("Codecool", "Course");

        //setting up a new product category
        ProductCategory tablet = ProductFactory.getInstance().productCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory laptop = ProductFactory.getInstance().productCategory("Laptop", "Hardware", " A laptop computer");
        ProductCategory course = ProductFactory.getInstance().productCategory("Course", "Education", "Learning materials");

        //setting up products and printing it
        ProductFactory.getInstance().product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        ProductFactory.getInstance().product("Lenovo IdeaPad Miix 700", 479f, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);
        ProductFactory.getInstance().product("Amazon Fire HD 8", 89f, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon);
        ProductFactory.getInstance().product("Lenovo YogaBook 530", 2000f, "USD", "Yogabook", laptop, lenovo);
        ProductFactory.getInstance().product("Codecool Programming Course", 543f, "USD", "From base to advanced", course, codecool);
    }


}
