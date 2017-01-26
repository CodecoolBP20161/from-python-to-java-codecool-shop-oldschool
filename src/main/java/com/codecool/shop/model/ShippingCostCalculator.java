package com.codecool.shop.model;


public class ShippingCostCalculator {
    private String name, destination, origin, details, currency;
    private int cost, timeInHours, distanceInKm;

    public ShippingCostCalculator(String name, String destination, int cost, String origin, String details,
                                  String currency, int timeInHours, int distanceInKm) {
        this.name = name;
        this.destination = destination;
        this.cost = cost;
        this.origin = origin;
        this.details = details;
        this.currency = currency;
        this.timeInHours = timeInHours;
        this.distanceInKm = distanceInKm;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDetails() {
        return details;
    }

    public String getCurrency() {
        return currency;
    }

    public int getCost() {
        return cost;
    }

    public int getTimeInHours() {
        return timeInHours;
    }

    public int getDistanceInKm() {
        return distanceInKm;
    }

}
