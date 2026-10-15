package Practica1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Lab1.run(args, sc); // ПР №1
        Lab2.run();         // ПР №2
        Lab3.run();         // ПР №3
        Lab4.run(sc);       // ПР №4

        sc.close();
    }
}
