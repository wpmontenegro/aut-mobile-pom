package com.bdd.step;

import com.mobile.exceptions.AutomationException;
import com.mobile.models.DataManager;

import static com.bdd.view.ViewFactory.*;

public class DemoAppStep {

    private static final String PRODUCT_NAME = "productName";

    public static void goProduct(String productName) {
        try {
            homeView().tapProduct(productName);
        } catch (Exception e) {
            throw new AutomationException("No se ha encontrado el producto", e);
        }
        DataManager.getInstance().getTestData().put(PRODUCT_NAME, productName);
    }

    public static void addToCart() {
        productView().tapAddToCart();
        menuView().tapCartMenu();
    }

    public static boolean getProductName() {
        return myCartView().getProductLabel().equalsIgnoreCase(DataManager.getInstance().getTestData().get(PRODUCT_NAME));
    }

    public static void removeProduct() {
        myCartView().tapRemoveItem();
    }
}