package ru.job4j.chapter1.ood.lsp.products;

import org.junit.Test;
import ru.job4j.chapter1.ood.lsp.products.model.Food;
import ru.job4j.chapter1.ood.lsp.products.store.Shop;
import ru.job4j.chapter1.ood.lsp.products.store.Storage;
import ru.job4j.chapter1.ood.lsp.products.store.Trash;
import ru.job4j.chapter1.ood.lsp.products.store.Warehouse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ControlQualityTest {

    @Test
    public void whenFoodAddedInControl() {
        Food product = new Food("some product",
                LocalDate.now().minus(3, ChronoUnit.DAYS),
                LocalDate.now().plus(7, ChronoUnit.DAYS),
                75);
        List<Food> container = List.of(product);
        ControlQuality control = new ControlQuality();
        assertThat(control.getTotalCount(), is(0));
        control.fillContainer(container);
        assertThat(control.getTotalCount(), is(1));
        assertThat(control.getProductById(0), is(product));
    }

    @Test
    public void whenOneProductReplacedFromShopToControlContainer() {
        List<Food> container = new ArrayList<>();
        container.add(new Food("first product",
                LocalDate.now().minus(3, ChronoUnit.DAYS),
                LocalDate.now().plus(7, ChronoUnit.DAYS),
                75));
        container.add(new Food("second product",
                LocalDate.now().minus(40, ChronoUnit.DAYS),
                LocalDate.now(),
                100));
        Storage shop = new Shop(container);
        ControlQuality control = new ControlQuality();
        assertThat(control.getTotalCount(), is(0));
        control.revision(shop);
        assertThat(shop.getTotalCount(), is(1));
        assertThat(control.getTotalCount(), is(1));
        assertThat(control.getProductById(0).getName(), is("second product"));
    }

    @Test
    public void whenOneProductReplacedOutFromWarehouseToControlContainer() {
        List<Food> container = new ArrayList<>();
        container.add(new Food("first product",
                LocalDate.now().minus(7, ChronoUnit.DAYS),
                LocalDate.now().plus(7, ChronoUnit.DAYS),
                430));
        container.add(new Food("second product",
                LocalDate.now(),
                LocalDate.now().plus(100, ChronoUnit.DAYS),
                254));
        Storage warehouse = new Warehouse(container);
        ControlQuality control = new ControlQuality();
        assertThat(control.getTotalCount(), is(0));
        control.revision(warehouse);
        assertThat(warehouse.getTotalCount(), is(1));
        assertThat(control.getTotalCount(), is(1));
        assertThat(control.getProductById(0).getName(), is("first product"));
    }

    @Test
    public void whenOneProductReplacedOutFromWarehouseToShop() {
        List<Food> warehouseContainer = new ArrayList<>();
        List<Food> shopContainer = new ArrayList<>();
        warehouseContainer.add(new Food("first product",
                LocalDate.now().minus(7, ChronoUnit.DAYS),
                LocalDate.now().plus(7, ChronoUnit.DAYS),
                430));
        warehouseContainer.add(new Food("second product",
                LocalDate.now(),
                LocalDate.now().plus(100, ChronoUnit.DAYS),
                254));
        Storage warehouse = new Warehouse(warehouseContainer);
        Storage shop = new Shop(shopContainer);
        ControlQuality control = new ControlQuality();
        control.revision(warehouse);
        assertThat(shop.getTotalCount(), is(0));
        control.revision(shop);
        assertThat(warehouse.getTotalCount(), is(1));
        assertThat(shop.getTotalCount(), is(1));
        assertThat(shop.getProductById(0).getName(), is("first product"));
    }


    @Test
    public void whenOneProductReplacedOutFromWarehouseToShopWithDiscount() {
        List<Food> warehouseContainer = new ArrayList<>();
        List<Food> shopContainer = new ArrayList<>();
        warehouseContainer.add(new Food("first product",
                LocalDate.now().minus(70, ChronoUnit.DAYS),
                LocalDate.now().plus(10, ChronoUnit.DAYS),
                400));
        warehouseContainer.add(new Food("second product",
                LocalDate.now(),
                LocalDate.now().plus(100, ChronoUnit.DAYS),
                254));
        Storage warehouse = new Warehouse(warehouseContainer);
        Storage shop = new Shop(shopContainer);
        ControlQuality control = new ControlQuality();
        control.revision(warehouse);
        assertThat(shop.getTotalCount(), is(0));
        control.revision(shop);
        assertThat(warehouse.getTotalCount(), is(1));
        assertThat(shop.getTotalCount(), is(1));
        assertThat(shop.getProductById(0).getName(), is("first product"));
        assertThat(shop.getProductById(0).getDiscount(), is(50));
    }

    @Test
    public void whenOneProductReplacedOutFromShopToTrash() {
        List<Food> shopContainer = new ArrayList<>();
        List<Food> trashContainer = new ArrayList<>();
        shopContainer.add(new Food("first product",
                LocalDate.now().minus(7, ChronoUnit.DAYS),
                LocalDate.now().plus(7, ChronoUnit.DAYS),
                95));
        shopContainer.add(new Food("second product",
                LocalDate.now().minus(30, ChronoUnit.DAYS),
                LocalDate.now(),
                300));
        Storage shop = new Shop(shopContainer);
        Storage trash = new Trash(trashContainer);
        ControlQuality control = new ControlQuality();
        control.revision(shop);
        assertThat(trash.getTotalCount(), is(0));
        control.revision(trash);
        assertThat(shop.getTotalCount(), is(1));
        assertThat(trash.getTotalCount(), is(1));
        assertThat(trash.getProductById(0).getName(), is("second product"));
    }

    @Test
    public void automaticDistributionTest() {
        List<Food> products = List.of(
                new Food("first product",
                        LocalDate.now().minus(3, ChronoUnit.DAYS),
                        LocalDate.now(),
                        75),
                new Food("second product",
                        LocalDate.now().minus(1, ChronoUnit.DAYS),
                        LocalDate.now().plus(365, ChronoUnit.DAYS),
                        100),
                new Food("third product",
                        LocalDate.now().minus(60, ChronoUnit.DAYS),
                        LocalDate.now(),
                        430),
                new Food("fourth product",
                        LocalDate.now().minus(30, ChronoUnit.DAYS),
                        LocalDate.now().plus(40, ChronoUnit.DAYS),
                        254),
                new Food("fifth product",
                        LocalDate.now().minus(7, ChronoUnit.DAYS),
                        LocalDate.now().plus(7, ChronoUnit.DAYS),
                        430),
                new Food("sixth product",
                        LocalDate.now(),
                        LocalDate.now().plus(100, ChronoUnit.DAYS),
                        254));
        Storage warehouse = new Warehouse(new ArrayList<>());
        Storage shop = new Shop(new ArrayList<>());
        Storage trash = new Trash(new ArrayList<>());
        ControlQuality control = new ControlQuality();
        control.fillContainer(products);

        assertThat(warehouse.getTotalCount(), is(0));
        assertThat(shop.getTotalCount(), is(0));
        assertThat(trash.getTotalCount(), is(0));
        assertThat(control.getTotalCount(), is(6));

        control.automaticDistribution(List.of(warehouse, shop, trash));

        assertThat(warehouse.getTotalCount(), is(2));
        assertThat(shop.getTotalCount(), is(2));
        assertThat(trash.getTotalCount(), is(2));
        assertThat(control.getTotalCount(), is(0));
    }
}
