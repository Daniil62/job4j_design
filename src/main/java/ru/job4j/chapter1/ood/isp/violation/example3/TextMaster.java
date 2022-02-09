package ru.job4j.chapter1.ood.isp.violation.example3;

public class TextMaster implements AntiISPTextModifier {

    @Override
    public String quotationMarks(String text) {
        StringBuilder builder = new StringBuilder("\"");
        if (text != null) {
            builder.append(text);
        }
        builder.append("\"");
        return builder.toString();
    }

    @Override
    public String curlyBrackets(String text) {
        StringBuilder builder = new StringBuilder("{");
        if (text != null) {
            builder.append(text);
        }
        builder.append("}");
        return builder.toString();
    }

    @Override
    public String roundBrackets(String text) {
        StringBuilder builder = new StringBuilder("(");
        if (text != null) {
            builder.append(text);
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public String squareBrackets(String text) {
        StringBuilder builder = new StringBuilder("[");
        if (text != null) {
            builder.append(text);
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public String oneWordToLine(String text) {
        StringBuilder builder = new StringBuilder();
        if (text != null) {
            String[] words = text.split(" ");
            for (String word : words) {
                builder.append(word)
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    @Override
    public void whiteText(String text) {
        System.out.println(Colors.WHITE + text + Colors.RESET);
    }

    @Override
    public void blueText(String text) {
        System.out.println(Colors.BLUE + text + Colors.RESET);
    }

    @Override
    public void redText(String text) {
        System.out.println(Colors.RED + text + Colors.RESET);
    }

    @Override
    public void greenText(String text) {
        System.out.println(Colors.GREEN + text + Colors.RESET);
    }

    @Override
    public void yellowText(String text) {
        System.out.println(Colors.YELLOW + text + Colors.RESET);
    }

    @Override
    public void purpleText(String text) {
        System.out.println(Colors.PURPLE + text + Colors.RESET);
    }

    @Override
    public void turquoiseText(String text) {
        System.out.println(Colors.TURQUOISE + text + Colors.RESET);
    }

    private static class Colors {

        static final String WHITE = "\u001b[30m";
        static final String BLUE = "\u001b[34m";
        static final String RED = "\u001b[31m";
        static final String GREEN = "\u001b[32m";
        static final String YELLOW = "\u001b[33m";
        static final String PURPLE = "\u001b[35m";
        static final String TURQUOISE = "\u001b[36m";
        static final String RESET = "\u001b[0m";
    }

    public static void main(String[] args) {
        TextMaster master = new TextMaster();
        master.whiteText("Job4j");
        master.blueText("Job4j");
        master.redText("Job4j");
        master.greenText("Job4j");
        master.yellowText("Job4j");
        master.purpleText("Job4j");
        master.turquoiseText("Job4j");
    }
}
