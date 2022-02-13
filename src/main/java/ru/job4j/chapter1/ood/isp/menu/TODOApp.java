package ru.job4j.chapter1.ood.isp.menu;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TODOApp {

    private final Menu menu;
    private final ActionDelegate action;
    private final MenuPrinter printer;

    private final Scanner scanner = new Scanner(System.in);

    private final List<String> innerMenuItems = List.of(
            "1: Show menu",
            "2: execute item",
            "3: add item",
            "4: exit");

    private final static String HEADER = " Select action";
    private final static String NOT_FOUND = " item not found.";
    private final static String ENTER_NAME = " enter item name: ";
    private final static String ENTER_PARENT_ITEM_NAME = " enter parent item name:";
    private final static String ENTER_CHILD_ITEM_NAME = " enter child item name:";
    private static final String DEFAULT_MESSAGE = " you enter some nonsense.";

    public TODOApp(Menu menu, ActionDelegate action, MenuPrinter printer) {
        this.menu = menu;
        this.action = action;
        this.printer = printer;
    }

    private void executeItem() {
        Optional<Menu.MenuItemInfo> item = menu.select(scanner.nextLine());
        if (item.isPresent()) {
            item.get().getActionDelegate().delegate();
        } else {
            System.out.println(NOT_FOUND);
        }
    }

    private void addItem() {
        System.out.println(ENTER_PARENT_ITEM_NAME);
        String parent = scanner.nextLine();
        System.out.println(ENTER_CHILD_ITEM_NAME);
        String child = scanner.nextLine();
        menu.add(parent, child, action);
    }

    private void showInnerMenu() {
        System.out.println(HEADER);
        for (String item : innerMenuItems) {
            System.out.println(item);
        }
    }

    private int parseId(String value) {
        int result = -1;
        if (value.matches("\\d")) {
            result = Integer.parseInt(value);
        }
        return result;
    }

    public void startUI() {
        boolean run = true;
        while (run) {
            showInnerMenu();
            int id = parseId(scanner.nextLine());
            switch (id) {
                case 1:
                    printer.print(menu);
                    break;

                case 2:
                    System.out.println(ENTER_NAME);
                    executeItem();
                    break;

                case 3:
                    addItem();
                    break;

                case 4:
                    run = false;
                    break;

                default:
                    System.out.println(DEFAULT_MESSAGE + System.lineSeparator());
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        ActionDelegate action = System.out::println;

        menu.add(Menu.ROOT, "Сходить в магазин", action);
        menu.add(Menu.ROOT, "Покормить кота", action);
        menu.add("Сходить в магазин", "Купить продукты", action);
        menu.add("Купить продукты", "Купить хлеб", action);
        menu.add("Купить продукты", "Купить молоко", action);

        new TODOApp(menu, System.out::println, new ConsoleMenuPrinter()).startUI();
    }

}
