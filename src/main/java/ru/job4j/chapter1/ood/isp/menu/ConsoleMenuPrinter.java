package ru.job4j.chapter1.ood.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {

    private static final String DELIMITER = "\\.";
    private static final String MARGIN = "----";
    private static final int MARGIN_THRESHOLD = 2;

    @Override
    public void print(Menu menu) {
        if (menu != null) {
            StringBuilder builder = new StringBuilder();
            for (Menu.MenuItemInfo info : menu) {
                String number = info.getNumber();
                builder.append(getMargin(number))
                        .append(info.getName())
                        .append(" ")
                        .append(number)
                        .append(System.lineSeparator());
            }
            System.out.println(builder.toString());
        }
    }

    private String getMargin(String number) {
        int count = number == null ? 0 : number.split(DELIMITER).length;
        return count < MARGIN_THRESHOLD ? "" : MARGIN.repeat(count - 1).concat(" ");
    }
}
