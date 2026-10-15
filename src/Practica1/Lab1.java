package Practica1;

import java.util.Scanner;

public class Lab1 {

    // Задание 3
    static void task3() {
        System.out.println("Задание 3");
        int[] arr = {5, 10, 15, 20, 25};
        int sum = 0;
        for (int i = 0; i < arr.length; i++) sum += arr[i];
        double avg = (double) sum / arr.length;
        System.out.println("Сумма: " + sum);
        System.out.println("Среднее арифметическое: " + avg);
    }

    // Задание 4
    static void task4(Scanner sc) {
        System.out.println("Задание 4");
        System.out.print("Введите размер массива: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Введите элементы массива:");
        int i = 0;
        while (i < n) { arr[i] = sc.nextInt(); i++; }

        int sum = 0, j = 0;
        do { sum += arr[j]; j++; } while (j < n);

        int min = arr[0], max = arr[0];
        for (int k = 1; k < n; k++) {
            if (arr[k] < min) min = arr[k];
            if (arr[k] > max) max = arr[k];
        }

        System.out.println("Сумма: " + sum);
        System.out.println("Минимум: " + min);
        System.out.println("Максимум: " + max);
    }

    // Задание 5
    static void task5(String[] args) {
        System.out.println("Задание 5");
        if (args.length == 0) {
            System.out.println("Аргументы не переданы.");
            return;
        }
        for (int i = 0; i < args.length; i++)
            System.out.println("Аргумент " + i + ": " + args[i]);
    }

    // Задание 6
    static void task6() {
        System.out.println("Задание 6");
        double sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += 1.0 / i;
            System.out.printf("H%d = %.4f%n", i, sum);
        }
    }

    // Задание 7
    static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    static void task7() {
        System.out.println("Задание 7");
        System.out.println("5! = " + factorial(5));
        System.out.println("10! = " + factorial(10));
    }

    public static void run(String[] args, Scanner sc) {
        System.out.println("\nПРАКТИКА №1");
        task3();
        task4(sc);
        task5(args);
        task6();
        task7();
    }
}
