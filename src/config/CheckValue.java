package config;

import java.util.Scanner;

public class CheckValue {
    public static String checkValue(String label, String regex) {
        try {
            System.out.print("Write the " + label + ": ");
            String text;
            do {
                text = new Scanner(System.in).nextLine();
                if (!text.matches(regex)) {
                    System.out.printf("Invalid %s. Write the valid %s: ", label, label);
                }
            } while (!text.matches(regex));
            return text;
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid value!");
        }
    }
}
