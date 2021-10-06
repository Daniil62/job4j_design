package ru.job4j.chapter1.examples;

public class BitOperations {

    public String convertDecValueToBin(int value) {
        return new StringBuilder().append(Integer.toBinaryString(value)).reverse().toString();
    }

    public int convertBinValueToDec(String value) {
        int result = 0;
        for (int i = 0; i < value.length(); i++) {
            result += Math.pow(2, i) * (value.charAt(i) == '1' ? 1 : 0);
        }
        return result;
    }

    public String moveLeft(int value, int quantity) {
        return convertDecValueToBin(value << quantity);
    }

    public String moveRight(int value, int quantity) {
        return convertDecValueToBin(value >> quantity);
    }

    public String compareByXOR(int value1, int value2) {
        return convertDecValueToBin(value1 ^ value2);
    }

    public String compareByBitwiseAnd(int value1, int value2) {
        return convertDecValueToBin(value1 & value2);
    }

    public String compareByBitwiseOr(int value1, int value2) {
        return convertDecValueToBin(value1 | value2);
    }

    public static void main(String[] args) {
        BitOperations operations = new BitOperations();
        int value1 = 759;
        int value2 = 894;

        System.out.println("value1 = " + operations.convertDecValueToBin(value1)
                + "\nvalue2 = " + operations.convertDecValueToBin(value2) + "\n");

        System.out.println("Move value1 to 2 steps left:\n" + operations.moveLeft(value1, 2) + "\n");

        System.out.println("Decimal value1 after moving to 2 steps left = \n"
                + operations.convertBinValueToDec(operations.moveLeft(value1, 2)) + "\n");

        System.out.println("Move value2 to 2 steps right:\n" + operations.moveRight(value2, 2) + "\n");

        System.out.println("Decimal value2 after moving to 2 steps right = \n"
                + operations.convertBinValueToDec(operations.moveRight(value2, 2)) + "\n");

        System.out.println("Compare values by XOR:\n" + operations.compareByXOR(value1, value2) + "\n");

        System.out.println("Compare values by bitwise and:\n" + operations.compareByBitwiseAnd(value1, value2) + "\n");

        System.out.println("Compare values by bitwise or:\n" + operations.compareByBitwiseOr(value1, value2) + "\n");
    }
}
