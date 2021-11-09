package ru.job4j.chapter1.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.chapter1.logging.UsageLog4j;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PedalBoard {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    @XmlAttribute
    private String manufactured;
    @XmlAttribute
    private int deviceCount;
    @XmlAttribute
    private boolean enabled;
    @XmlElementWrapper(name = "effects")
    @XmlElement(name = "effect")
    private Effect[] effects;

    public PedalBoard() {
    }

    public PedalBoard(String manufactured, boolean enabled, Effect[] effects) {
        this.manufactured = manufactured;
        this.enabled = enabled;
        this.effects = effects;
        this.deviceCount = effects.length;
    }

    public String getManufactured() {
        return manufactured;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Effect[] getEffects() {
        return effects;
    }

    public static PedalBoard createFromJson(File file) {
        validate(file);
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                builder.append(s);
            }
        } catch (FileNotFoundException e) {
            LOG.error("Creating from json error: ", e);
        }
        return createFromJsonString(builder.toString());
    }

    public static PedalBoard createFromJsonString(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, PedalBoard.class);
    }

    public String parseToJson() {
        return new GsonBuilder().create().toJson(this);
    }

    public void createJson(String path) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path)))) {
            out.println(parseToJson());
        } catch (FileNotFoundException e) {
            LOG.error("Creating json error: ", e);
        }
    }

    public JSONObject createJsonObject() {
        return new JSONObject(this);
    }

    public String createJsonString() {
        return createJsonObject().toString();
    }

    public static PedalBoard createFromStringXml(String xml) {
        PedalBoard result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(PedalBoard.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            try (StringReader reader = new StringReader(xml)) {
                result = (PedalBoard) unmarshaller.unmarshal(reader);
            }
        } catch (JAXBException e) {
            LOG.error("Creating from string error: ", e);
        }
        return result;
    }

    public static PedalBoard createFromXml(File file) {
        validate(file);
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                builder.append(s);
            }
        } catch (FileNotFoundException e) {
            LOG.error("File not found", e);
        }
        return createFromStringXml(builder.toString());
    }

    public String parseToXml() {
        String result = "";
        try {
            JAXBContext context = JAXBContext.newInstance(PedalBoard.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(this, writer);
                result = writer.getBuffer().toString();
            } catch (IOException | JAXBException e) {
                LOG.error("StringWriter error: ", e);
            }
        } catch (JAXBException e) {
            LOG.error("Parse to xml error: ", e);
        }
        return result;
    }

    public void createXml(String path) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path)))) {
            out.println(parseToXml());
        } catch (FileNotFoundException e) {
            LOG.error("Creating xml error: ", e);
        }
    }

    private static void validate(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found!");
        }
    }

    @Override
    public String toString() {
        return "PedalBoard"
                + System.lineSeparator()
                + "manufactured: " + manufactured + ','
                + System.lineSeparator()
                + "deviceCount=" + deviceCount + ','
                + System.lineSeparator()
                + "enabled: " + enabled + ','
                + System.lineSeparator()
                + "effects: " + Arrays.toString(effects);
    }

    public static void main(String[] args) {

        PedalBoard firstBoard = PedalBoard.createFromJson(new File(
                ".\\src\\main\\java\\ru\\job4j\\chapter1\\serialization\\json\\files\\pedalboard.json"));

        PedalBoard secondBoard = PedalBoard.createFromJsonString(firstBoard.createJsonString());

        System.out.println(firstBoard.createJsonString());
        System.out.println(firstBoard.createJsonObject());

        System.out.println(secondBoard.parseToJson());
    }
}
