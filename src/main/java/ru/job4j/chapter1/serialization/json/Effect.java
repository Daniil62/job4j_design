package ru.job4j.chapter1.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "effect")
public class Effect {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean enabled;
    @XmlAttribute
    private boolean bypass;
    @XmlElementWrapper(name = "controls")
    @XmlElement(name = "control")
    private String[] controls;
    @XmlAttribute
    private byte inputs;
    @XmlAttribute
    private byte outputs;
    @XmlAttribute
    private byte voltage;

    public Effect() {
    }

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

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isBypass() {
        return bypass;
    }

    public String[] getControls() {
        return controls;
    }

    public byte getInputs() {
        return inputs;
    }

    public byte getOutputs() {
        return outputs;
    }

    public byte getVoltage() {
        return voltage;
    }

    @Override
    public String toString() {
        return System.lineSeparator()
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
