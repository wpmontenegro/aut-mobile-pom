package com.bdd.glue;

import com.bdd.step.DemoAppStep;
import com.mobile.integrations.MobileDriverManager;
import com.mobile.util.ScreenShoot;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;

public class DemoAppGlue {

    @Dado("que me encuentro en la aplicación DemoApp")
    public void queMeEncuentroEnLaAplicacionDemoApp(){
        MobileDriverManager.setMobileDriver();
    }

    @Cuando("ingreso al producto {string}")
    public void ingresoAlProducto(String productName) {
        DemoAppStep.goProduct(productName);
        ScreenShoot.takeScreenShoot();
    }

    @Y("lo agrego al carrito")
    public void loAgregoAlCarrito() {
        DemoAppStep.addToCart();
        ScreenShoot.takeScreenShoot();
    }

    @Entonces("se debería mostrar el producto en el carrito")
    public void seDeberíaMostrarElProductoEnElCarrito() {
        Assertions.assertTrue(DemoAppStep.getProductName(), "No se ha agregado el producto al carrito");
        ScreenShoot.takeScreenShoot();
        DemoAppStep.removeProduct();
    }
}
