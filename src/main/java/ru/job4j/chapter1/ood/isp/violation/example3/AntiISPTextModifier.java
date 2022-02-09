package ru.job4j.chapter1.ood.isp.violation.example3;

/***
 * Третий пример нарушения ISP.
 * Интерфейс предоставляет к реализации
 * чрезмерное количество методов, часть которых
 * точно будет не нужна в рамках одного класса.
 */
public interface AntiISPTextModifier {

    String quotationMarks(String text);

    String curlyBrackets(String text);

    String roundBrackets(String text);

    String squareBrackets(String text);

    String oneWordToLine(String text);

    void whiteText(String text);

    void blueText(String text);

    void redText(String text);

    void greenText(String text);

    void yellowText(String text);

    void purpleText(String text);

    void turquoiseText(String text);
}
