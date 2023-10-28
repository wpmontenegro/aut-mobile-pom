package com.bdd.view;

public class ViewFactory {
    public static HomeView homeView() {
        return new HomeView();
    }
    public static ProductView productView() {
        return new ProductView();
    }
    public static MenuView menuView() {return new MenuView();}
    public static MyCartView myCartView() {return new MyCartView();}
}
