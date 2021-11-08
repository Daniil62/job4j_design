package ru.job4j.chapter1.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

        PedalBoard firstBoard = PedalBoard.createFromXml(new File(
                ".\\src\\main\\java\\ru\\job4j\\chapter1\\serialization\\json\\file\\pedalboard.xml"));

        String xml = firstBoard.parseToXml();

        System.out.println("\nFirst board to string\n___________________________");
        System.out.println(firstBoard.toString());
        System.out.println("\nFirst board to json\n___________________________");
        System.out.println(firstBoard.parseToJson());
        System.out.println("\nFirst board to xml\n___________________________");
        System.out.println(xml);

        PedalBoard secondBoard = PedalBoard.createFromStringXml(xml);

        System.out.println("\nsecond board to string\n___________________________");
        System.out.println(secondBoard.toString());
    }
}
