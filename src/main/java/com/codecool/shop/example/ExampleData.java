package com.codecool.shop.example;


import com.codecool.shop.factory.ProductFactory;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

public class ExampleData {

    public static  void populateDate(){
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
