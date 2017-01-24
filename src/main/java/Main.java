import com.codecool.shop.controller.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.io.IOException;
import java.net.URISyntaxException;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // Route for adding products to cart
        post("/add-to-cart/:product-id", CartController::renderOrder, new ThymeleafTemplateEngine());

        // Store route to be able redirect after adding an order
        before("/product-category/:category-id", (req, res) -> {
            req.session().attribute("path", req.pathInfo());
        });

        // Route for products by product category
        get("/product-category/:category-id", ProductCategoryController::renderProductsByCategory, new ThymeleafTemplateEngine());

        // Store route to be able redirect after adding an order
        before("/supplier/:supplier-id", (req, res) -> {
            req.session().attribute("path", req.pathInfo());
        });

        // Route for products by supplier
        get("/supplier/:supplier-id", SupplierController::renderProductsBySupplier, new ThymeleafTemplateEngine());

        // Store route to be able redirect after adding an order
        before("/", (req, res) -> {
            req.session().attribute("path", req.pathInfo());
        });

        // Route for shopping cart page
        get("/cart", CartController::renderShoppingCart, new ThymeleafTemplateEngine());

        get("/checkout", CartController::renderCheckOut, new ThymeleafTemplateEngine());

        post("/checkout", CartController::saveCustomerDetails, new ThymeleafTemplateEngine());

        get("/payment", CartController::renderPayment, new ThymeleafTemplateEngine());

        get("/order/:order-id", EmailController::renderEmail, new ThymeleafTemplateEngine());

        // Route for main index page
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());

        enableDebugScreen();
    }


}
