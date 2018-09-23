package ferma.service;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputUtil {
    private static Scanner sc = new Scanner(System.in);
    public static int getInputNumber() {
        int number = -1;
        try {
            String input = sc.nextLine();
            number = Integer.parseInt(input);
        } catch (NoSuchElementException | NumberFormatException e) {
            System.out.println("вводите только целые числа.");
        }
        return number;
    }
    public static String getNameLogin(){
        System.out.println("Введите ваш логин");
        return sc.nextLine();
    }
    public static String getNamePass(){
        System.out.println("Введите ваш пароль");
        return sc.nextLine();
    }
}
