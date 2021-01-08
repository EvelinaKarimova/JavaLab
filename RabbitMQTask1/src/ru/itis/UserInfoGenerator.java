package ru.itis;

import java.util.Scanner;

public class UserInfoGenerator {
    public static String getUserInfoFromConsole(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your last name:");
        String str = scanner.nextLine();
        System.out.println("Your first name:");
        str = str + "/" + scanner.nextLine();
        System.out.println("Your day of birth:");
        str = str + "/" + scanner.nextLine();
        System.out.println("Your passport id:");
        str = str + "/" + scanner.nextLine();
        System.out.println("Passport date of issue:");
        str = str + "/" + scanner.nextLine();
        return str;
    }
}
