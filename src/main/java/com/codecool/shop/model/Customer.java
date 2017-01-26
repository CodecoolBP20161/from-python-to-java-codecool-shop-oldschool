package com.codecool.shop.model;


public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;

    private String billingCountry;
    private String billingCity;
    private String billingZipcode;
    private String billingAddress;

    private String shippingCountry;
    private String shippingCity;
    private String shippingZipcode;
    private String shippingAddress;

    public Customer() {
        this.id = IdFactory.getInstance().id(this.getClass());
    }

    public Customer(String name, String email, String phone) {
        this();
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
        this();
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getBillingZipcode() {
        return billingZipcode;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public String getShippingZipcode() {
        return shippingZipcode;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", billingCountry='" + billingCountry + '\'' +
                ", billingCity='" + billingCity + '\'' +
                ", billingZipcode='" + billingZipcode + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", shippingCountry='" + shippingCountry + '\'' +
                ", shippingCity='" + shippingCity + '\'' +
                ", shippingZipcode='" + shippingZipcode + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                '}';
    }
}
