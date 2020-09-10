package com.example.jetpackdemo.model;

import java.util.List;

public class City  {
    private List<String> hots;
    private List<String> cities;

    public List<String> getHots() {
        return hots;
    }

    public void setHots(List<String> hots) {
        this.hots = hots;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "City{" +
                "hots=" + hots +
                ", cities=" + cities +
                '}';
    }
}
