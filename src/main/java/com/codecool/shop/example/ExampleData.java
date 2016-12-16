package com.codecool.shop.example;


import com.codecool.shop.model.*;

public class ExampleData {

    public static void populateData() {
        OrderStatus inCart = OrderStatus.IN_CART;
        OrderStatus checked = OrderStatus.CHECKED_OUT;

        Supplier amazon = ProductFactory.getInstance().supplier(1, "Amazon", "Digital content and services");
        Supplier lenovo = ProductFactory.getInstance().supplier(2, "Lenovo", "Computers");
        Supplier codecool = ProductFactory.getInstance().supplier(3, "Codecool", "Course");

        //setting up a new product category
        ProductCategory tablet = ProductFactory.getInstance().productCategory(1, "Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory laptop = ProductFactory.getInstance().productCategory(2, "Laptop", "Hardware", " A laptop computer");
        ProductCategory course = ProductFactory.getInstance().productCategory(3, "Course", "Education", "Learning materials");

        //setting up products and printing it
        Product fire = ProductFactory.getInstance().product(1, "Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        Product mix = ProductFactory.getInstance().product(2, "Lenovo IdeaPad Miix 700", 479f, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);
        Product fireHD = ProductFactory.getInstance().product(3, "Amazon Fire HD 8", 89f, "USD", "Amazon latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon);
        Product yoga = ProductFactory.getInstance().product(4, "Lenovo YogaBook 530", 499.9f, "USD", "The affordable & light Yoga 500 is a multimode laptop with fast, cutting-edge processing power.", laptop, lenovo);
        Product codecools = ProductFactory.getInstance().product(5, "Codecool Programming Course", 2000f, "USD", "Codecool - Where we train programmers with project experience in up to 18 months.", course, codecool);


    }
}
