package ru.job4j.chapter1.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class PedalBoard {

    private String manufactured;
    private int deviceCount;
    private boolean enabled;
    private Effect[] effects;

    public PedalBoard(String manufactured, boolean enabled, Effect[] effects) {
        this.manufactured = manufactured;
        this.enabled = enabled;
        this.effects = effects;
        this.deviceCount = effects.length;
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
                + "effects=" + Arrays.toString(effects);
    }

    public static void main(String[] args) {

        String json = "{\n"
                + "  \"manufactured\" : \"Boss\",\n"
                + "  \"deviceCount\" : 2,\n"
                + "  \"enabled\" : true,\n"
                + "  \"effects\" : [\n"
                + "    {\n"
                + "      \"name\" : \"Boss NS-2\",\n"
                + "      \"enabled\" : true,\n"
                + "      \"bypass\" : false,\n"
                + "      \"controls\" : [\n"
                + "        \"threshold\", \"decay\", \"mode\"\n"
                + "      ],\n"
                + "      \"inputs\" : 2,\n"
                + "      \"outputs\" : 2,\n"
                + "      \"voltage\" : 9\n"
                + "    },\n"
                + "    {\n"
                + "      \"name\" : \"Boss PW-2\",\n"
                + "      \"enabled\" : true,\n"
                + "      \"bypass\" : false,\n"
                + "      \"controls\" : [\n"
                + "        \"level\", \"fat\", \"muscle\", \"drive\"\n"
                + "      ],\n"
                + "      \"inputs\" : 1,\n"
                + "      \"outputs\" : 1,\n"
                + "      \"voltage\" : 9\n"
                + "    },\n"
                + "    {\n"
                + "      \"name\" : \"Boss DD-7\",\n"
                + "      \"enabled\" : false,\n"
                + "      \"bypass\" : false,\n"
                + "      \"controls\" : [\n"
                + "        \"effect level\", \"feedback\", \"delay time\", \"mode\"\n"
                + "      ],\n"
                + "      \"inputs\" : 3,\n"
                + "      \"outputs\" : 2,\n"
                + "      \"voltage\" : 9\n"
                + "    }\n"
                + "  ]\n"
                + "}";

        Gson gson = new GsonBuilder().create();
        PedalBoard board = gson.fromJson(json, PedalBoard.class);
        String fromObjectToJson = gson.toJson(board);
        System.out.println(board.toString());
        System.out.println(fromObjectToJson);
    }
}
