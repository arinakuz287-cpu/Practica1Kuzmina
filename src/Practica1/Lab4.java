package Practica1;

import java.util.*;

public class Lab4 {

    // Задание 1
    enum Season {
        WINTER(-5) {
            @Override public String getDescription() { return "Холодное время года"; }
        },
        SPRING(10) {
            @Override public String getDescription() { return "Холодное время года"; }
        },
        SUMMER(25) {
            @Override public String getDescription() { return "Теплое время года"; }
        },
        AUTUMN(8) {
            @Override public String getDescription() { return "Холодное время года"; }
        };

        private final int avgTemp;
        Season(int avgTemp) { this.avgTemp = avgTemp; }

        public int getAvgTemp() { return avgTemp; }
        public abstract String getDescription();
    }

    static void task1_Seasons() {
        System.out.println("// Задание 1");

        // 1) любимое время года
        Season favorite = Season.SUMMER;
        System.out.println("Любимое: " + favorite
                + ", t=" + favorite.getAvgTemp()
                + ", " + favorite.getDescription());

        // 2) switch
        switch (favorite) {
            case SUMMER: System.out.println("Я люблю лето");  break;
            case WINTER: System.out.println("Я люблю зиму");  break;
            case SPRING: System.out.println("Я люблю весну"); break;
            case AUTUMN: System.out.println("Я люблю осень"); break;
        }

        // 6) цикл по всем значениям
        for (Season s : Season.values()) {
            System.out.printf("%s: t=%d°C, %s%n",
                    s.name(), s.getAvgTemp(), s.getDescription());
        }
    }

    // Задание 2
    enum Size {
        XXS(32) {
            @Override public String getDescription() { return "Детский размер"; }
        },
        XS(34), S(36), M(38), L(40);

        private final int euroSize;
        Size(int euroSize) { this.euroSize = euroSize; }

        public int getEuroSize() { return euroSize; }
        public String getDescription() { return "Взрослый размер"; }
    }

    interface MenClothing   { void dressMan(); }
    interface WomenClothing { void dressWomen(); }

    static abstract class Clothes {
        protected Size size;
        protected double price;
        protected String color;

        public Clothes(Size size, double price, String color) {
            this.size = size;
            this.price = price;
            this.color = color;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName()
                    + "[size=" + size + " (" + size.getEuroSize() + ")"
                    + ", price=" + price + ", color=" + color + "]";
        }
    }

    static class TShirt extends Clothes implements MenClothing, WomenClothing {
        public TShirt(Size s, double p, String c) { super(s, p, c); }
        @Override public void dressMan()   { System.out.println("Одеть мужчину: " + this); }
        @Override public void dressWomen() { System.out.println("Одеть женщину: " + this); }
    }
    static class Pants extends Clothes implements MenClothing, WomenClothing {
        public Pants(Size s, double p, String c) { super(s, p, c); }
        @Override public void dressMan()   { System.out.println("Одеть мужчину: " + this); }
        @Override public void dressWomen() { System.out.println("Одеть женщину: " + this); }
    }
    static class Skirt extends Clothes implements WomenClothing {
        public Skirt(Size s, double p, String c) { super(s, p, c); }
        @Override public void dressWomen() { System.out.println("Одеть женщину: " + this); }
    }
    static class Tie extends Clothes implements MenClothing {
        public Tie(Size s, double p, String c) { super(s, p, c); }
        @Override public void dressMan() { System.out.println("Одеть мужчину: " + this); }
    }

    static class Atelier {
        public static void dressWomen(Clothes[] clothes) {
            System.out.println(">> Женская одежда:");
            for (Clothes c : clothes)
                if (c instanceof WomenClothing) ((WomenClothing) c).dressWomen();
        }
        public static void dressMan(Clothes[] clothes) {
            System.out.println(">> Мужская одежда:");
            for (Clothes c : clothes)
                if (c instanceof MenClothing) ((MenClothing) c).dressMan();
        }
    }

    static void task2_Clothes() {
        System.out.println("Задание 2");

        Clothes[] arr = {
                new TShirt(Size.M, 1500, "Белый"),
                new Pants(Size.L, 3000, "Чёрный"),
                new Skirt(Size.S, 2500, "Красный"),
                new Tie(Size.XS, 800, "Синий")
        };
        Atelier.dressWomen(arr);
        Atelier.dressMan(arr);
    }

    // Задание 3
    enum Category { ELECTRONICS, CLOTHES, BOOKS }

    static class Product {
        String name; double price;
        Product(String name, double price) { this.name = name; this.price = price; }
        @Override public String toString() { return name + " — " + price + " руб."; }
    }

    static void task3_Shop(Scanner sc) {
        System.out.println("Задание 3");

        System.out.print("Логин: ");  String login = sc.nextLine();
        System.out.print("Пароль: "); String pass  = sc.nextLine();
        if (!login.equals("admin") || !pass.equals("1234")) {
            System.out.println("Неверные данные! (пропускаем магазин)");
            return;
        }

        Map<Category, List<Product>> catalog = new EnumMap<>(Category.class);
        catalog.put(Category.ELECTRONICS, Arrays.asList(
                new Product("Ноутбук", 60000), new Product("Телефон", 30000)));
        catalog.put(Category.CLOTHES, Arrays.asList(
                new Product("Футболка", 1500), new Product("Джинсы", 4000)));
        catalog.put(Category.BOOKS, Arrays.asList(
                new Product("Java. Полное руководство", 2500)));

        System.out.println("Каталоги: " + Arrays.toString(Category.values()));

        List<Product> cart = new ArrayList<>();
        while (true) {
            System.out.print("Каталог (или 'exit'): ");
            String cmd = sc.nextLine();
            if (cmd.equalsIgnoreCase("exit")) break;

            Category cat;
            try { cat = Category.valueOf(cmd.toUpperCase()); }
            catch (Exception e) { System.out.println("Нет такого каталога"); continue; }

            List<Product> items = catalog.get(cat);
            for (int i = 0; i < items.size(); i++)
                System.out.println(i + ". " + items.get(i));

            System.out.print("Номер товара: ");
            int idx = Integer.parseInt(sc.nextLine());
            cart.add(items.get(idx));
            System.out.println("Добавлено: " + items.get(idx));
        }

        double total = 0;
        for (Product p : cart) total += p.price;
        System.out.println("В корзине: " + cart);
        System.out.println("Итого: " + total + " руб.");
    }

    // Задание 4
    enum Brand { INTEL, AMD, NVIDIA, SAMSUNG, LG, DELL }

    static class Processor {
        Brand brand; String model; double frequency;
        Processor(Brand b, String m, double f) { brand = b; model = m; frequency = f; }
        @Override public String toString() {
            return "Processor[" + brand + " " + model + ", " + frequency + " GHz]";
        }
    }
    static class Memory {
        int sizeGb; Brand brand;
        Memory(int s, Brand b) { sizeGb = s; brand = b; }
        @Override public String toString() { return "Memory[" + sizeGb + " GB, " + brand + "]"; }
    }
    static class Monitor {
        double diagonal; Brand brand;
        Monitor(double d, Brand b) { diagonal = d; brand = b; }
        @Override public String toString() { return "Monitor[" + diagonal + "\", " + brand + "]"; }
    }
    static class Computer {
        Processor processor; Memory memory; Monitor monitor;
        Computer(Processor p, Memory m, Monitor mon) {
            processor = p; memory = m; monitor = mon;
        }
        @Override public String toString() {
            return "Computer{\n  " + processor + "\n  " + memory + "\n  " + monitor + "\n}";
        }
    }

    static void task4_Computer() {
        System.out.println("Задание 4");
        Computer pc = new Computer(
                new Processor(Brand.INTEL, "Core i7-12700", 3.6),
                new Memory(32, Brand.SAMSUNG),
                new Monitor(27, Brand.LG));
        System.out.println(pc);
    }

    // Задание 5
    static class Book {
        String title; String author;
        Book(String title, String author) { this.title = title; this.author = author; }
        @Override public String toString() { return title + " (" + author + ")"; }
    }

    static class Reader {
        String fullName;
        int ticketNumber;
        String faculty;
        String birthDate;
        String phone;

        Reader(String fullName, int ticketNumber, String faculty,
               String birthDate, String phone) {
            this.fullName = fullName;
            this.ticketNumber = ticketNumber;
            this.faculty = faculty;
            this.birthDate = birthDate;
            this.phone = phone;
        }

        void takeBook(int count) {
            System.out.println(fullName + " взял " + count + " книги");
        }
        void takeBook(String... titles) {
            System.out.println(fullName + " взял книги: " + String.join(", ", titles));
        }
        void takeBook(Book... books) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < books.length; i++) {
                if (i > 0) sb.append(", ");
                sb.append(books[i].title);
            }
            System.out.println(fullName + " взял книги: " + sb);
        }
        void returnBook(int count) {
            System.out.println(fullName + " вернул " + count + " книги");
        }
        void returnBook(String... titles) {
            System.out.println(fullName + " вернул книги: " + String.join(", ", titles));
        }
        void returnBook(Book... books) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < books.length; i++) {
                if (i > 0) sb.append(", ");
                sb.append(books[i].title);
            }
            System.out.println(fullName + " вернул книги: " + sb);
        }

        @Override
        public String toString() {
            return "Reader[" + fullName + ", билет №" + ticketNumber
                    + ", " + faculty + ", " + birthDate + ", тел. " + phone + "]";
        }
    }

    static void task5_Library() {
        System.out.println("Задание 5");

        Reader[] readers = {
                new Reader("Петров В. В.", 101, "ФИТ", "01.05.2000", "+7-900-111-22-33"),
                new Reader("Иванова А. С.", 102, "ФЭУ", "12.10.1999", "+7-900-444-55-66")
        };
        for (Reader r : readers) System.out.println(r);

        Book b1 = new Book("Приключения", "Иванов И. И.");
        Book b2 = new Book("Словарь", "Петров П. П.");
        Book b3 = new Book("Энциклопедия", "Сидоров С. С.");

        readers[0].takeBook(3);
        readers[0].takeBook("Приключения", "Словарь");
        readers[0].takeBook(b1, b2, b3);

        readers[1].returnBook(3);
        readers[1].returnBook("Приключения", "Словарь", "Энциклопедия");
        readers[1].returnBook(b1, b2, b3);
    }

    // Задание 6
    static class Employer {
        String firstName; String lastName; double income;
        Employer(String firstName, String lastName, double income) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.income = income;
        }
        double getIncome() { return income * 12; }
        @Override public String toString() {
            return "Employer[" + firstName + " " + lastName + "]";
        }
    }

    static class Manager extends Employer {
        double averageSum;
        Manager(String firstName, String lastName, double income, double averageSum) {
            super(firstName, lastName, income);
            this.averageSum = averageSum;
        }
        @Override double getIncome() { return super.getIncome() + averageSum; }
        @Override public String toString() {
            return "Manager[" + firstName + " " + lastName
                    + ", средняя доплата=" + averageSum + "]";
        }
    }

    static void task6_Employer() {
        System.out.println("Задание 6");

        Employer e = new Manager("Иван", "Петров", 50000, 20000);
        System.out.printf("%s -> income = %.2f%n", e, e.getIncome());

        Employer[] staff = {
                new Employer("Анна", "Смирнова", 40000),
                new Manager("Пётр", "Кузнецов", 55000, 15000),
                new Employer("Олег", "Соколов", 45000),
                new Manager("Мария", "Попова", 60000, 25000)
        };
        for (Employer emp : staff)
            System.out.printf("%s -> income = %.2f%n", emp, emp.getIncome());
    }

    // Задание 7
    static abstract class Pupil {
        protected String fullName;
        protected int age;
        Pupil(String fullName, int age) { this.fullName = fullName; this.age = age; }
        abstract String getStatus();
        @Override public String toString() {
            return getStatus() + ": " + fullName + ", возраст " + age;
        }
    }

    static class Schoolchild extends Pupil {
        int grade;
        Schoolchild(String fullName, int age, int grade) {
            super(fullName, age); this.grade = grade;
        }
        @Override String getStatus() { return "Школьник " + grade + " класса"; }
    }

    static class Student extends Pupil {
        int course; String university;
        Student(String fullName, int age, int course, String university) {
            super(fullName, age); this.course = course; this.university = university;
        }
        @Override String getStatus() {
            return "Студент " + course + " курса (" + university + ")";
        }
    }

    static void task7_Pupils() {
        System.out.println("Задание 7");

        Pupil[] pupils = {
                new Schoolchild("Маша Иванова", 12, 6),
                new Student("Игорь Петров", 20, 3, "МГУ"),
                new Schoolchild("Саша Козлов", 15, 9),
                new Student("Лена Смирнова", 19, 2, "МФТИ"),
                new Schoolchild("Дима Орлов", 10, 4),
                new Student("Оля Кузнецова", 22, 5, "ВШЭ")
        };

        System.out.println(">> Студенты:");
        for (Pupil p : pupils)
            if (p instanceof Student) System.out.println("  " + p);

        System.out.println(">> Школьники:");
        for (Pupil p : pupils)
            if (p instanceof Schoolchild) System.out.println("  " + p);
    }

    // Задание 8
    static abstract class Shape {
        protected String color;
        protected boolean filled;

        public Shape() { this.color = "blue"; this.filled = false; }
        public Shape(String color, boolean filled) {
            this.color = color; this.filled = filled;
        }

        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }
        public boolean isFilled() { return filled; }
        public void setFilled(boolean filled) { this.filled = filled; }

        public abstract double getArea();
        public abstract double getPerimeter();

        @Override
        public String toString() {
            return "Shape[color=" + color + ", filled=" + filled + "]";
        }
    }

    static class Circle extends Shape {
        protected double radius;
        public Circle() { super(); this.radius = 1.0; }
        public Circle(double radius) { super(); this.radius = radius; }
        public Circle(double radius, String color, boolean filled) {
            super(color, filled); this.radius = radius;
        }
        public double getRadius() { return radius; }
        public void setRadius(double radius) { this.radius = radius; }
        @Override public double getArea() { return Math.PI * radius * radius; }
        @Override public double getPerimeter() { return 2 * Math.PI * radius; }
        @Override public String toString() {
            return "Circle[" + super.toString() + ", radius=" + radius + "]";
        }
    }

    static class Rectangle extends Shape {
        protected double width;
        protected double length;

        public Rectangle() { super(); this.width = 1.0; this.length = 1.0; }
        public Rectangle(double width, double length) {
            super(); this.width = width; this.length = length;
        }
        public Rectangle(double width, double length, String color, boolean filled) {
            super(color, filled); this.width = width; this.length = length;
        }

        public double getWidth() { return width; }
        public void setWidth(double width) { this.width = width; }
        public double getLength() { return length; }
        public void setLength(double length) { this.length = length; }

        @Override public double getArea() { return width * length; }
        @Override public double getPerimeter() { return 2 * (width + length); }
        @Override public String toString() {
            return "Rectangle[" + super.toString()
                    + ", width=" + width + ", length=" + length + "]";
        }
    }

    static class Square extends Rectangle {
        public Square() { super(); }
        public Square(double side) { super(side, side); }
        public Square(double side, String color, boolean filled) {
            super(side, side, color, filled);
        }
        public double getSide() { return getWidth(); }
        public void setSide(double side) { super.setWidth(side); super.setLength(side); }
        @Override public void setWidth(double side) { super.setWidth(side); super.setLength(side); }
        @Override public void setLength(double side) { super.setWidth(side); super.setLength(side); }
        @Override public String toString() {
            return "Square[" + super.toString() + "]";
        }
    }

    static void task8_ShapeAbstract() {
        System.out.println("Задание 8");

        Shape[] shapes = {
                new Circle(5.5, "RED", false),
                new Rectangle(2.0, 3.0, "GREEN", true),
                new Square(4.0, "BLUE", true)
        };

        for (Shape s : shapes) {
            System.out.println(s);
            System.out.printf("  площадь = %.3f, периметр = %.3f%n",
                    s.getArea(), s.getPerimeter());
        }

        // демонстрация полиморфизма + downcast
        Shape s1 = new Circle(5.5, "RED", false);
        System.out.println("Upcast Circle -> Shape: " + s1);
        Circle c1 = (Circle) s1;
        System.out.println("Downcast -> getRadius(): " + c1.getRadius());
    }

    // Задание 9
    static abstract class Furniture {
        protected String name;
        protected double price;
        Furniture(String name, double price) { this.name = name; this.price = price; }
        abstract String getType();
        @Override public String toString() {
            return getType() + " " + name + " (" + price + " руб.)";
        }
    }
    static class Chair extends Furniture {
        Chair(String name, double price) { super(name, price); }
        @Override String getType() { return "Стул"; }
    }
    static class Table extends Furniture {
        Table(String name, double price) { super(name, price); }
        @Override String getType() { return "Стол"; }
    }
    static class Sofa extends Furniture {
        Sofa(String name, double price) { super(name, price); }
        @Override String getType() { return "Диван"; }
    }

    static class FurnitureShop {
        public static void show(Furniture[] items) {
            for (Furniture f : items) System.out.println(f);
        }
    }

    static void task9_Furniture() {
        System.out.println("Задание 9");
        Furniture[] items = {
                new Chair("IKEA Markus", 5000),
                new Table("Дубовый", 15000),
                new Sofa("Угловой", 40000)
        };
        FurnitureShop.show(items);
    }

    // Задание 10
    static abstract class Transport {
        abstract double timePassengers(double d);
        abstract double costPassengers(double d);
        abstract double timeCargo(double d);
        abstract double costCargo(double d);
    }
    static class Car extends Transport {
        @Override double timePassengers(double d) { return d / 90.0; }
        @Override double costPassengers(double d) { return d * 15; }
        @Override double timeCargo(double d)      { return d / 70.0; }
        @Override double costCargo(double d)      { return d * 10; }
    }
    static class Plane extends Transport {
        @Override double timePassengers(double d) { return d / 800.0 + 1.5; }
        @Override double costPassengers(double d) { return d * 8 + 3000; }
        @Override double timeCargo(double d)      { return d / 700.0 + 2.0; }
        @Override double costCargo(double d)      { return d * 5 + 5000; }
    }
    static class Train extends Transport {
        @Override double timePassengers(double d) { return d / 70.0; }
        @Override double costPassengers(double d) { return d * 3; }
        @Override double timeCargo(double d)      { return d / 60.0; }
        @Override double costCargo(double d)      { return d * 2; }
    }
    static class Ship extends Transport {
        @Override double timePassengers(double d) { return d / 40.0; }
        @Override double costPassengers(double d) { return d * 2; }
        @Override double timeCargo(double d)      { return d / 30.0; }
        @Override double costCargo(double d)      { return d * 1.5; }
    }

    static void task10_Transport() {
        System.out.println("Задание 10");
        Transport[] ts = { new Car(), new Plane(), new Train(), new Ship() };
        double dist = 1000;
        for (Transport t : ts) {
            System.out.printf("%s:%n", t.getClass().getSimpleName());
            System.out.printf("  пассажиры: %.2f ч, %.2f руб.%n",
                    t.timePassengers(dist), t.costPassengers(dist));
            System.out.printf("  груз:      %.2f ч, %.2f руб.%n",
                    t.timeCargo(dist), t.costCargo(dist));
        }
    }

    public static void run(Scanner sc) {
        System.out.println("\nПРАКТИКА №4");

        task1_Seasons();
        task2_Clothes();
        task3_Shop(sc);
        task4_Computer();
        task5_Library();
        task6_Employer();
        task7_Pupils();
        task8_ShapeAbstract();
        task9_Furniture();
        task10_Transport();
    }
}