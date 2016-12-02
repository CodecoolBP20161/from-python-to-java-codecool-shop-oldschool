package com.codecool.shop.model;


public class Customer {
    private String name;
    private String email;
    private String phone;

    // fixme: should address be a separated model and DB table???
    private String billingCountry;
    private String billingCity;
    private String billingZipcode;
    private String billingAddress;

    private String shippingCountry;
    private String shippingCity;
    private String shippingZipcode;
    private String shippingAddress;

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Customer(
            String name,
            String email,
            String phone,
            String billingCountry,
            String billingCity,
            String billingZipcode,
            String billingAddress,
            String shippingCountry,
            String shippingCity,
            String shippingZipcode,
            String shippingAddress
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingCountry = billingCountry;
        this.billingCity = billingCity;
        this.billingZipcode = billingZipcode;
        this.billingAddress = billingAddress;
        this.shippingCountry = shippingCountry;
        this.shippingCity = shippingCity;
        this.shippingZipcode = shippingZipcode;
        this.shippingAddress = shippingAddress;
    }
}
