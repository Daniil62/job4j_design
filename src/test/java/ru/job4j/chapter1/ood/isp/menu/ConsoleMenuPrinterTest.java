package ru.job4j.chapter1.ood.isp.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleMenuPrinterTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    private ByteArrayOutputStream out;
    private PrintStream printStream;

    @Before
    public void before() {
        out = new ByteArrayOutputStream();
        printStream = System.out;
        System.setOut(new PrintStream(out));
    }

    @After
    public void after() {
        System.setOut(printStream);
    }

    @Test
    public void whenMenuBuildSuccess() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Реализовать структуру для поддержания меню.", STUB_ACTION);

        menu.add("Реализовать структуру для поддержания меню.",
                "Реализовать поиск в методе findItem() на основе DFS итератора.", STUB_ACTION);

        menu.add("Реализовать поиск в методе findItem() на основе DFS итератора.",
                "На основе метода findItem() реализовать методы add() и select().", STUB_ACTION);

        menu.add("Реализовать структуру для поддержания меню.",
                "на основе существующего итератора реализовать метод iterator().", STUB_ACTION);

        menu.add("Реализовать структуру для поддержания меню.",
                "Создать класс-реализацию MenuPrinter для вывода в консоль.", STUB_ACTION);

        menu.add("Создать класс-реализацию MenuPrinter для вывода в консоль.",
                "Сделать отступы как в примере.", STUB_ACTION);

        menu.add("Реализовать структуру для поддержания меню.",
                "Дописать тесты на метод select() и на вывод.", STUB_ACTION);

        menu.add("Реализовать структуру для поддержания меню.",
                "Создать класс TODOApp для построения и вывода списка задач пользователя.", STUB_ACTION);
        
        new ConsoleMenuPrinter().print(menu);

        String expected = "Реализовать структуру для поддержания меню. 1."
               + System.lineSeparator()
               + "---- Реализовать поиск в методе findItem() на основе DFS итератора. 1.1."
               + System.lineSeparator()
               + "-------- На основе метода findItem() реализовать методы add() и select(). 1.1.1."
               + System.lineSeparator()
               + "---- на основе существующего итератора реализовать метод iterator(). 1.2."
               + System.lineSeparator()
               + "---- Создать класс-реализацию MenuPrinter для вывода в консоль. 1.3."
               + System.lineSeparator()
               + "-------- Сделать отступы как в примере. 1.3.1."
               + System.lineSeparator()
               + "---- Дописать тесты на метод select() и на вывод. 1.4."
               + System.lineSeparator()
               + "---- Создать класс TODOApp для построения и вывода списка задач пользователя. 1.5."
               + System.lineSeparator()
               + System.lineSeparator();

        assertThat(new String(out.toByteArray()), is(expected));
    }
}
