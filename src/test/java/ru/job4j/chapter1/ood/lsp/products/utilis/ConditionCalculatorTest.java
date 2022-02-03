package ru.job4j.chapter1.ood.lsp.products.utilis;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chapter1.ood.lsp.products.model.Food;
import ru.job4j.chapter1.ood.lsp.products.utils.ConditionCalculator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.core.Is.is;

public class ConditionCalculatorTest {

    @Test
    public void whenExpiryDateHasPassedBy0percents() {
        Food product = new Food("product",
                LocalDate.now(),
                LocalDate.now().plus(14, ChronoUnit.DAYS),
                90);
        Assert.assertThat(ConditionCalculator.checkCondition(product), is(0.0));
    }

    @Test
    public void whenExpiryDateHasPassedBy20percents() {
        Food product = new Food("product",
                LocalDate.now().minus(2, ChronoUnit.DAYS),
                LocalDate.now().plus(8, ChronoUnit.DAYS),
                90);
        Assert.assertThat(ConditionCalculator.checkCondition(product), is(20.0));
    }

    @Test
    public void whenExpiryDateHasPassedBy50percents() {
        Food product = new Food("product",
                LocalDate.now().minus(3, ChronoUnit.DAYS),
                LocalDate.now().plus(3, ChronoUnit.DAYS),
                90);
        Assert.assertThat(ConditionCalculator.checkCondition(product), is(50.0));
    }

    @Test
    public void whenExpiryDateHasPassedBy90percents() {
        Food product = new Food("product",
                LocalDate.now().minus(9, ChronoUnit.DAYS),
                LocalDate.now().plus(1, ChronoUnit.DAYS),
                90);
        Assert.assertThat(ConditionCalculator.checkCondition(product), is(90.0));
    }
}
