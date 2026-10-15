package Practica1;

public class Lab3 {

    // SHAPE
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
        protected double width, length;
        public Rectangle() { super(); this.width = 1.0; this.length = 1.0; }
        public Rectangle(double width, double length) {
            super(); this.width = width; this.length = length;
        }
        public Rectangle(double width, double length, String color, boolean filled) {
            super(color, filled); this.width = width; this.length = length;
        }
        public double getWidth() { return width; }
        public void setWidth(double w) { this.width = w; }
        public double getLength() { return length; }
        public void setLength(double l) { this.length = l; }
        @Override public double getArea() { return width * length; }
        @Override public double getPerimeter() { return 2 * (width + length); }
        @Override public String toString() {
            return "Rectangle[" + super.toString() + ", width=" + width + ", length=" + length + "]";
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
        @Override public void setWidth(double s) { super.setWidth(s); super.setLength(s); }
        @Override public void setLength(double s) { super.setWidth(s); super.setLength(s); }
        @Override public String toString() { return "Square[" + super.toString() + "]"; }
    }

    // MOVABLE
    interface Movable {
        void moveUp();
        void moveDown();
        void moveLeft();
        void moveRight();
    }

    static class MovablePoint implements Movable {
        int x, y, xSpeed, ySpeed;
        public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
            this.x = x; this.y = y;
            this.xSpeed = xSpeed; this.ySpeed = ySpeed;
        }
        @Override public void moveUp()    { y -= ySpeed; }
        @Override public void moveDown()  { y += ySpeed; }
        @Override public void moveLeft()  { x -= xSpeed; }
        @Override public void moveRight() { x += xSpeed; }
        @Override public String toString() {
            return "MovablePoint(" + x + ", " + y + ") speed(" + xSpeed + ", " + ySpeed + ")";
        }
    }

    static class MovableCircle implements Movable {
        private int radius;
        private MovablePoint center;
        public MovableCircle(int x, int y, int xSpeed, int ySpeed, int radius) {
            this.center = new MovablePoint(x, y, xSpeed, ySpeed);
            this.radius = radius;
        }
        @Override public void moveUp()    { center.moveUp(); }
        @Override public void moveDown()  { center.moveDown(); }
        @Override public void moveLeft()  { center.moveLeft(); }
        @Override public void moveRight() { center.moveRight(); }
        @Override public String toString() {
            return "MovableCircle(radius=" + radius + ", center=" + center + ")";
        }
    }

    static class MovableRectangle implements Movable {
        private MovablePoint topLeft, bottomRight;
        public MovableRectangle(int x1, int y1, int x2, int y2, int xSpeed, int ySpeed) {
            topLeft = new MovablePoint(x1, y1, xSpeed, ySpeed);
            bottomRight = new MovablePoint(x2, y2, xSpeed, ySpeed);
        }
        private boolean sameSpeed() {
            return topLeft.xSpeed == bottomRight.xSpeed
                    && topLeft.ySpeed == bottomRight.ySpeed;
        }
        @Override public void moveUp()    { if (sameSpeed()) { topLeft.moveUp();    bottomRight.moveUp(); } }
        @Override public void moveDown()  { if (sameSpeed()) { topLeft.moveDown();  bottomRight.moveDown(); } }
        @Override public void moveLeft()  { if (sameSpeed()) { topLeft.moveLeft();  bottomRight.moveLeft(); } }
        @Override public void moveRight() { if (sameSpeed()) { topLeft.moveRight(); bottomRight.moveRight(); } }
        @Override public String toString() {
            return "MovableRectangle(" + topLeft + " - " + bottomRight + ")";
        }
    }

    public static void run() {
        System.out.println("\nПРАКТИКА №3");

        System.out.println("Shape");
        Shape s1 = new Circle(5.5, "RED", false);
        System.out.println(s1);
        System.out.println(s1.getArea());
        System.out.println(s1.getPerimeter());
        System.out.println(s1.getColor());
        System.out.println(s1.isFilled());

        Circle c1 = (Circle) s1;
        System.out.println(c1.getRadius());

        Shape s3 = new Rectangle(1.0, 2.0, "RED", false);
        System.out.println(s3);
        System.out.println(s3.getArea());
        System.out.println(((Rectangle) s3).getLength());

        Shape s4 = new Square(6.6);
        System.out.println(s4);
        System.out.println(((Square) s4).getSide());

        System.out.println("Movable");
        MovablePoint p = new MovablePoint(0, 0, 2, 2);
        System.out.println("До: " + p);
        p.moveRight(); p.moveDown();
        System.out.println("После: " + p);

        MovableCircle c = new MovableCircle(5, 5, 1, 1, 10);
        System.out.println("До: " + c);
        c.moveLeft(); c.moveUp();
        System.out.println("После: " + c);

        MovableRectangle r = new MovableRectangle(0, 0, 10, 10, 1, 1);
        System.out.println("До: " + r);
        r.moveRight(); r.moveDown();
        System.out.println("После: " + r);
    }
}
