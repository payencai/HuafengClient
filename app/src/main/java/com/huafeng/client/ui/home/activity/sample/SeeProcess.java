package com.huafeng.client.ui.home.activity.sample;

import java.util.List;

public class SeeProcess {
    private String name;

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public SeeProcess(String name, List<Child> childList) {
        this.name = name;
        this.childList = childList;
    }

    private List<Child> childList;
    public static class Child{


        private int isout;
        private String name;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        private double price;
        public Child(int isout, String name,double price) {
            this.isout = isout;
            this.name = name;
            this.price=price;
        }

        public int getIsout() {
            return isout;
        }

        public void setIsout(int isout) {
            this.isout = isout;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
