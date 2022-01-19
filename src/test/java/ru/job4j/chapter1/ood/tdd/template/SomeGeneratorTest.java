package ru.job4j.chapter1.ood.tdd.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SomeGeneratorTest {

    @Ignore
    @Test
    public void whenGenerationSuccess() {
        assertThat(new SomeGenerator().produce("The capital of ${country} is ${city}.",
                Map.of("country", "Russia", "city", "Moscow")),
                is("The capital of Russia is Moscow."));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenOneOfKeysInvalid() {
        new SomeGenerator().produce("The capital of ${country} is ${city}.",
                Map.of("land", "Russia", "city", "Moscow"));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenHasExtraPair() {
        new SomeGenerator().produce("The capital of ${country} is ${city}.",
                Map.of("country", "Russia", "city", "Moscow", "urban", "Ryazan"));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenOneOfPairsIsMissing() {
        new SomeGenerator().produce("The capital of ${country} is ${city}.",
                Map.of("country", "Russia"));
    }
}
