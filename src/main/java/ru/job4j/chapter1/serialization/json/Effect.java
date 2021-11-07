package ru.job4j.chapter1.serialization.json;

import java.util.Arrays;

public class Effect {

    private String name;
    private boolean enabled;
    private boolean bypass;
    private String[] controls;
    private byte inputs;
    private byte outputs;
    private byte voltage;

    public Effect(String name, boolean enabled, boolean bypass,
                  String[] controls, byte inputs, byte outputs, byte voltage) {
        this.name = name;
        this.enabled = enabled;
        this.bypass = bypass;
        this.controls = controls;
        this.inputs = inputs;
        this.outputs = outputs;
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return "Effect"
                + System.lineSeparator()
                + "Name: " + name + ','
                + System.lineSeparator()
                + "enabled: " + enabled + ','
                + System.lineSeparator()
                + "bypass: " + bypass + ','
                + System.lineSeparator()
                + "controls: " + Arrays.toString(controls) + ','
                + System.lineSeparator()
                + "inputs: " + inputs + ','
                + System.lineSeparator()
                + "outputs: " + outputs + ','
                + System.lineSeparator()
                + "voltage: " + voltage;
    }
}
